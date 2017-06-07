<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="https://use.fontawesome.com/e1fbaa6604.js"></script>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<title>ComputerWorld</title>
</head>
<body>
	<div class="cw-content">
		<div class="container">
			<h3>
				<i>Select an address </i> - <a href="accountInfo"
					class="btn btn-primary">Click to add a new address</a>
			</h3>

			<c:if test="${noAddressForUser=='true'}">
				<h3>
					<font color="red">You have No address added.</font> <a
						href="accountInfo">Click to add address.</a>
				</h3>
			</c:if>
			<div class="row">
				<c:forEach var="address" items="${addressList}">
					<div class="col-md-4">
						<div class="list-group">
							<a href="paymentInfo" class="list-group-item disabled">
								<h4>Shipping Address</h4>
							</a> <a href="paymentInfo" class="list-group-item"><h3>
									<c:out value="${address.name}" />
								</h3>
								<h4>
									<c:out value="${address.addressLine1}" />
								</h4>
								<h4>
									<c:out value="${address.addressLine2}" />
								</h4>
								<h4>
									<c:out value="${address.city}" />
									,
									<c:out value="${address.state}" />
								</h4>
								<h4>
									<c:out value="${address.zipCode}" />
								</h4>
								<h4>
									<c:out value="${address.country}" />
								</h4>
								<h5>
									<c:out value="Phone:${address.contact}" />
								</h5></a>
						</div>
					</div>
				</c:forEach>
			</div>
			<c:if test="${showInfo=='true'}">
				<h5>Your invoice will be sent to the registered email. A hard
					copy of the invoice will be included in the package.</h5>
			</c:if>
		</div>
	</div>
</body>
</html>