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
<body style="padding-top: 50px">
	<div class="cw-content">
		<div class="container">
			<div class="collapse" id="demo3"></div>
			<div id="MainMenu">
				<a href="#cat7" class="list-group-item list-group-item-success"
					data-toggle="collapse" data-parent="#MainMenu2"><center>
						Click here to choose Payment Option</center></a>
				<div class="collapse" id="cat7">
					<a href="#SubMenu1" class="list-group-item" data-toggle="collapse"
						data-parent="#SubMenu1"><center>Credit/Debit Card</center> </a>
					<div class="collapse list-group-submenu" id="SubMenu1">
						<div class="panel panel-default credit-card-box">
							<div class="panel-heading display-table">
								<div class="row display-tr">
									<h3 class="panel-title display-td">
										<center>Enter card details</center>
									</h3>
									<div class="display-td">
										<img class="img-responsive pull-right"
											src="http://i76.imgup.net/accepted_c22e0.png">
									</div>
								</div>
							</div>
							<div class="panel-body">
								<form role="form" id="payment-form" method="post"
									action="checkout/${selectedAddress.id}">
									<div class="row">
										<div class="col-xs-12">
											<div class="form-group">
												<label for="cardNumber">CARD NUMBER</label>
												<div class="input-group">
													<input type="tel" class="form-control" name="cardNumber"
														placeholder="Valid Card Number" autocomplete="cc-number"
														pattern="[0-9][0-9]{6}"
														title="Card number should have 7 digits" required
														autofocus /> <span class="input-group-addon"><i
														class="fa fa-credit-card"></i></span>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-7 col-md-7">
											<div class="form-group">
												<label for="cardExpiry"><span class="hidden-xs">EXPIRATION</span><span
													class="visible-xs-inline">EXP</span> DATE</label> <input type="tel"
													class="form-control" name="cardExpiry"
													placeholder="MM/YYYY" autocomplete="cc-exp"
													pattern="(0[1-9]|1[012])[- /.](20)\d\d"
													title="Date invalid" required />
											</div>
										</div>
										<div class="col-xs-5 col-md-5 pull-right">
											<div class="form-group">
												<label for="cardCVC">CVV</label> <input type="tel"
													class="form-control" name="cardCVC" placeholder="CVC"
													autocomplete="cc-csc" pattern="[0-9][0-9]{2,3}"
													title="CVV must be 3 or 4 digits" required />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-12">
											<button class="subscribe btn btn-success btn-lg btn-block"
												type="submit">Checkout</button>
										</div>
									</div>
									<div class="row" style="display: none;">
										<div class="col-xs-12">
											<p class="payment-errors"></p>
										</div>
									</div>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>

							</div>

						</div>
					</div>
					<a href="" class="list-group-item" data-toggle="modal"
						data-target="#myModal"><center>Cash on Delivery</center></a>
				</div>
			</div>
		</div>
		<div class="row">
			<div id="myModal" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Cash on Delivery</h4>
						</div>
						<div class="modal-body">
							<h4>Please have exact change during the delivery.</h4>
							<h3>
								<i>Signature needed during the delivery.</i>
							</h3>
						</div>
						<div class="modal-footer">
							<a href="checkout/${selectedAddress.id}" class="btn btn-info ">Checkout</a>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
