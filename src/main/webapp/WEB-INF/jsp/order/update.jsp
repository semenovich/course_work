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

    <h3 class="text-center text-штащ">Старая информация</h3>
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
            <c:if test="${empty order}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
            <tr align=\"center\">
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.price}"/></td>
                <td><c:out value="${order.date}"/></td>
                <td><c:out value="${order.car.id}"/></td>
                <td><c:out value="${order.user.id}"/></td>
                <td><c:out value="${order.customer.id}"/></td>
                <td>
                    <form method="get" action="/project/servlet">
                        <input type="hidden" name="action" value="moreOrderInfo"/>
                        <input type="hidden" name="order_id" value="${order.id}"/>
                        <input type="submit" class="btn btn-default" name="delete" value="Подробнее"/>
                    </form>
                </td>
            </tr>
    </table>
    <hr>
</div>


<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Изменить информацию о покупке</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="updateOrder"/>
        <input type="hidden" name="order_id" value="${order.id}"/>

        <div class="form-group">
        <label for="exampleInputYear">Год</label>
        <input type="number" class="form-control" min="2010" max="2020" value="${order.date.year +1900}" name="date_year" id="exampleInputYear">
    </div>
        <div class="form-group">
            <label for="exampleInputMonth">Месяц</label>
            <input type="number" class="form-control" min="1" max="12" value="${order.date.month+1}" name="date_month" id="exampleInputMonth">
        </div>
        <div class="form-group">
            <label for="exampleInputDay">День</label>
            <input type="number" class="form-control" min="1" max="31" value="${order.date.day-1}" name="date_day" id="exampleInputDay">
        </div>
        <div class="form-group">
            <label for="exampleInputPrice">Цена</label>
            <input type="number" class="form-control" min="0" value="${order.price}" name="order_price" id="exampleInputPrice">
        </div>
        <div class="form-group">
            <label for="exampleInputCar">Номер машины</label>
            <input type="number" class="form-control" min="0" value="${order.car.id}" name="order_car_id" id="exampleInputCar">
        </div>
        <div class="form-group">
            <label for="exampleInputSeller">Номер продавца</label>
            <input type="number" class="form-control" min="0" value="${order.user.id}" name="order_seller_id" id="exampleInputSeller">
        </div>
        <div class="form-group">
            <label for="exampleInputCustomer">Номер покупателя</label>
            <input type="number" class="form-control" min="0" value="${order.customer.id}" name="order_customer_id" id="exampleInputCustomer">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Изменить</button>
        </div>
    </form>
    <hr>
</div>

</Body>
</Html>
