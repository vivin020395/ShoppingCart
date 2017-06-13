<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://use.fontawesome.com/e1fbaa6604.js"></script>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Checkout</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<div class="cw-content">
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
	</div>
</body>
</html>