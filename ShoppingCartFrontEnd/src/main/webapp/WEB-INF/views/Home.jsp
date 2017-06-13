<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="https://use.fontawesome.com/e1fbaa6604.js"></script>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Computer World</title>
</head>
<body>
	<sql:setDataSource var="myDS" driver="org.h2.Driver"
		url="jdbc:h2:tcp://localhost/~/MyDB" user="sa" password="" />

	<sql:query var="listProducts" dataSource="${myDS}">
        SELECT * FROM Category;
    </sql:query>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#topFixedNavbar1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span><span
						class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="HomePage"><span class="title">COMPUTER
						ACCESSORIES</span></a>
			</div>

			<div class="collapse navbar-collapse" id="topFixedNavbar1">
				<c:choose>
					<c:when test="${isUserLoggedIn=='true'}">
						<ul class="nav navbar-nav">

							<li class="active"><a href="HomePage">Home<span
									class="sr-only">(current)</span></a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Categories<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<c:forEach var="products" items="${listProducts.rows}">
										<li><a href="<c:out value="${products.name}"/>Page"><c:out
													value="${products.name}" /></a></li>
									</c:forEach>
									<li role="separator" class="divider"></li>
									<li><a href="viewProduct">All Products</a></li>
								</ul></li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" style="width: 400px;" class="form-control"
									placeholder="Search...">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
							<a href="CartPage" class="btn btn-cart"><i
								class="fa fa-shopping-cart"></i></a>
						</form>
					</c:when>
					<c:when test="${isAdminLoggedIn=='true'}">
						<ul class="nav navbar-nav">

							<li class="active"><a href="checkRole">Home<span
									class="sr-only">(current)</span></a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Manage<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="manageCategories">Categories</a></li>
									<li><a href="manageSuppliers">Suppliers</a></li>
									<li><a href="manageProducts">Products</a></li>
								</ul></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="nav navbar-nav">

							<li class="active"><a href="HomePage">Home<span
									class="sr-only">(current)</span></a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Categories<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<c:forEach var="products" items="${listProducts.rows}">
										<li><a href="<c:out value="${products.name}" />Page"><c:out
													value="${products.name}" /></a></li>
									</c:forEach>
									<li role="separator" class="divider"></li>
									<li><a href="viewProduct">All Products</a></li>
								</ul></li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" style="width: 400px;" class="form-control"
									placeholder="Search...">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>

						</form>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${isUserLoggedIn=='true'}">
						<ul class="nav navbar-nav navbar-left">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Settings<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="accountInfo">My Account</a></li>
									<li><a href="OrderHistoryPage">Order History</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="secure_logout">Sign Out</a></li>
								</ul></li>
						</ul>
					</c:when>
					<c:when test="${isAdminLoggedIn=='true'}">
						<ul class="nav navbar-nav navbar-left">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Settings<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="manageUsers">Manage Users</a></li>
									<!-- <li><a href="status">Cart Status</a></li> -->
									<li role="separator" class="divider"></li>
									<li><a href="secure_logout">Sign Out</a></li>
								</ul></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="nav navbar-nav navbar-left">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">Settings<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="LoginPage">Sign In</a></li>
									<li><a href="RegistrationPage">Register</a></li>
								</ul></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<marquee>
				<font color="green">${message}</font>
			</marquee>
		</div>

	</nav>

	<c:if test="${isAdmin=='true'}">
		<jsp:include page="admin/AdminHome.jsp"></jsp:include>
	</c:if>

	<c:if test="${isAdminClickedCategories=='true' }">
		<jsp:include page="admin/Category.jsp"></jsp:include>
	</c:if>

	<c:if test="${isAdminClickedSuppliers=='true' }">
		<jsp:include page="admin/Supplier.jsp"></jsp:include>
	</c:if>

	<c:if test="${isAdminClickedProducts=='true' }">
		<jsp:include page="admin/Product.jsp"></jsp:include>
	</c:if>

	<c:if test="${isAdminClickedUsers=='true' }">
		<jsp:include page="admin/ManageUsers.jsp"></jsp:include>
	</c:if>

	<%-- <c:if test="${isAdminClickedCart=='true' }">
		<jsp:include page="admin/CartStatus.jsp"></jsp:include>
	</c:if> --%>

	<!-- Home -->
	<c:if test="${isUserClickedHomePage=='true'}">
		<jsp:include page="FrontPage.jsp"></jsp:include>
	</c:if>

	<!-- Checkrole -->
	<c:if test="${isUserAtHomePage=='true'}">
		<jsp:include page="FrontPage.jsp"></jsp:include>
	</c:if>

	<!-- CPU -->
	<c:if test="${isUserClickedCPU=='true' }">
		<jsp:include page="CPU.jsp"></jsp:include>
	</c:if>

	<!-- Motherboard -->
	<c:if test="${isUserClickedMotherboard=='true' }">
		<jsp:include page="Motherboard.jsp"></jsp:include>
	</c:if>

	<!-- Memory -->
	<c:if test="${isUserClickedMemory=='true' }">
		<jsp:include page="Memory.jsp"></jsp:include>
	</c:if>

	<!-- Storage -->
	<c:if test="${isUserClickedStorage=='true' }">
		<jsp:include page="Storage.jsp"></jsp:include>
	</c:if>

	<!-- VideoCard -->
	<c:if test="${isUserClickedVideoCard=='true' }">
		<jsp:include page="VideoCard.jsp"></jsp:include>
	</c:if>

	<!-- ListAllProducts -->
	<c:if test="${isUserClickedListAllProducts=='true' }">
		<jsp:include page="ListProducts.jsp"></jsp:include>
	</c:if>

	<!-- ProdutDescription -->
	<c:if test="${isUserDescriptionPage=='true'}">
		<jsp:include page="ProductDescription.jsp"></jsp:include>
	</c:if>

	<!-- Cart -->
	<c:if test="${isCartPage=='true'}">
		<jsp:include page="Cart.jsp"></jsp:include>
	</c:if>

	<!-- OrderHistory -->
	<c:if test="${isUserClickedOrderHistory=='true'}">
		<jsp:include page="OrderHistory.jsp"></jsp:include>
	</c:if>

	<!-- Address -->
	<c:if test="${isUserClickedAddress=='true'}">
		<jsp:include page="Address.jsp"></jsp:include>
	</c:if>

	<!-- MyAccount -->
	<c:if test="${isUserClickedAccountInfo=='true'}">
		<jsp:include page="AccountInfo.jsp"></jsp:include>
	</c:if>

	<!-- PaymentInfo -->
	<c:if test="${isUserClickedPayment=='true'}">
		<jsp:include page="PaymentInfo.jsp"></jsp:include>
	</c:if>

	<!-- Checkout -->
	<c:if test="${isUserClickedCheckout=='true'}">
		<jsp:include page="CheckOut.jsp"></jsp:include>
	</c:if>

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="//netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>