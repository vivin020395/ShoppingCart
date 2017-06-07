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
	<sql:setDataSource var="myDS" driver="org.h2.Driver"
		url="jdbc:h2:tcp://localhost/~/MyDB" user="sa" password="" />

	<sql:query var="listProducts" dataSource="${myDS}">       
        SELECT * FROM MYCART where userid ='${user}' and status='P';
    </sql:query>
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
								<th>Email</th>
								<th>Address</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="products" items="${listProducts.rows}">
								<tr>
									<td><c:out value="${products.name}" /></td>
									<td>${products.quantity}</td>
									<td>Rs.${products.price}</td>
									<td>${userEmail}</td>
									<td>${products.address}</td>
									<td><span class="label label-info">Completed</span></td>
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