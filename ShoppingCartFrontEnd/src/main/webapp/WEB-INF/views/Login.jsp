<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="https://use.fontawesome.com/e1fbaa6604.js"></script>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<title>Login Page</title>
</head>
<body>
	<div class="login-page">
		<div class="form">
			<c:url var="action" value="/j_spring_security_check" />
			<form action="${action}" method="post">
				<form id="login-form">
					<input type="text" placeholder="UserId" name="userid"
						pattern=".{5,15}" title="Username must have 5 to 15 characters"
						required /> <input type="password" placeholder="Password"
						name="pass" pattern=".{5,15}"
						title="Password must have 5 to 15 characters" required />
					<c:if test="${isUserLoginError=='true'}">
						<c:out value="${isLoginError}" />
					</c:if>
					<button>login</button>
					<p class="message">
						Not registered? <a href="RegistrationPage">Create an account</a>
					</p>
					<a href="HomePage" class="btn btn-back">
						<div class="fa fa-arrow-circle-o-left"></div> <i>&nbspBack</i>
					</a> <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</form>
		</div>
	</div>
	<script src="resources/js/loginRegister.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<c:if test="${isRegistered=='true' }">
		<p>Registered Successfully</p>
	</c:if>
</body>
</html>