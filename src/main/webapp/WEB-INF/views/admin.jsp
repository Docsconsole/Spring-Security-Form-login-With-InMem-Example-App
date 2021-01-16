<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 style="color: #38b5bb;">Demonstration for Spring Security Form login</h2>
<h2 style="color: #38b5bb;">Hello <span>${username}<span>: Welcome to admin</h2>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>