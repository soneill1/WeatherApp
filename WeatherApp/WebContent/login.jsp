<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
	<h1>Login</h1>
<form action="/WeatherApp/loginAction" method="post">
	Username:
	<input name="username" class="form-control"/>
	Password:
	<input name="password" type="password" class="form-control"/>
	
	<button class="btn btn-primary btn-block">Login</button>
</form>
</div>
</body>
</html>