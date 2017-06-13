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
<body style="padding-top: 50px">
	<div class="cw-content">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div id="MainMenu">
						<div class="list-group panel">
							<a href="#cat3" class="list-group-item list-group-item-success"
								data-toggle="collapse" data-parent="#MainMenu">${CPULIST.name}</a>
							<div class="collapse" id="cat3">
								<c:forEach var="products" items="${productList}">
									<a href="viewProduct/${products.id}" class="list-group-item">${products.name}<span
										class="badge">${products.stock}</span></a>
								</c:forEach>
							</div>
							<a href="#cat4" class="list-group-item list-group-item-success"
								data-toggle="collapse" data-parent="#MainMenu2">${MOTHERBOARDLIST.name}</a>
							<div class="collapse" id="cat4">
								<c:forEach var="products" items="${productList2}">
									<a href="viewProduct/${products.id}" class="list-group-item">${products.name}<span
										class="badge">${products.stock}</span></a>
								</c:forEach>
							</div>
							<a href="#cat5" class="list-group-item list-group-item-success"
								data-toggle="collapse" data-parent="#MainMenu2">${MEMORYLIST.name}</a>
							<div class="collapse" id="cat5">
								<c:forEach var="products" items="${productList3}">
									<a href="viewProduct/${products.id}" class="list-group-item">${products.name}<span
										class="badge">${products.stock}</span></a>
								</c:forEach>
							</div>
							<a href="#cat6" class="list-group-item list-group-item-success"
								data-toggle="collapse" data-parent="#MainMenu2">${VIDEOCARDLIST.name}</a>
							<div class="collapse" id="cat6">
								<c:forEach var="products" items="${productList4}">
									<a href="viewProduct/${products.id}" class="list-group-item">${products.name}<span
										class="badge">${products.stock}</span></a>
								</c:forEach>
							</div>
							<a href="#cat7" class="list-group-item list-group-item-success"
								data-toggle="collapse" data-parent="#MainMenu2">${STORAGELIST.name}</a>
							<div class="collapse" id="cat7">
								<c:forEach var="products" items="${productList5}">
									<a href="viewProduct/${products.id}" class="list-group-item">${products.name}<span
										class="badge">${products.stock}</span></a>
								</c:forEach>
							</div>
						</div>
					</div>

				</div>

				<div class="col-md-7">

					<!-- c for each with image and product name price -->
					<c:forEach var="products" items="${productList}">
						<div class="col-md-4">
							<a href="viewProduct/${products.id}"> <img
								src="resources/images/featured/
								${products.id}.jpg"
								alt="${products.name}" class="sc-item-img img-responsive" /></a>
							<h5>
								<p class="text-center">
									<c:out value="${products.name}" />
								</p>
							</h5>
							<div class="text-center">
								<c:out value="Rs.${products.price}" />
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="col-md-1">
					<select name="sortBy">
						<option selected disabled>Sort by price</option>
						<option value="low">Low to High</option>
						<option value="high">High to Low</option>
					</select>
				</div>
			</div>
		</div>
	</div>

	<!--cw-content end-->
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
	<!--footer end-->
</body>
</html>
