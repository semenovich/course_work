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
        <th>ПРОИЗВОДИТЕЛЬ</th>
        <th>МОДЕЛЬ</th>
        <th>ГОД</th>
        <th>ЦВЕТ</th>
        <th>ТОПЛИВО</th>
        <th>ТРАНСМИССИЯ</th>
        <th>СТАТУС</th>
    </tr>
    <div class="container">
    <c:if test="${empty cars}">
        <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
    </c:if>
    </div>

    <c:forEach items="${cars}" var="car" varStatus="status">
        <tr align=\"center\">
            <td><c:out value="${car.producer}"/></td>
            <td><c:out value="${car.model}"/></td>
            <td><c:out value="${car.year}"/></td>
            <td><c:out value="${car.color}"/></td>
            <td><c:out value="${car.fuel}"/></td>
            <td><c:out value="${car.transmission}"/></td>
            <c:set var="temp" value="true"/>
        <c:forEach items="${orders}" var="order" varStatus="status">
            <c:if test="${order.car.id eq car.id}">
                <td class="text-danger">Продана</td>
                <c:set var="temp" value="false"/>
            </c:if>
        </c:forEach>
            <c:if test="${temp eq true}">
                <td class="text-success">В продаже</td>
            </c:if>


            <td><form method="post" action="/project/servlet">
                <input type="hidden" name="action" value="updatePageCar"/>
                <input type="hidden" name="car_id" value="${car.id}"/>
                <input type="submit" class="btn btn-default" name="update" value="Изменить"/>
            </form>
                <form method="get" action="/project/servlet">
                    <input type="hidden" name="action" value="DeleteCar"/>
                    <input type="hidden" name="car_id" value="${car.id}"/>
                    <input type="submit" class="btn btn-default" name="delete" value="Удалить"/>
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
        <input type="hidden" name="action" value="sortCar"/>
        <br>
        <select name="comparator" required class="form-control">
            <option value="producer">По производителю</option>
            <option value="model">По модели</option>
            <option value="year">По году</option>
            <option value="color">По цвету</option>
            <option value="transmission">По коробке передач</option>
            <option value="fuel">По топливу</option>
        </select>
        <br>
        <button type="submit" class="btn btn-default">Сортировать</button>
    </form>
    <hr><br>
</div>


<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Поиск</h3>
<form class="form-horizontal"method="post" action="/project/servlet">
    <input type="hidden" name="action" value="searchCar"/>
    <input type="text" class="form-control" name="value">
    <br>
    <select name="search" required class="form-control">
        <option value="producer">По производителю</option>
        <option value="model">По модели</option>
        <option value="year">По году</option>
        <option value="color">По цвету</option>
        <option value="transmission">По коробке передач</option>
        <option value="fuel">По топливу</option>
    </select>
    <br>
    <button type="submit" class="btn btn-default">Поиск</button>
</form>
    <hr><br>
</div>

<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Добавить машину</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="addCar"/>
        <div class="form-group">
            <label for="exampleInputProducer">Производитель</label>
            <input type="text" minlength="1" class="form-control" name="car_producer" id="exampleInputProducer">
        </div>
        <div class="form-group">
            <label for="exampleInputModel">Модель</label>
            <input type="text" minlength="1" class="form-control" name="car_model" id="exampleInputModel">
        </div>
        <div class="form-group">
            <label for="exampleInputColor">Цвет</label>
            <input type="text" class="form-control" minlength="3" pattern="[A-Z]([a-z])*" name="car_color" id="exampleInputColor">
        </div>
        <div class="form-group">
            <label for="exampleInputYear">Год выпуска</label>
            <input type="number" min="1940" max="2020" class="form-control" name="car_year" id="exampleInputYear">
        </div>
        <div class="form-group">
            <label for="exampleInputFuel">Тип топлива</label>
            <select name="car_fuel" required class="form-control" id="exampleInputFuel">
                <option value="Бензин">Бензин</option>
                <option value="Дизель">Дизель</option>
                <option value="Газ">Газ</option>
                <option value="Гибрид">Гибрид</option>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleInputTransmission">Коробка передач</label>
            <select name="car_transmission" required class="form-control" id="exampleInputTransmission">
                <option value="Автоматическая">Автоматическая</option>
                <option value="Механическая">Механическая</option>
            </select>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Добавить</button>
        </div>
    </form>
    <hr>
</div>
</BODY>
</HTML>
