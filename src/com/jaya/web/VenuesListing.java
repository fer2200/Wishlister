package com.jaya.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jaya.controller.CheckingsData;
import com.jaya.controller.DataHelper;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;

/**
 * Servlet implementation class venuesListing
 */
@WebServlet(name = "venuesListing", displayName = "Servlet de wishlist Foursquare", urlPatterns = { "/venuesListing" })
public class VenuesListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(VenuesListing.class.getName());

	FoursquareApi foursquareApi = null;
	String accessToken; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VenuesListing() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		if (request.getParameter("code") != null) {
			logger.info("Iniciar wishlist");
			if (logger.isInfoEnabled())
				logger.info("Iniciar wishlist");
			handleCallback(request, response);
			getFourSquareData(request);
			request.getRequestDispatcher("/wishlister/jsps/wishlister.jsp").forward(request,response);
		} else {
			authenticationRequest(request, response);
		}

	}
	
	protected void getFourSquareData(HttpServletRequest request) {
		CheckingsData checkingsData = new CheckingsData();		
		request.setAttribute("wishListerFriendsCheckings", checkingsData.getFriendsCheckings(this.accessToken));
		System.out.println(request.getAttribute("wishListerFriendsCheckings"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void handleCallback(HttpServletRequest request, HttpServletResponse response) {
		// Ap�s a permiss�o, o usu�rio � redirecionado conforme a URL de retorno
		String code = request.getParameter("code");
		try {
			// autenticar com o c�digo recebido pela chamada de retorno
			foursquareApi.authenticateCode(code);
			foursquareApi.setVersion(DataHelper.formatTime());			
			this.accessToken = foursquareApi.getOAuthToken();
			
	
		} catch (FoursquareApiException e) {
			// TODO: Error handling
			System.out.print(e.getMessage());
		}

	}

	public void authenticationRequest(HttpServletRequest request, HttpServletResponse response) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("com/jaya//config/config.properties");
		Properties properties = new Properties();

		try {

			properties.load(input);

			String clientId = properties.getProperty("client_id");
			String clientSecret = properties.getProperty("client_secret");
			String redirectURL = properties.getProperty("redirect_url");

			foursquareApi = new FoursquareApi(clientId, clientSecret, redirectURL);

			// Redirecionar o usu�rio para a p�gina Forsquare de autentica��o / permiss�o
			response.sendRedirect(foursquareApi.getAuthenticationUrl());			
		

		} catch (IOException e) {
			// TODO: Error handling
			if (logger.isErrorEnabled())
				logger.error("IOException: " + e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			if (logger.isErrorEnabled())
				logger.error("Erro: " + e.getMessage());

			System.out.print(e.getMessage());
		}

	}

}
