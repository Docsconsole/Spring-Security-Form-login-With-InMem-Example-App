<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body onload='document.loginForm.username.focus();'>

<h2 style="color: #38b5bb;">Demonstration for Spring Security Form login</h2>

	<c:if test="${not empty error}"><div style="color: #38b5bb;">${error}</div></c:if>
	<c:if test="${not empty message}"><div style="color: #38b5bb;">${message}</div></c:if>

	<form name='login' action="<c:url value='/login' />" method='POST'>
		<table>
			<tr>
				<td>UserName:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>