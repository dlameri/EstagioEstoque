<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estoque</title>
</head>
<body>
	<h1>Estoque</h1>
	<ul type="none">
		<li><a href="category.jsp">Categorias</a></li>
		<li>
			<form action="listcategories" method="get">
				<input type="submit" value="List Categories" />
			</form>
		</li>
		<li>
			<form action="enteraddsubcategory" method="get">
				<input type="submit" value="Add Subcategory" />
			</form>
		</li>
		<li>
			<form action="listsubcategories" method="get">
				<input type="submit" value="List Subcategories" />
			</form>
		</li>
	</ul>
</body>
</html>