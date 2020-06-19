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
        <tr align=\"center\" class="acttive">
            <th>ЛОГИН</th>
            <th>ПАРОЛЬ</th>
            <th>РОЛЬ</th>
            <th>ИМЯ</th>
            <th>ФАМИЛИЯ</th>
            <th>ПОЧТА</th>
            <th>ТЕЛЕФОН</th>
        </tr>
        <div class="container">
            <c:if test="${empty user}">
                <h3 class="text-center text-danger">Такого пользователя не существует</h3>
            </c:if>
        </div>
            <tr align=\"center\">
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.surname}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.telephone}"/></td>
            </tr>
    </table>
    <hr>
</div>
<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Изменить информацию о пользователе</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="updateUserAndUserInfo"/>
        <input type="hidden" name="user_id" value="${user.id}"/>

        <div class="form-group">
            <label for="exampleInputName">Имя</label>
            <input type="text" class="form-control" minlength="1" pattern="([A-z]|[А-я])*" value="${user.name}" name="user_info_name" id="exampleInputName">
        </div>
        <div class="form-group">
            <label for="exampleInputSurname">Фамилия</label>
            <input type="text" class="form-control" minlength="1" pattern="([A-z]|[А-я])*" value="${user.surname}" name="user_info_surname" id="exampleInputSurname">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail">Почта</label>
            <input type="email" class="form-control" value="${user.email}" name="user_info_email" id="exampleInputEmail">
        </div>
        <div class="form-group">
            <label for="exampleInputTelephone">Номер телефона</label>
            <input type="text" class="form-control" maxlength="15" minlength="6" value="${user.telephone}" name="user_info_telephone" id="exampleInputTelephone">
        </div>
        <div class="form-group">
            <label for="exampleInputLogin1">Логин</label>
            <input type="text" class="form-control" minlength="4" maxlength="25" value="${user.login}" name="user_login" id="exampleInputLogin1">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Пароль</label>
            <input type="text" class="form-control" minlength="5" maxlength="25" value="${user.password}" name="user_password" id="exampleInputPassword1">
        </div>
        <div class="form-group">
            <label for="exampleInputRole">Роль</label>
            <select name="user_role" required class="form-control" value="${user.role}" id="exampleInputRole">
                <option value="ADMIN">Админ</option>
                <option value="GUEST">Гость</option>
                <option value="SELLER">Продавец</option>
                <option value="PERSONNALOFFICER">Специолист по кадрам</option>
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