<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Subcategoria</title>
</head>
<body>

	<h1>Subcategoria</h1>

	<ul>
		<c:forEach items="${categories}" var="category">
			<li>${category.name}</li>
			<ul>
			<c:forEach items="${category.subcategories}" var="subcategory">
				<li>
					${subcategory.name} - <a href="deletesubcategory?id=${subcategory.id}">deletar</a>
				</li>
			</c:forEach>
			</ul>
			</li>
		</c:forEach>
	</ul>

</body>