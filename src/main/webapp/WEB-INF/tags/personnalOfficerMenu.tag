<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <TITLE>Специалист по кадрам</TITLE>
</HEAD>
<BODY>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<nav class="navbar navbar-default " role="navigation">
    <div class="container-fluid backgr">
        <div class="navbar-header">
            <a class="navbar-brand" href="https://www.google.com/maps/place/АВТОДОМ+Premium/@53.9260466,27.5100926,18.22z/data=!4m5!3m4!1s0x46dbc56c30a818c5:0x4f759ab963bbdec7!8m2!3d53.926098!4d27.510304?hl=ru-RU">Автосалон</a>
        </div>
        <style>
            .backgr{
                background-color: #eec111;
                color: white;
            }
        </style>
        <div>
            <form class="navbar-form navbar-left" method="post" action="/project/servlet">
                <input type="hidden" name="action" value="MainPage"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">На главную</button>
                </div>
            </form>
            <form class="navbar-form navbar-left" method="post" action="/project/servlet">
                <input type="hidden" name="action" value="readAllUserAndUserInfo"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Работа с пользователями</button>
                </div>
            </form>
            <form class="navbar-form navbar-left" method="post" action="/project/servlet">
                <input type="hidden" name="action" value="freeCars"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Автомобили в наличии</button>
                </div>
            </form>
            <form class="navbar-form navbar-right" method="post" action="/project/servlet">
                <input type="hidden" name="action" value="logout"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Выйти</button>
                </div>
            </form>
        </div>
    </div>
</nav>
<DIV id="page">
    <jsp:doBody/>
</DIV>

