<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://use.fontawesome.com/e1fbaa6604.js"></script>
<link href="resources/css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Admin!</title>
</head>
<body>

	<div class="cw-content">
		<div class="container">
			<div class="row">
				<div class="alert alert-success alert-nondis" role="alert">
					<button type="button"
						onclick="this.parentNode.parentNode.removeChild(this.parentNode);"
						class="close" data-dismiss="alert">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<marquee>
						<p style="font-family: Impact; font-size: 15pt">Welcome
							&nbsp${user}!!!</p>
						<p>
							<font color="red">You can manage categories, suppliers and
								products from the Manage drop down menu.</font> <font color="blue">You
								can manage users from the Manage Users under settings.</font>
						</p>
					</marquee>
				</div>
			</div>
		</div>
	</div>
</body>
</html>