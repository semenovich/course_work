<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<u:adminMenu/>
    <br><br><br>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <div class="container col-md-offset-3 col-md-6">
    <H1 class="text-center">Здравствуйте,<strong><c:out value="${user.name}"/></strong></H1>
    <br>
    <br>
    <H3 class="text-center"><strong>Ваши данные</strong></H3>
    <hr>
    <label>Фамилия:</label><c:out value="${user.surname}"/><br>
    <label>Имя:</label><c:out value="${user.name}"/><br>
    <label>Почта:</label><c:out value="${user.email}"/><br>
    <label>Телефон:</label><c:out value="${user.telephone}"/><br>
    <label>Логин:</label><c:out value="${user.login}"/><br>
    <br>
    <form method="get" action="/project/servlet">
    <input type="hidden" name="action" value="UpdateMyselfPage"/>
    <input type="hidden" name="user_id" value="${user.id}"/>
    <input type="submit" class="btn btn-success btn-md btn-block" name="update" value="Изменить"/>
    </form>
    <hr>
    <H1 class="text-center"><strong>Наш автосалон на картах</strong></H1>
    <br>
    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2349.3177806465505!2d27.50811531557842!3d53.92609798010457!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbc56c30a818c5%3A0x4f759ab963bbdec7!2z0JDQktCi0J7QlNCe0JwgUHJlbWl1bQ!5e0!3m2!1sru!2sby!4v1576238201510!5m2!1sru!2sby" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="">
    </iframe>
    <br><br>
    </div>


    <style>hr {
    border: none;
    background-color: #200122;
    color: #200122;
    height: 2px;
    }</style>
    <style>
    .myText{
    color: white;
    }
    </style>
</BODY>
    <HTML>
