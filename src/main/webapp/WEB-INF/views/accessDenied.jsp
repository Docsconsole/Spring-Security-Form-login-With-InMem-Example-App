<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

<h3>Access is denied</h3>
<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <c:choose>
  		<c:when test="${empty username}">
  			<h3>You do not have permission to access this page!</h3>
  		</c:when>
  		<c:otherwise>
  			<h3>Username : ${username} <br/>You do not have permission to access this page!</h3>
  		</c:otherwise>
  	</c:choose>
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>

</body>
</html>