<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set value="ADMIN" var="role"/>
<c:if test="${authorizedUser.role eq role}">
    <u:adminMenu/>
</c:if>

<c:set value="PERSONNALOFFICER" var="role"/>
<c:if test="${authorizedUser.role eq role}">
    <u:personnalOfficerMenu/>
</c:if>

<c:set value="SELLER" var="role"/>
<c:if test="${authorizedUser.role eq role}">
    <u:sellerMenu/>
</c:if>

<c:set value="GUEST" var="role"/>
<c:if test="${authorizedUser.role eq role}">
    <u:guestMenu/>
</c:if>

