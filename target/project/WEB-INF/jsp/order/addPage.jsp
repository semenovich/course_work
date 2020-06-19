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
                    <td><input name="car_id" type="radio" value="${car.id}"></td>
                    </td>
                </tr>
        </c:forEach>
    </table>
    <hr>
</div>
    <div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Добавить покупку</h3>
        <input type="hidden" name="action" value="addOrder"/>
        <div class="form-group">
            <label for="exampleInputPrice">Цена</label>
            <input type="number" class="form-control" min="0" name="order_price" id="exampleInputPrice">
        </div>
        <div class="form-group">
            <label for="exampleInputYear">Год</label>
            <input type="number" min="2010" max="2020" class="form-control" name="date_year" id="exampleInputYear">
        </div>
        <div class="form-group">
            <label for="exampleInputMonth">Месяц</label>
            <input type="number" min="1" max="12" class="form-control" name="date_month" id="exampleInputMonth">
        </div>
        <div class="form-group">
            <label for="exampleInputDay">День</label>
            <input type="number" min="1" max="31" class="form-control" name="date_day" id="exampleInputDay">
        </div>
        <div class="form-group">
            <input type="hidden" name="order_seller_id" value="${user.id}"/>
        </div>

        <div class="form-group">
            <label for="exampleInputFuel">Преобреал ли водиель ранее автомобиль в нашем салоне?</label>
            <select name="isBought" required class="form-control" id="exampleInputFuel">
                <option value="yes">Да</option>
                <option value="no">Нет</option>
            </select>
        </div>
        <div class="form-group">
            <label class="text-warning">Если приобретал,то введите его номер</label>
            <br>
            <label class="text-warning">Если нет, то введите его информацию</label>
        </div>
        <div class="form-group">
            <label for="exampleInputId">Номер покупателя</label>
            <input type="number" class="form-control" min="0" name="order_customer_id" id="exampleInputId">
        </div>
        <div class="form-group">
            <label for="exampleInputAddress">Адрес покупателя</label>
            <input type="text" class="form-control" name="customer_address" id="exampleInputAddress">
        </div>
        <div class="form-group">
            <label for="exampleInputSurname">Фамилия покупателя</label>
            <input type="text" class="form-control" pattern="([A-z]|[А-я])*" name="customer_surname" id="exampleInputSurname">
        </div>
        <div class="form-group">
            <label for="exampleInputName">Имя покупателя</label>
            <input type="text" class="form-control" pattern="([A-z]|[А-я])*" name="customer_name" id="exampleInputName">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Добавить</button>
        </div>
    </div>
    </form>

</body>
</html>
