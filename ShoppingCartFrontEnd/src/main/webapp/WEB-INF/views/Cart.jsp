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
	<div class="container">
		<h3>
			<i>My Cart</i>
		</h3>
		<h4>
			<center>
				<u> <c:if test="${isCartEmpty=='true'}">
						<c:out value="${emptyCart}" />
					</c:if></u>
			</center>
		</h4>
		<c:if test="${isQuantityOne=='true'}">
			<c:out value="${productMessage}"></c:out>
		</c:if>
		<table id="cart" class="table table-hover table-condensed">
			<thead>
				<tr>
					<th style="width: 50%">Product</th>
					<th style="width: 10%">Price</th>
					<th style="width: 8%">Quantity</th>
					<th style="width: 22%" class="text-center">Subtotal</th>
					<th style="width: 10%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="products" items="${cartList}">
					<tr>
						<td data-th="Product">
							<div class="row">
								<div class="col-sm-2 hidden-xs">
									<img width="70" height="70"
										src="resources/images/featured/
								${products.productId}.jpg"
										class="sc-item-img img-responsive" />
								</div>
								<div class="col-sm-10">
									<c:out value="${products.name}" />
									<br>
									<p>
										Date added :
										<c:out value="${products.date}" />
									</p>

								</div>
							</div>
						</td>
						<td><c:out value="Rs.${products.price}" /></td>
						<td data-th="Quantity"><a
							href="myCart/addQuantity/${products.id}"
							class="btn btn-info btn-sm"> <i class="fa fa-plus"></i>
						</a> <b><c:out value="${products.quantity}" /></b> <a
							href="myCart/reduceQuantity/${products.id}"
							class="btn btn-warning btn-sm"> <i class="fa fa-minus"></i>
						</a></td>
						<td data-th="Subtotal" class="text-center"><c:out
								value="Rs.${products.price*products.quantity}" /></td>
						<td class="actions" data-th=""><a
							href="myCart/delete/${products.id}" class="btn btn-danger btn-sm">
								<i class="fa fa-trash-o"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr class="visible-xs">
					<td data-th="Total class="text-center"><strong>Total
							Rs.${totalAmount}</strong></td>
				</tr>
				<tr>
					<td><a href="HomePage" class="btn btn-warning"><i
							class="fa fa-angle-left"></i> Continue Shopping</a></td>
					<td colspan="2" class="hidden-xs"></td>
					<td class="hidden-xs text-center"><strong>Total
							Rs.${totalAmount}</strong></td>
					<td><a href="addressInfo" class="btn btn-success btn-block">Next
							<i class="fa fa-angle-right"></i>
					</a></td>
				</tr>
			</tfoot>
		</table>
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
