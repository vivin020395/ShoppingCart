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
				<div class="col-xs-4 item-photo">
					<img style="max-width: 100%;"
						src="resources/images/featured/
								${selectedProduct.id}.jpg" />
				</div>
				<div class="col-xs-5" style="border: 0px solid gray">
					<h3>
						vendor: <b><i>${supplierName}</i></b>
					</h3>
					<h3>${selectedProduct.name}</h3>
					<h6 class="title-price">
						<small>Price</small>

					</h6>
					<h3 style="margin-top: 0px;">RS. ${selectedProduct.price}</h3>



					<div class="section" style="padding-bottom: 5px;">
						<h6 class="title-attr">
							<small>Description</small>
						</h6>
						<div>
							<div class="attr1">${selectedProduct.description}</div>

						</div>
					</div>

					<div class="section" style="padding-bottom: 20px;">
						<c:if test="${isNoStock=='true'}">
							<c:out value="${productNotAvailable}"></c:out>
						</c:if>
						<c:if test="${isStock=='true'}">
							<a href="myCart/add/${selectedProduct.id}">
								<button class="btn btn-success">
									<span style="margin-right: 20px"
										class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
									Add to Cart
								</button>
							</a>
						</c:if>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
