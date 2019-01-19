<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${mainTitle}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>
<div align="center" class="tape">

<table>
    <tr><td><h1>${title}</h1>
    </td>
    <td align="right"> <c:url value="/search" var="searchAction" />
        <form:form action="${searchAction}"  method="POST"  >
            <label >
                <input type="text" name="partName"/>
            </label>
            <input type="submit" value="search" class="buttonGrad" /> </form:form></td>
    </tr>
<tr><td>
    <c:url value="/sort" var="sortAction" />
    <form:form action="${sortAction}"  method="GET" >
        <input name="sortParts" type="radio" value="0" <c:if test="${curChecked == 0}">checked</c:if>> All parts
        <input name="sortParts" type="radio" value="1" <c:if test="${curChecked == 1}">checked</c:if>> Only needed parts
        <input name="sortParts" type="radio" value="2" <c:if test="${curChecked == 2}">checked</c:if>> Optional parts
        <input type="submit" value="sort" class="buttonGrad">
    </form:form>
</td><td align="right">
    <div class="container">
        <input id="modal" type="checkbox" <c:if test="${openModalWindow == 1}">checked</c:if>>
    <c:url value="/startadd" var="startAddAction" />
    <form:form action="${startAddAction}"  method="POST" >
        <input type="submit" value="add a part" class="buttonGrad">
    </form:form>

           <div class="modal-content">

            <table class="table_add">
                <caption>
                    <c:if test="${!empty part.partName && part.partAmount!=0}">
                        <spring:message text="Edit part"/>
                    </c:if>
                    <c:if test="${empty part.partName || part.partAmount==0}">
                        <spring:message text="Add part"/>
                    </c:if>
            </caption>
                <tr>
                    <td>
                        <c:url var="addAction" value="/parts/add"/>

                        <form:form action="${addAction}" commandName="part">
                            <table>
                                <c:if test="${!empty part.partName && part.partAmount!=0}">
                                    <tr>
                                        <td>
                                            <form:label path="id">
                                                <spring:message text="ID"/>
                                            </form:label>
                                        </td>
                                        <td>
                                            <form:input path="id" readonly="true" size="8" disabled="true"/>
                                            <form:hidden path="id"/>
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td>
                                        <form:label path="partName">
                                            <spring:message text="Name"/>
                                        </form:label>
                                    </td>
                                    <td>
                                        <form:input path="partName" />
                                    </td>
                                </tr>
                                <tr><td colspan="2"><span class="error"><form:errors path="partName" /></span></td></tr>
                                <tr>
                                    <td>
                                        <form:label path="partNeed">
                                            <spring:message text="Need"/>
                                        </form:label>
                                    </td>
                                    <td>
                                        <form:select path="partNeed" cssClass="select">
                                            <form:option value="true">true</form:option>
                                            <form:option value="false">false</form:option>
                                        </form:select>
                                    </td>
                                </tr>
                                <tr><td colspan="2"></td></tr>
                                <tr>
                                    <td>
                                        <form:label path="partAmount">
                                            <spring:message text="Amount"/>
                                        </form:label>
                                    </td>
                                    <td>
                                        <form:input path="partAmount"/>
                                    </td>
                                </tr>
                                <tr><td colspan="2"><span class="error"><form:errors path="partAmount" /></span></td></tr>
                                <tr>
                                    <td colspan="2">
                                        <c:if test="${!empty part.partName && part.partAmount!=0}">
                                            <input type="submit"
                                                   value="<spring:message text="Edit Part"/>" class="buttonGrad" for="modal"/>
                                        </c:if>
                                        <c:if test="${empty part.partName || part.partAmount==0}">
                                            <input type="submit"
                                                   value="<spring:message text="Add Part"/>" class="buttonGrad" for="modal"/>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </form:form>
                    </td>
                </tr>
            </table>
        </div> <!-- .modal-content -->
    </div> <!-- .modal-container -->
</td></tr>
<tr>
    <td colspan="2">

<c:if test="${!empty listParts}">
    <table class="table_blur">
        <tr>
            <!--  <th width="80">ID</th> -->
            <th width="120">Name</th>
            <th width="120">Need</th>
            <th width="120">Amount</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listParts}" var="part">
            <tr>
                <!-- <td>${part.id}</td>-->
                <td><a href="/partdata/${part.id}">${part.partName}</a></td>
                <td>${part.partNeed}</td>
                <td>${part.partAmount}</td>
                <td><a href="<c:url value='/edit/${part.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${part.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
        <tr><td colspan="6"></td></tr>
        <tr>
            <td colspan="3">It's possible to make</td>
            <td>${compCount}</td>
            <td colspan="2">computers</td>
        </tr>
    </table>
    </td>
</tr>
    <tr>
        <td colspan="2" align="right">
    <div id="pagination">
        <c:url value="/parts" var="prev">
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
        </c:if>

        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span>${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="/parts" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href='<c:out value="${url}" />'>${i.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="/parts" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 <= maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">Next</a>
        </c:if>
    </div>
</c:if>
        </td>
    </tr>
</table>
</div>
</body>
</html>

