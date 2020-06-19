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

<div class="container">
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>НОМЕР ПОКУПКИ</th>
            <th>ЦЕНА</th>
            <th>ДАТА</th>
            <th>НОМЕР МАШИНЫ</th>
            <th>НОМЕР ПРОДАВЦА</th>
            <th>НОМЕР ПОКУПАТЕЛЯ</th>
        </tr>
        <div class="container">
            <c:if test="${empty orders}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>

        <c:forEach items="${orders}" var="order" varStatus="status">
            <tr align=\"center\">
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.price}"/></td>
                <td><c:out value="${order.date}"/></td>
                <td><c:out value="${order.car.id}"/></td>
                <td><c:out value="${order.user.id}"/></td>
                <td><c:out value="${order.customer.id}"/></td>
                <td><form method="post" action="/project/servlet">
                    <input type="hidden" name="action" value="updatePageOrder"/>
                    <input type="hidden" name="order_id" value="${order.id}"/>
                    <input type="submit" class="btn btn-default" name="update" value="Изменить"/>
                </form>
                    <form method="get" action="/project/servlet">
                        <input type="hidden" name="action" value="DeleteOrder"/>
                        <input type="hidden" name="order_id" value="${order.id}"/>
                        <input type="submit" class="btn btn-default" name="delete" value="Удалить"/>
                    </form>
                    <form method="get" action="/project/servlet">
                        <input type="hidden" name="action" value="moreOrderInfo"/>
                        <input type="hidden" name="order_id" value="${order.id}"/>
                        <input type="submit" class="btn btn-default" name="delete" value="Подробнее"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <hr>
</div>

<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Сортировка</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="sortOrder"/>
        <br>
        <select name="comparator" required class="form-control">
            <option value="price">По цене</option>
            <option value="date">По дате</option>
        </select>
        <br>
        <button type="submit" class="btn btn-default">Сортировать</button>
    </form>
    <hr><br>
</div>


<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Поиск</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="searchOrder"/>
        <input type="text" class="form-control" name="value">
        <br>
        <select name="search" required class="form-control">
            <option value="date">По дате</option>
            <option value="price">По цене</option>
            <option value="seller_id">По номеру продавца</option>
            <option value="id">По номеру заказа</option>
            <option value="customer_id">По номеру покупателя</option>
            <option value="car_id">По номеру машины</option>
        </select>
        <br>
        <button type="submit" class="btn btn-default">Поиск</button>
    </form>
    <hr><br>
</div>

<div class="container">
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="addOrderPage"/>
        <div class="form-group">
            <button type="submit" class="btn btn-info btn-md btn-block">Оформить новую покупку</button> >
        </div>
    </form>
    <hr>
</div>
</BODY>
</HTML>
