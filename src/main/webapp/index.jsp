<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Parts manager</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div align="center" class="tape">
<h3>Welcome to the Parts manager</h3>
<br/>
  <form action="${pageContext.request.contextPath}/parts">
    <button type="submit" class="buttonGrad">Start work with the Parts manager</button>
  </form>
<br/>
</div>
</body>
</html>