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

<div class="container col-md-offset-3 col-md-6">
    <H1 class="text-center">Чек покупки</H1>
    <br>
    <hr>
    <label>Номер заказа:</label><c:out value="${order.id}"/><br>
    <label>Дата:</label><c:out value="${order.date}"/><br>
    <label>Цена:</label><c:out value="${order.price}"/><strong>$</strong><br>
    <br>
    <hr>
    <label>Номер автомобиля:</label><c:out value="${order.car.id}"/><br>
    <label>Производитель:</label><c:out value="${order.car.producer}"/><br>
    <label>Модель:</label><c:out value="${order.car.model}"/><br>
    <label>Год выпуска:</label><c:out value="${order.car.year}"/><br>
    <label>Цвет:</label><c:out value="${order.car.color}"/><br>
    <label>Тип топлива:</label><c:out value="${order.car.fuel}"/><br>
    <label>Коробка передач:</label><c:out value="${order.car.transmission}"/><br>
    <br>
    <hr>
    <label>Номер покупателя:</label><c:out value="${order.customer.id}"/><br>
    <label>Имя покупателя:</label><c:out value="${order.customer.name}"/><br>
    <label>Фамилия покупателя:</label><c:out value="${order.customer.surname}"/><br>
    <label>Адрес покупателя:</label><c:out value="${order.customer.address}"/><br>
    <br>
    <hr>
    <label>Номер продавца:</label><c:out value="${order.user.id}"/><br>
    <label>Имя продавца:</label><c:out value="${order.user.name}"/><br>
    <label>Фамилия продавца:</label><c:out value="${order.user.surname}"/><br>
    <label>Почта продавца:</label><c:out value="${order.user.email}"/><br>
    <label>Телефон продавца:</label><c:out value="${order.user.telephone}"/><br>
    <hr>
    <form method="post" action="/project/servlet">
        <input type="hidden" name="action" value="printCheck">
        <input type="hidden" name="order_id" value="${order.id}">
        <button type="submit" class="btn btn-default btn-success">Сохранить в файл</button>
    </form>
    <hr>
</div>

</body>
</html>
