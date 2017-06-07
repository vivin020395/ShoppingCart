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
	<sql:setDataSource var="myDS" driver="org.h2.Driver"
		url="jdbc:h2:tcp://localhost/~/MyDB" user="sa" password="" />

	<sql:query var="listProducts" dataSource="${myDS}">
        SELECT * FROM product where category_id = 5;
    </sql:query>
	<sql:query var="listProducts2" dataSource="${myDS}">
        SELECT * FROM product where category_id = 4;
    </sql:query>
	<sql:query var="listProducts3" dataSource="${myDS}">
        SELECT * FROM product where category_id = 6;
    </sql:query>

	<div class="cw-content">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
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
							<h4>Video Card</h4>
						</a>
						<c:forEach var="products3" items="${listProducts3.rows}">
							<a href="viewProduct/${products3.id}" class="list-group-item">${products3.name}<span
								class="badge">${products3.stock}</span></a>
						</c:forEach>
					</div>
				</div>

				<div class="col-md-8">
					<!-- c for each with image and product name price -->
					<c:forEach var="products" items="${listProducts.rows}">
						<div class="col-md-4">
							<a href="viewProduct/${products.id}"> <img
								src="resources/images/featured/
								${products.id}.jpg"
								alt="${products.name}" class="sc-item-img img-responsive" />
							</a>
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
