<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>
</head>
<body>

	<h1>Categoria</h1>
	
	<ul>
		<c:forEach items="${categories}" var="category">
			<li> ${category.name} - <a href="deletecategory?id=${category.id}">deletar</a> </li>	
		</c:forEach>
	</ul>

</body>