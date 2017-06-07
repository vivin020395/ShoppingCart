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
			<div class="row">
				<h4>
					Account Settings <a href="#" class="btn btn-info"
						data-toggle="modal" data-target="#myModal"> Edit</a>
				</h4>
				<font color="green"><center>
						<c:out value="${message}"></c:out>
					</center></font>
				<div id="myModal" class="modal fade" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit User</h4>
							</div>
							<div class="modal-body">
								<form action="updateUser" class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">Name:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="name"
												placeholder="Name" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Email:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="email"
												placeholder="email" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Mobile:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="contact"
												placeholder="Mobile" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Password:</label>
										<div class="col-sm-10">
											<input type="password" class="form-control" name="password"
												placeholder="Password" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-warning">
												<i class="fa fa-user-plus" aria-hidden="true"></i> Update
												User
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
				<table class="table table-striped table-inverse">
					<tr>
						<td>Name</td>
						<td><c:out value="${userName}"></c:out></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><c:out value="${userEmail}"></c:out></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><c:out value="${userPass}"></c:out></td>
					</tr>
					<tr>
						<td>PhoneNumber</td>
						<td><c:out value="${userContact}"></c:out></td>
					</tr>
				</table>

				<h3>
					Manage Address <a href="#" class="btn btn-info" data-toggle="modal"
						data-target="#addAddress">Add new Address</a>
				</h3>
				<font color="green"><center>
						<c:out value="${message2}"></c:out>
					</center></font>

				<div id="addAddress" class="modal fade" role="dialog">
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
										<label class="control-label col-sm-2">Address Line 1:</label>
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

				<c:forEach var="address" items="${addressList}">
					<div class="col-md-4">
						<div class="list-group">
							<a href="#" class="list-group-item disabled">
								<h4>Registered Address</h4>
							</a> <a href="#" class="list-group-item" data-toggle="modal"
								data-target="#editAddress"><h3>
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
						<a href="deleteAddress/${address.id}" class="btn btn-warning">Delete</a>
					</div>
				</c:forEach>
				<div id="editAddress" class="modal fade" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Edit Address</h4>
							</div>
							<div class="modal-body">
								<form action="editAddress/${address.id}" class="form-horizontal"
									method="post">
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
										<label class="control-label col-sm-2">Address Line 1:</label>
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
												<i class="fa fa-user-plus" aria-hidden="true"></i> Update
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
			</div>
		</div>
	</div>
</body>
</html>