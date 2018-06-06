<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/resources/styles/wishstyles.css' />" rel="stylesheet" type="text/css" />
<title>Wishlister Foursquare</title>
</head>
<body>
<fmt:bundle basename="com.jy.config.config" >	
<!-- 
<form action="https://foursquare.com/oauth2/authenticate?client_id=<fmt:message key="client_id"></fmt:message>&response_type=code&redirect_uri=<fmt:message key="redirect_url"></fmt:message>" method="post">
 -->
<form action="${pageContext.request.contextPath}/venuesListing" method="get"> 
<div class="container">
<div class="button-wrapper">
      <input class="addButton" id="loginbutton" type="submit" value="Login with Foursquare"/>
</div>
</div>      
</form>

</fmt:bundle>
</body>
</html>