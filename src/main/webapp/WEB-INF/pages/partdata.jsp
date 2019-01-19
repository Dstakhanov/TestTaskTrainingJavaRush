<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>${mainTitle}</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
<div align="center" class="tape">
  <h1>${title}</h1>
<table class="table_blur">
  <tr>
    <th width="80">ID</th>
    <th width="120">Name</th>
    <th width="120">Need</th>
    <th width="120">Amount</th>
  </tr>
  <c:if test="${!empty listParts}">
  <c:forEach items="${listParts}" var="part">
  <tr>

    <td>${part.id}</td>
    <td>${part.partName}</td>
    <td>${part.partNeed}</td>
    <td>${part.partAmount}</td>

  </tr>
  </c:forEach>
  </c:if>

  <c:if test="${!empty part}">
  <tr>
    <td>${part.id}</td>
    <td>${part.partName}</td>
    <td>${part.partNeed}</td>
    <td>${part.partAmount}</td>
  </tr>
  </c:if>
</table>
  </div>
</body>
</html>