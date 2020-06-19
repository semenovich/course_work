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
            <th>ПРОИЗВОДИТЕЛЬ</th>
            <th>МОДЕЛЬ</th>
            <th>ГОД</th>
            <th>ЦВЕТ</th>
            <th>ТОПЛИВО</th>
            <th>ТРАНСМИССИЯ</th>
        </tr>
        <div class="container">
            <c:if test="${empty cars}">
                <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
            </c:if>
        </div>
        <tr align=\"center\">
            <td><c:out value="${car.producer}"/></td>
            <td><c:out value="${car.model}"/></td>
            <td><c:out value="${car.year}"/></td>
            <td><c:out value="${car.color}"/></td>
            <td><c:out value="${car.fuel}"/></td>
            <td><c:out value="${car.transmission}"/></td>
        </tr>
    </table>
    <hr>
</div>


<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Изменить информацию об автомобиле</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="updateCar"/>
        <input type="hidden" name="car_id" value="${car.id}"/>
        <div class="form-group">
            <label for="exampleInputProducer">Производитель</label>
            <input type="text" class="form-control" minlength="1" value="${car.producer}" name="car_producer" id="exampleInputProducer">
        </div>
        <div class="form-group">
            <label for="exampleInputModel">Модель</label>
            <input type="text" class="form-control" minlength="1" value="${car.model}" name="car_model" id="exampleInputModel">
        </div>
        <div class="form-group">
            <label for="exampleInputColor">Цвет</label>
            <input type="text" class="form-control" minlength="3" pattern="[A-Z]([a-z])*" value="${car.color}" name="car_color" id="exampleInputColor">
        </div>
        <div class="form-group">
            <label for="exampleInputYear">Год выпуска</label>
            <input type="number" class="form-control" value="${car.year}" min="1940" max="2020" name="car_year" id="exampleInputYear">
        </div>
        <div class="form-group">
            <label for="exampleInputFuel">Тип топлива</label>
            <select name="car_fuel" required class="form-control" value="${car.fuel}" id="exampleInputFuel">
                <option value="Бензин">Бензин</option>
                <option value="Дизель">Дизель</option>
                <option value="Газ">Газ</option>
                <option value="Гибрид">Гибрид</option>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleInputTransmission">Коробка передач</label>
            <select name="car_transmission" required class="form-control" value="${car.transmission}" id="exampleInputTransmission">
                <option value="Автоматическая">Автоматическая</option>
                <option value="Механическая">Механическая</option>
            </select>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Изменить</button>
        </div>
    </form>
    <hr>
</div>

</Body>
</Html>