<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<u:findHead/>
<br><br><br>
    <style>hr {
    border: none;
    background-color: #200122;
    color: #200122;
    height: 2px;
    }</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">Изменить информацию о себе</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
    <input type="hidden" name="action" value="UpdateMyself"/>
    <input type="hidden" name="user_password" value="${user.password}"/>
    <input type="hidden" name="user_role" value="${user.role}"/>
    <input type="hidden" name="user_id" value="${user.id}"/>
    <hr>
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
    <hr>
    <div class="form-group">
    <button type="submit" class="btn btn-default">Изменить</button>
    </div>
    </form>
    <hr>
    </div>

<style>
.myText{
        color: white;
        }
</style>
</BODY>
<HTML>

