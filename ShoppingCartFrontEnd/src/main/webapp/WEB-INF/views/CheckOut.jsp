<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://use.fontawesome.com/e1fbaa6604.js"></script>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Checkout</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#topFixedNavbar1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
					class="icon-bar"></span><span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><span class="title">COMPUTER
					ACCESSORIES</span></a>
		</div>

		<div class="collapse navbar-collapse" id="topFixedNavbar1">
			<ul class="nav navbar-nav">

				<li class="active"><a href="HomePage">Home<span
						class="sr-only">(current)</span></a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container">
		<div class="h2 text-success text-center">Thank You!</div>
		<div class="h2 text-center">Your order has been placed.</div>
		<div class="h2 text-center">
			The product will be delivered in
			<c:out value="${deliveryDays}" />
			business days.
		</div>
		<img src="<c:url value="/resources/images/gifs/teemoThankYou.gif"/>"
			alt="Thank You GIF" class="img-responsive center-block" /> <a
			href="HomePage"><i class="fa fa-home-centre" aria-hidden="true"></i></a>
	</div>
</body>
</html>