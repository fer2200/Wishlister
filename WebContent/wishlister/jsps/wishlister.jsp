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

<c:set var="checkings" value='${requestScope["wishListerFriendsCheckings"]}' />

<p>Recent</p>

<div id="divCheckingsContainer">
<div id="divCheckingsRow">
<c:set var="count" value="1"/>
<c:forEach var="checking" items="${checkings}" step="1" begin="0">
 <c:if test="${count==5}">
   <c:set var="count" value="1"/>
   </div>
   <div id="divCheckingsRow" style="display: table-row;">
 </c:if>
 <div id="divCheckingIDNo${count}">
     <div id="divCheckingPicture">     
     <c:out value="${checking.friendFullName}"/>
     </div> 
 	<div id="divCheckingText">
     <c:out value="${checking.venueName}"/>
     </div>
 </div>
 <c:set var="count" value="${count+1}"/>
</c:forEach>

</div>
</div>
</body>
</html>