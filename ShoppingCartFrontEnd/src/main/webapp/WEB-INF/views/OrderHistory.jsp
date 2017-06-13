<!Doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Product Name</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>Date Purchased</th>
								<th>Address</th>
								<th>Progress</th>
								<th>Order Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="products" items="${cartList}">
								<tr>
									<td><c:out value="${products.name}" /></td>
									<td>${products.quantity}</td>
									<td>Rs.${products.price}</td>
									<td>${OrderDate}</td>
									<td>${products.address}</td>
									<td><c:if test="${products.days>0}">
											<div class="progress">
												<div class="progress-bar progress-bar-info"
													role="progressbar" aria-valuenow="70" aria-valuemin="0"
													aria-valuemax="100" style="width: 50%"></div>
											</div>
										</c:if> <c:if test="${products.days==0}">
											<div class="progress">
												<div class="progress-bar progress-bar-success"
													role="progressbar" aria-valuenow="70" aria-valuemin="0"
													aria-valuemax="100" style="width: 100%"></div>
											</div>
										</c:if></td>


									<td><c:if test="${products.days==0}">
											<span class="label label-info">Completed</span>
										</c:if> <c:if test="${products.days>0}">
											<span class="label label-warning">Delivery in
												${products.days} <c:if test="${products.days==1}">day</c:if>
												<c:if test="${products.days>1}">days</c:if>
											</span>
										</c:if></td>
								</tr>

							</c:forEach>
						</tbody>

					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>