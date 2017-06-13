<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>
</head>
<body>

	<div class="container">
		<h2>Add/Delete/Update Products</h2>
		<div id="updateProduct">
			<form class="form-horizontal" action="manage_product_update"
				method="post">
				<div class="form-group">
					<label class="control-label col-sm-3" for="name">Product
						Name:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="name"
							placeholder="Enter Product Name" name="name"
							value="${selectedProduct.name}" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="description">Product
						description:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="description"
							placeholder="Enter Product description" name="description"
							value="${selectedProduct.description}" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="price">Product
						price:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="price"
							placeholder="Enter price" name="price"
							value="${selectedProduct.price}" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="stock">Product
						Stock:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="stock"
							placeholder="Enter stock" name="stock"
							value="${selectedProduct.stock}" required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="description">Product
						Category:</label>
					<div class="col-sm-9">
						<select name="category">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id}">${category.id}-${category.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="description">Product
						Supplier:</label>
					<div class="col-sm-9">
						<select name="supplier">
							<c:forEach var="supplier" items="${supplierList}">
								<option value="${supplier.id}">${supplier.id}-${supplier.name}</option>
							</c:forEach>
						</select>
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
		<button type="button" class="btn btn-success btn-lg"
			data-toggle="modal" data-target="#createProduct">Add Product</button>
		<div align="center">
			<table class="table table-striped">
				<tr>
					<th>ID</th>
					<th>Image</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price(Rs)</th>
					<th>Stock</th>
					<th>Action</th>
				</tr>
				<c:forEach var="product" items="${productList}">
					<tr>
						<td><c:out value="${product.id}" /></td>
						<td><img width="70" height="70"
							src="resources/images/featured/
								${product.id}.jpg"
							class="sc-item-img img-responsive" /></td>
						<td><c:out value="${product.name}" /></td>
						<td><c:out value="${product.description}" /></td>
						<td><c:out value="Rs.${product.price}" /></td>
						<td><c:out value="${product.stock} Units" /></td>
						<td><a href="manage_product_edit/${product.id}"
							class="btn btn-info"> Edit</a> <a class="btn btn-danger"
							href="manage_product_delete/${product.id}">Delete </a></td>
					</tr>
				</c:forEach>
			</table>
		</div>


		<c:url var="addAction"
			value="/manage-product-add?${_csrf.parameterName}=${_csrf.token}"></c:url>
		<div id="createProduct" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Add Product</h4>
					</div>
					<div class="modal-body">
						<div id="CreateProduct">
							<form class="form-horizontal" action="${addAction}" method="post"
								enctype="multipart/form-data">
								<div class="form-group">
									<label class="control-label col-sm-3" for="name">Product
										Name:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="name"
											placeholder="Enter Product Name" name="name" required>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="description">Product
										Descritpion:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="description"
											placeholder="Enter Product Descritpion" name="description"
											required>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="description">Product
										Category:</label>
									<div class="col-sm-9">
										<select name="category">
											<c:forEach var="category" items="${categoryList}">
												<option value="${category.id}">${category.id}-${category.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="description">Product
										Supplier:</label>
									<div class="col-sm-9">
										<select name="supplier">
											<c:forEach var="supplier" items="${supplierList}">
												<option value="${supplier.id}">${supplier.id}-${supplier.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="price">Product
										Price:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="price"
											placeholder="Enter Price" name="price" required>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="stock">Product
										Stock:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="stock"
											placeholder="Enter stock" name="stock" required>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="price">Product
										Image:</label>
									<div class="col-sm-9">
										<input type="file" name="image" /><input type="hidden"
											name="${_csrf.parameterName}" value="${_csrf.token}" />
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