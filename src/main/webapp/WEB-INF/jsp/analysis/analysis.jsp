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
    <h3 class="text-center text-info">Анализ заказов</h3>
    <hr>
    <h4 class="text-center text-info">Самая дорогой проданный автомобиль</h4>
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>ЦЕНА</th>
            <th>ПРОИЗВОДИТЕЛЬ</th>
            <th>МОДЕЛЬ</th>
            <th>ГОД</th>
            <th>ЦВЕТ</th>
            <th>ТОПЛИВО</th>
            <th>ТРАНСМИССИЯ</th>
        </tr>
        <div class="container">
            <c:if test="${empty expensiveOrder}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${expensiveOrder.price}"/></td>
            <td><c:out value="${expensiveOrder.car.producer}"/></td>
            <td><c:out value="${expensiveOrder.car.model}"/></td>
            <td><c:out value="${expensiveOrder.car.year}"/></td>
            <td><c:out value="${expensiveOrder.car.color}"/></td>
            <td><c:out value="${expensiveOrder.car.fuel}"/></td>
            <td><c:out value="${expensiveOrder.car.transmission}"/></td>
        </tr>
    </table>
    <hr>
    <h4 class="text-center text-info">Самый продаваемый цвет автомобиля</h4>
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>ЦВЕТ</th>
            <th>КОЛИЧЕСТВО</th>
        </tr>
        <div class="container">
            <c:if test="${empty popularColor}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${popularColor.color}"/></td>
            <td><c:out value="${popularColor.id}"/></td>
        </tr>
    </table>
    <hr>

    <h4 class="text-center text-info">Самая продаваемая марка автомобиля</h4>
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>ПРОИЗВОДИТЕЛЬ</th>
            <th>КОЛИЧЕСТВО</th>
        </tr>
        <div class="container">
            <c:if test="${empty popularProducer}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${popularProducer.producer}"/></td>
            <td><c:out value="${popularProducer.id}"/></td>
        </tr>
    </table>
    <hr>

    <h4 class="text-center text-info">Самый продаваемый вид топлива автомобиль</h4>
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>ТОПЛИВО</th>
            <th>КОЛИЧЕСТВО</th>
        </tr>
        <div class="container">
            <c:if test="${empty popularFuel}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${popularFuel.fuel}"/></td>
            <td><c:out value="${popularFuel.id}"/></td>
        </tr>
    </table>
    <hr>

    <h4 class="text-center text-info">Самый продаваемый вид трансмиссии автомобиль</h4>
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>ТРАНСМИССИЯ</th>
            <th>КОЛИЧЕСТВО</th>
        </tr>
        <div class="container">
            <c:if test="${empty popularTransmission}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${popularTransmission.transmission}"/></td>
            <td><c:out value="${popularTransmission.id}"/></td>
        </tr>
    </table>
    <hr>

    <h4 class="text-center text-info">Средняя цена автомобиля</h4>
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>ЦЕНА</th>
        </tr>
        <div class="container">
            <c:if test="${empty avgPrice}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${avgPrice}"/></td>
        </tr>
    </table>
    <hr>

    <h4 class="text-center text-info">Средний год выпуска автомобилей в салоне</h4>
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>ГОД</th>
        </tr>
        <div class="container">
            <c:if test="${empty avgCarYear}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${avgCarYear}"/></td>
        </tr>
    </table>
    <hr>

    <h4 class="text-center text-info">Лучший продавец</h4>
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>ИМЯ</th>
            <th>ФАМИЛИЯ</th>
            <th>ПОЧТА</th>
            <th>ТЕЛЕФОН</th>
            <th>КОЛ-ВО ПРОДАННЫХ АВТО</th>
        </tr>
        <div class="container">
            <c:if test="${empty expensiveOrder}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${activeSeller.name}"/></td>
            <td><c:out value="${activeSeller.surname}"/></td>
            <td><c:out value="${activeSeller.email}"/></td>
            <td><c:out value="${activeSeller.telephone}"/></td>
            <td><c:out value="${activeSeller.id}"/></td>
        </tr>
    </table>
    <hr>

    <h4 class="text-center text-info">Постоянный клиент</h4>
    <table border="1" width="40%" class="table table-hover table-bordered">
        <tr align=\"center\" class="active">
            <th>ИМЯ</th>
            <th>ФАМИЛИЯ</th>
            <th>АДРЕС</th>
            <th>КОЛ-ВО КУПЛЕНЫХ АВТО</th>
        </tr>
        <div class="container">
            <c:if test="${empty expensiveOrder}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${activeCustomer.name}"/></td>
            <td><c:out value="${activeCustomer.surname}"/></td>
            <td><c:out value="${activeCustomer.address}"/></td>
            <td><c:out value="${activeCustomer.id}"/></td>
        </tr>
    </table>
    <hr>
</div>

</body>
</html>

