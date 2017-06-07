<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="https://use.fontawesome.com/e1fbaa6604.js"></script>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>
</head>
<body>

	<div class="cw-content">
		<div class="container">
			<div align="center">
			<%-- 	<font color="blue"><h2>Viewing all Products</h2></font>
				<div class="col-md-12">
					<!-- c for each with image and product name price -->
					<c:forEach var="product" items="${productList}">
						<div class="col-md-2">
							<a href="viewProduct/${product.id}"> <img width="100"
								height="100"
								src="resources/images/featured/
								${product.id}.jpg"
								alt="${product.name}" class="sc-item-img img-responsive" /></a>
							<h5>
								<p class="text-center">
									<c:out value="${product.name}" />
								</p>
							</h5>
							<div class="text-center">
								<c:out value="Rs.${product.price}" />
							</div>
						</div>
					</c:forEach>
				</div> --%>
				<table class="table table-striped">
					<caption>
						<h2>Viewing all Products</h2>
					</caption>
					<tr>
						<th>Image</th>
						<th>Name</th>
						<th>Description</th>
						<th>Price(Rs)</th>
						<th>Action</th>
					</tr>
					<c:forEach var="product" items="${productList}">
						<tr>
							<td><img width="70" height="70"
								src="resources/images/featured/
								${product.id}.jpg"
								class="sc-item-img img-responsive" /></td>
							<td><c:out value="${product.name}" /><br> <b>Stock:${product.stock}</b></td>
							<td><c:out value="${product.description}" /></td>
							<td><c:out value="Rs.${product.price}" /></td>
							<td><a href="myCart/add/${product.id}"><i
									class="fa fa-shopping-cart"></i></a></td>
						</tr>
					</c:forEach>
				</table>
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