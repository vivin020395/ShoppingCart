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
<title>Registration Page</title>
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form name="registration" action="newUser" method="post">
				<c:if test="${idAlreadyExists=='true'}">
					<p>
						<font color="red">User Id Already exists.Choose a different
							Id</font>
					</p>
				</c:if>
				<input type="text" placeholder="name" name="name"
					pattern="[A-Za-z]{5,15}"
					title="Username must have 5 to 15 characters and no numbers"
					required /> <input type="text" placeholder="user id" name="userid"
					pattern=".{5,15}" title="Userid must have 5 to 15 characters"
					required /><input type="text" placeholder="email address"
					name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
					title="Email format must be in abc@xyz.com" required /> <input
					type="password" placeholder="password" name="password"
					pattern=".{5,15}" title="password must have 5 to 15 characters"
					required /> <input type="text" placeholder="Phone number"
					name="contact" pattern="[0-9][0-9]{9}"
					title="Mobile number should have 10 digits" required />
				<button type="submit">create</button>
				<p class="message">
					Already registered? <a href="LoginPage">Sign In</a>
				</p>
				<a href="HomePage" class="btn btn-back">
					<div class="fa fa-arrow-circle-o-left"></div> <i>&nbspBack</i>
				</a> <input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</div>


</body>
</html>