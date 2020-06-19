<%@ page contentType="text/html;charset=cp1251" language="java" %>
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
        <th>ЛОГИН</th>
        <th>ПАРОЛЬ</th>
        <th>РОЛЬ</th>
        <th>ИМЯ</th>
        <th>ФАМИЛИЯ</th>
        <th>ПОЧТА</th>
        <th>ТЕЛЕФОН</th>
    </tr>
    <div class="container">
    <c:if test="${empty users}">
        <h3 class="text-center text-danger">СПИСОК ПУСТ!</h3>
    </c:if>
    </div>

    <c:forEach items="${users}" var="user" varStatus="status">
        <tr align=\"center\">
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.surname}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.telephone}"/></td>
            <td><form method="get" action="/project/servlet">
                <input type="hidden" name="action" value="updatePageUserAndUserInfo"/>
                <input type="hidden" name="user_id" value="${user.id}"/>
                <input type="submit" class="btn btn-default" name="update" value="Изменить"/>
            </form>
                <form method="get" action="/project/servlet">
                    <input type="hidden" name="action" value="DeleteUserAndUserInfo"/>
                    <input type="hidden" name="user_id" value="${user.id}"/>
                    <input type="submit" class="btn btn-default" name="delete" value="Удалить"/>
                </form>
            </td>
            <td>
                <c:if test="${user.status eq true}">
                <form method="get" action="/project/servlet">
                    <input type="hidden" name="action" value="blockUser"/>
                    <input type="hidden" name="user_id" value="${user.id}"/>
                    <input type="submit" class="btn btn-default btn-danger" name="delete" value="Заблокировать"/>
                </form>
                </c:if>
                <c:if test="${user.status eq false}">
                <form method="get" action="/project/servlet">
                    <input type="hidden" name="action" value="unblockUser"/>
                    <input type="hidden" name="user_id" value="${user.id}"/>
                    <input type="submit" class="btn btn-default btn-success" name="delete" value="Разблокировать"/>
                </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
    <hr>
</div>

<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Сортировка</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="sortUser"/>
        <br>
        <select name="comparator" required class="form-control">
            <option value="name">По имени</option>
            <option value="surname">По фамилии</option>
            <option value="email">По почте</option>
            <option value="telephone">По номеру тел.</option>
            <option value="role">По роли</option>
        </select>
        <br>
        <button type="submit" class="btn btn-default">Сортировать</button>
    </form>
    <hr><br>
</div>


<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Поиск</h3>
<form class="form-horizontal"method="post" action="/project/servlet">
    <input type="hidden" name="action" value="searchUser"/>
    <input type="text" class="form-control" name="value">
    <br>
    <select name="search" required class="form-control">
        <option value="name">По имени</option>
        <option value="surname">По фамилии</option>
        <option value="email">По почте</option>
        <option value="telephone">По номеру тел.</option>
        <option value="role">По роли</option>
    </select>
    <br>
    <button type="submit" class="btn btn-default">Поиск</button>
</form>
    <hr><br>
</div>

<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Добавить Пользователя</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="addUserAndUserInfo"/>
        <div class="form-group">
            <label for="exampleInputName">Имя</label>
            <input type="text" class="form-control" minlength="1" pattern="([A-z]|[А-я])*" name="user_info_name" id="exampleInputName">
        </div>
        <div class="form-group">
            <label for="exampleInputSurname">Фамилия</label>
            <input type="text" class="form-control" minlength="1" pattern="([A-z]|[А-я])*" name="user_info_surname" id="exampleInputSurname">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail">Почта</label>
            <input type="email" class="form-control" name="user_info_email" id="exampleInputEmail">
        </div>
        <div class="form-group">
            <label for="exampleInputTelephone">Номер телефона</label>
            <input type="text" class="form-control" maxlength="15" minlength="6" name="user_info_telephone" id="exampleInputTelephone">
        </div>
        <div class="form-group">
            <label for="exampleInputLogin1">Логин</label>
            <input type="text" class="form-control" minlength="4" maxlength="25" name="user_login" id="exampleInputLogin1">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Пароль</label>
            <input type="password" class="form-control" minlength="5" maxlength="25" name="user_password" id="exampleInputPassword1">
        </div>
        <div class="form-group">
            <label for="exampleInputRole">Роль</label>
            <select name="user_role" required class="form-control" id="exampleInputRole">
                <option value="ADMIN">Админ</option>
                <option value="GUEST">Гость</option>
                <option value="SELLER">Продавец</option>
                <option value="PERSONNALOFFICER">Специолист по кадрам</option>
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