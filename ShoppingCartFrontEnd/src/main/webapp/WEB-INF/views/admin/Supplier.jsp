<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://use.fontawesome.com/e1fbaa6604.js"></script>

<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<title>Supplier Manager</title>
</head>
<body>

	<div class="container">
		<font color="red"><c:out value="${supplierMessage}" /></font>
		<h2>Add/Delete/Update Suppliers</h2>
		<div id="UpdateSupplier">
			<form class="form-horizontal" action="manage_supplier_update"
				method="post">
				<div class="form-group">
					<label class="control-label col-sm-3" for="name">Supplier
						Name:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="name"
							placeholder="Enter Supplier Name" name="name"
							value="${selectedSupplier.name}" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="address">Supplier
						Address:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="address"
							placeholder="Enter Supplier Address" name="address"
							value="${selectedSupplier.address}" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="phone">Supplier
						Phone:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="phone"
							placeholder="Enter Supplier Phone" name="phone"
							value="${selectedSupplier.phone}" required>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-info">Update
							Supplier</button>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
		<button type="button" class="btn btn-success btn-lg"
			data-toggle="modal" data-target="#createSupplier">Add
			Supplier</button>
		<div id="ShowSupplier">
			<table class="table table-striped">
				<thead>
					<tr>
						<td>Supplier Id</td>
						<td>Supplier Name</td>
						<td>Supplier Address</td>
						<td>Supplier Phone</td>
						<td>Action</td>
					</tr>
				</thead>
				<c:forEach var="supplier" items="${supplierList}">
					<tr>
						<td>${supplier.id}</td>
						<td>${supplier.name}</td>
						<td>${supplier.address}</td>
						<td>${supplier.phone}</td>
						<td><a href="manage-supplier-edit/${supplier.id}"
							class="btn btn-info""> Edit</a>&nbsp<a
							href="manage-supplier-delete/${supplier.id}"
							class="btn btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div id="createSupplier" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Add Supplier</h4>
					</div>
					<div class="modal-body">

						<div id="CreateSupplier">
							<form class="form-horizontal" action="manage_supplier_add">
								<div class="form-group">
									<label class="control-label col-sm-3" for="name">Supplier
										Name:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="name"
											placeholder="Enter Supplier Name" name="name" required>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="address">Supplier
										Address:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="address"
											placeholder="Enter Supplier Address" name="address" required>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="phone">Supplier
										Phone:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="phone"
											placeholder="Enter Phone" name="phone" required>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-9">
										<button type="submit" class="btn btn-default">Submit</button>
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>

	<div class="footer">
		<div class="container">
			<div class="col-md-12">
				<p class="text-center">
					Get to Know Us<br> Careers<br> About Computer World<br>
					Investor Relations<br> Make Money with Us<br> Contact Us<br>
				</p>
			</div>

		</div>
	</div>

</body>
</body>
</html>