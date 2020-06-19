<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<u:findHead/>
<style>hr {
    border: none;
    background-color: #200122;
    color: #200122;
    height: 2px;
}</style>

<form class="form-horizontal"method="post" action="/project/servlet">
    <div class="container">
        <h3 class="text-center text-info">Выберите автомобиль</h3>
        <c:if test="${empty freeCars}">
            <h3 class="text-center text-danger">Автомобилей нету в наличии</h3>
        </c:if>
        <table border="1" width="40%" class="table table-hover table-bordered">
            <tr align=\"center\" class="active">
                <th>ПРОИЗВОДИТЕЛЬ</th>
                <th>МОДЕЛЬ</th>
                <th>ГОД</th>
                <th>ЦВЕТ</th>
                <th>ТОПЛИВО</th>
                <th>ТРАНСМИССИЯ</th>
            </tr>
            <c:forEach items="${freeCars}" var="car" varStatus="status">
                <tr align=\"center\">
                    <td><c:out value="${car.producer}"/></td>
                    <td><c:out value="${car.model}"/></td>
                    <td><c:out value="${car.year}"/></td>
                    <td><c:out value="${car.color}"/></td>
                    <td><c:out value="${car.fuel}"/></td>
                    <td><c:out value="${car.transmission}"/></td>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <hr>
    </div>
</form>
</BODY>
</HTML>