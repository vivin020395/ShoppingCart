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
				<i>Select an address </i> -
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#myModal">Click to add new address</button>
			</h3>
			<div id="myModal" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">New Address</h4>
						</div>
						<div class="modal-body">
							<form action="addAddress" class="form-horizontal" method="post">
								<div class="form-group">
									<label class="control-label col-sm-2">Name:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="name"
											placeholder="Name" required />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">Phone:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="contact"
											placeholder="Phone Number" required />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">Address Line 1:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="line1"
											placeholder="Address Line 1" required />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">Address Line 2:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="line2"
											placeholder="Address Line 2" required />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">City:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="city"
											placeholder="City" required />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">State:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="state"
											placeholder="State" required />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">Country:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="country"
											placeholder="Country" required />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">Zip Code:</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="zip"
											placeholder="ZipCode" required />
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-warning">
											<i class="fa fa-user-plus" aria-hidden="true"></i> Add
											Address
										</button>
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>
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
							</a> <a href="paymentInfo/${address.id}" class="list-group-item"><h3>
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