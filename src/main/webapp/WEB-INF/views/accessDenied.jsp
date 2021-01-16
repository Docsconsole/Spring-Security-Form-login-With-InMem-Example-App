<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

<h2>Demonstration for Spring Security Form login</h2>
<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <c:choose>
  		<c:when test="${empty username}">
  			<h3 style="color: #38b5bb;">Permission is required for Accessing.</h3>
  		</c:when>
  		<c:otherwise>
  			<h3 style="color: #38b5bb;">Hi <span>${username}<span>, permission is required for Accessing.</h3>
  		</c:otherwise>
  	</c:choose>
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>

</body>
</html>