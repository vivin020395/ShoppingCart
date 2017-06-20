<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://use.fontawesome.com/e1fbaa6604.js"></script>

<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>

	<div class="container">
	<h2>Add/Delete/Update Category</h2>
		<div id="UpdateCategory">
			<form class="form-horizontal" action="manage_category_update"
				method="post">
				<div class="form-group">
					<label class="control-label col-sm-3" for="id">Category
						Name:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="name"
							placeholder="Enter Category Name" name="name"
							value="${selectedCategory.name}" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="id">Category
						Description:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="description"
							placeholder="Enter Category Description" name="description"
							value="${selectedCategory.description}" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-info">Update
							Category</button>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

		</div>
		<font color="red"><c:out value="${categoryMessage}" /></font>
		<h2>Add/Delete/Update Categories</h2>
		<button type="button" class="btn btn-success btn-lg"
			data-toggle="modal" data-target="#createCategory">Add
			Category</button>
		<div id="ShowCategories">
			<table class="table table-striped">
				<thead>
					<tr>
						<td>Category ID</td>
						<td>Category Name</td>
						<td>Category Description</td>
						<td>Action</td>
					</tr>
				</thead>
				<c:forEach var="category" items="${categoryList}">
					<tr>
						<td>${category.id}</td>
						<td>${category.name}</td>
						<td>${category.description}</td>
						<td><a href="manage_category_edit/${category.id}"
							class="btn btn-info"> Edit</a> <a
							href="manage_category_delete/${category.id}"
							class="btn btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<!-- Create Category -->
		<div id="createCategory" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Add Categories</h4>
					</div>
					<div class="modal-body">
						<div id="CreateCategory">
							<form class="form-horizontal" action="manage_category_add"
								method="post">
								<div class="form-group">
									<label class="control-label col-sm-3" for="name">Category
										Name:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="name"
											placeholder="Enter Category Name" name="name" required>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="description">Category
										Description:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="description"
											placeholder="Enter Category description" name="description"
											required>
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
</html>
















