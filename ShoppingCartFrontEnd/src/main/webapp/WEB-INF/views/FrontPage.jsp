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
<title>Computer World</title>
</head>
<body>
	<sql:setDataSource var="myDS" driver="org.h2.Driver"
		url="jdbc:h2:tcp://localhost/~/MyDB" user="sa" password="" />

	<sql:query var="listProducts" dataSource="${myDS}">
        SELECT * FROM product where category_id = 6;
    </sql:query>
	<sql:query var="listProducts2" dataSource="${myDS}">
        SELECT * FROM product where category_id = 3;
    </sql:query>
	<sql:query var="listProducts3" dataSource="${myDS}">
        SELECT * FROM product where category_id = 4;
    </sql:query>

	<div class="container">
		<!-- carousel start-->
		<div id="carousel1" class="carousel slide" data-ride="carousel">

			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<img src="resources/images/slider/andromeda.jpg"
						class="center-block">
				</div>
				<div class="item">
					<img src="resources/images/slider/gf980tilight_01.jpg"
						alt="Second slide image" class="center-block">
				</div>
				<div class="item">
					<img src="resources/images/slider/intel i7.jpg"
						alt="Third slide image" class="center-block">
				</div>
				<div class="item">
					<img src="resources/images/slider/msiMOBO.jpg"
						alt="Third slide image" class="center-block">
				</div>
				<div class="item">
					<img src="resources/images/slider/msiMoBODeal.png"
						alt="Third slide image" class="center-block">
				</div>
				<div class="item">
					<img src="resources/images/slider/msiNOBOUK.jpg"
						alt="Third slide image" class="center-block">
				</div>
				<div class="item">
					<img src="resources/images/slider/nVidia.jpg"
						alt="Third slide image" class="center-block">
				</div>
				<div class="item">
					<img src="resources/images/slider/ripJaws.jpg"
						alt="Third slide image" class="center-block">
				</div>
				<div class="item">
					<img src="resources/images/slider/samsungEvo.jpg"
						alt="Third slide image" class="center-block">
				</div>
			</div>
			<a class="left carousel-control" href="#carousel1" role="button"
				data-slide="prev"><span class="glyphicon glyphicon-chevron-left"
				aria-hidden="true"></span><span class="sr-only">Previous</span></a><a
				class="right carousel-control" href="#carousel1" role="button"
				data-slide="next"><span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span><span
				class="sr-only">Next</span></a>
		</div>
		<!-- carousel end-->
	</div>

	<div class="cw-content">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="list-group">
						<a href="#" class="list-group-item disabled">
							<h4>Graphics Card</h4>
						</a>
						<c:forEach var="products" items="${listProducts.rows}">
							<a href="viewProduct/${products.id}" class="list-group-item">${products.name}<span
								class="badge">${products.stock}</span></a>
						</c:forEach>
					</div>

					<div class="list-group">
						<a href="#" class="list-group-item disabled">
							<h4>CPU</h4>
						</a>
						<c:forEach var="products2" items="${listProducts2.rows}">
							<a href="viewProduct/${products2.id}" class="list-group-item">${products2.name}<span
								class="badge">${products2.stock}</span></a>
						</c:forEach>
					</div>

					<div class="list-group">
						<a href="#" class="list-group-item disabled">
							<h4>Motherboard</h4>
						</a>
						<c:forEach var="products3" items="${listProducts3.rows}">
							<a href="viewProduct/${products3.id}" class="list-group-item">${products3.name}<span
								class="badge">${products3.stock}</span></a>
						</c:forEach>
					</div>

				</div>
				<div class="col-md-8">
					<div class="col-md-4">
						<img src="resources/images/featured/12.jpg"
							"
							class="sc-item-img img-responsive" />
						<h5>
							<p class="text-center">Gigabyte Geforce GTX 1080</p>
						</h5>
						<div class="text-center">
							<a href="VideoCardPage">Go to category</a>
						</div>

					</div>
					<div class="col-md-4">
						<img src="resources/images/featured/13.jpg"
							"
							alt="Image could not be loaded"
							class="sc-item-img img-responsive" />
						<h5>
							<p class="text-center">Gigabyte Geforce GTX 1070</p>
						</h5>
						<div class="text-center">
							<a href="VideoCardPage">Go to category</a>
						</div>
					</div>
					<div class="col-md-4">
						<img src="resources/images/featured/14.jpg"
							"
							class="sc-item-img img-responsive" />
						<h5>
							<p class="text-center">Gigabyte Geforce GTX 1060</p>
						</h5>
						<div class="text-center">
							<a href="VideoCardPage">Go to category</a>
						</div>
					</div>
					<div class="col-md-4">
						<img src="resources/images/featured/15.jpg"
							"
							class="sc-item-img img-responsive" />
						<h5>
							<p class="text-center">Gigabyte Geforce GTX Titan Z</p>
						</h5>
						<div class="text-center">
							<a href="VideoCardPage">Go to category</a>
						</div>
					</div>
					<div class="col-md-4">
						<img src="resources/images/featured/1.jpg"
							class="sc-item-img img-responsive" />
						<h5>
							<p class="text-center">Intel 7th Gen Intel Core i7-7700K</p>
						</h5>
						<div class="text-center">
							<a href="CPUPage">Go to category</a>
						</div>
					</div>
					<div class="col-md-4">
						<img src="resources/images/featured/2.jpg"
							"
							class="sc-item-img img-responsive" />
						<h5>
							<p class="text-center">Intel 7th Gen Intel Core i5-4690K</p>
						</h5>
						<div class="text-center">
							<a href="CPUPage">Go to category</a>
						</div>
					</div>
					<div class="col-md-4">
						<img src="resources/images/featured/3.jpg"
							"
							class="sc-item-img img-responsive" />
						<h5>
							<p class="text-center">AMD Ryzen 7</p>
						</h5>
						<div class="text-center">
							<a href="CPUPage">Go to category</a>
						</div>
					</div>
					<div class="col-md-4">
						<img src="resources/images/featured/4.jpg"
							class="sc-item-img img-responsive" />
						<h5>
							<p class="text-center">AMD Ryzen 5</p>
						</h5>
						<div class="text-center">
							<a href="CPUPage">Go to category</a>
						</div>
					</div>
					<div class="col-md-4">
						<img src="resources/images/featured/22.jpg"
							class="sc-item-img img-responsive" />
						<h5>
							<p class="text-center">Patriot Viper 2x8 GB DDR4</p>
						</h5>
						<div class="text-center">
							<a href="MemoryPage">Go to category</a>
						</div>
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