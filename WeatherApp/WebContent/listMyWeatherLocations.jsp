<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="edu.neu.cs5200.user.*, java.util.*, edu.neu.cs5200.weather.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<link href ="css/bootstrap.css" rel="stylesheet"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<div class ="container">
	<h1>List of my Weather Locations</h1>
	
	
	
	<%
	// this does not involve the server, did not have to create a session for us, scales much better, not asking the server to remember me, maintained entirely on the client side, stateless application
	User user = (User) request.getAttribute("user");
	System.out.println(user.getUsername());
	
	// Tpo down design
	WeatherDao weatherDao = WeatherDao.getInstance();
	List<Weather> weathers = weatherDao.selectFromUserId(user.getId());
	%>
	
	<table class ="table">
	<%
	for( Weather weather: weathers) {
		%>
			<tr>
				<td><%= weather.getZip() %></td>
				<td><%= weather.getLocationname() %></td>
			</tr>
			
		<%
		System.out.println(weather.getLocationname());
	}
	%>
	
	</table>
	<%= user.getUsername() %>
	<%= user.getId() %>
	</div>
</body>
</html>