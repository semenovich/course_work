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
        <th>�����</th>
        <th>������</th>
        <th>����</th>
        <th>���</th>
        <th>�������</th>
        <th>�����</th>
        <th>�������</th>
    </tr>
    <div class="container">
    <c:if test="${empty users}">
        <h3 class="text-center text-danger">������ ����!</h3>
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
                <input type="submit" class="btn btn-default" name="update" value="��������"/>
            </form>
                <form method="get" action="/project/servlet">
                    <input type="hidden" name="action" value="DeleteUserAndUserInfo"/>
                    <input type="hidden" name="user_id" value="${user.id}"/>
                    <input type="submit" class="btn btn-default" name="delete" value="�������"/>
                </form>
            </td>
            <td>
                <c:if test="${user.status eq true}">
                <form method="get" action="/project/servlet">
                    <input type="hidden" name="action" value="blockUser"/>
                    <input type="hidden" name="user_id" value="${user.id}"/>
                    <input type="submit" class="btn btn-default btn-danger" name="delete" value="�������������"/>
                </form>
                </c:if>
                <c:if test="${user.status eq false}">
                <form method="get" action="/project/servlet">
                    <input type="hidden" name="action" value="unblockUser"/>
                    <input type="hidden" name="user_id" value="${user.id}"/>
                    <input type="submit" class="btn btn-default btn-success" name="delete" value="��������������"/>
                </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
    <hr>
</div>

<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">����������</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="sortUser"/>
        <br>
        <select name="comparator" required class="form-control">
            <option value="name">�� �����</option>
            <option value="surname">�� �������</option>
            <option value="email">�� �����</option>
            <option value="telephone">�� ������ ���.</option>
            <option value="role">�� ����</option>
        </select>
        <br>
        <button type="submit" class="btn btn-default">�����������</button>
    </form>
    <hr><br>
</div>


<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">�����</h3>
<form class="form-horizontal"method="post" action="/project/servlet">
    <input type="hidden" name="action" value="searchUser"/>
    <input type="text" class="form-control" name="value">
    <br>
    <select name="search" required class="form-control">
        <option value="name">�� �����</option>
        <option value="surname">�� �������</option>
        <option value="email">�� �����</option>
        <option value="telephone">�� ������ ���.</option>
        <option value="role">�� ����</option>
    </select>
    <br>
    <button type="submit" class="btn btn-default">�����</button>
</form>
    <hr><br>
</div>

<div class="container col-md-offset-3 col-md-6">
    <h3 class="text-center text-info">�������� ������������</h3>
    <form class="form-horizontal"method="post" action="/project/servlet">
        <input type="hidden" name="action" value="addUserAndUserInfo"/>
        <div class="form-group">
            <label for="exampleInputName">���</label>
            <input type="text" class="form-control" minlength="1" pattern="([A-z]|[�-�])*" name="user_info_name" id="exampleInputName">
        </div>
        <div class="form-group">
            <label for="exampleInputSurname">�������</label>
            <input type="text" class="form-control" minlength="1" pattern="([A-z]|[�-�])*" name="user_info_surname" id="exampleInputSurname">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail">�����</label>
            <input type="email" class="form-control" name="user_info_email" id="exampleInputEmail">
        </div>
        <div class="form-group">
            <label for="exampleInputTelephone">����� ��������</label>
            <input type="text" class="form-control" maxlength="15" minlength="6" name="user_info_telephone" id="exampleInputTelephone">
        </div>
        <div class="form-group">
            <label for="exampleInputLogin1">�����</label>
            <input type="text" class="form-control" minlength="4" maxlength="25" name="user_login" id="exampleInputLogin1">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">������</label>
            <input type="password" class="form-control" minlength="5" maxlength="25" name="user_password" id="exampleInputPassword1">
        </div>
        <div class="form-group">
            <label for="exampleInputRole">����</label>
            <select name="user_role" required class="form-control" id="exampleInputRole">
                <option value="ADMIN">�����</option>
                <option value="GUEST">�����</option>
                <option value="SELLER">��������</option>
                <option value="PERSONNALOFFICER">���������� �� ������</option>
            </select>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">��������</button>
        </div>
    </form>
    <hr>
</div>
</BODY>
</HTML>