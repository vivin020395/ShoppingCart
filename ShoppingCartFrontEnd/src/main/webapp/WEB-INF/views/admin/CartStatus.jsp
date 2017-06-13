<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="cw-content">
			<c:out value="${message}"></c:out>
			<table id="cart" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th style="width: 40%">Product</th>
						<th style="width: 10%">User</th>
						<th style="width: 10%">Price</th>
						<th style="width: 8%">Quantity</th>
						<th style="width: 22%" class="text-center">Subtotal</th>
						<th style="width: 10%">Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="products" items="${cartList}">
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs">
										<img width="70" height="70"
											src="resources/images/featured/2.jpg"
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
							<td><c:out value="${products.userId}" /></td>
							<td><c:out value="Rs.${products.price}" /></td>
							<td data-th="Quantity"><c:out value="${products.quantity}" /></td>
							<td data-th="Subtotal" class="text-center"><c:out
									value="Rs.${products.price*products.quantity}" /></td>
							<c:if test="${status==P}">
								<td class="actions" data-th="">Order Completed</td>
							</c:if>
							<c:if test="${status==N}">
								<td class="actions" data-th="">In Cart</td>
							</c:if>

						</tr>
					</c:forEach>
				</tbody>

				<tfoot>
					<tr class="visible-xs">
						<td data-th="Total class="text-center"><strong>Total
								Rs.${totalAmount}</strong></td>
					</tr>
				</tfoot>
			</table>


		</div>
	</div>

</body>
</html>