
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produtos</title>
<script type="text/javascript" src="../js/product/validate.product.js"></script>
<script type="text/javascript" src="../js/product/pagination.js"></script>
<script type="text/javascript" src="../js/product/product.js"></script>
</head>
<body>

<a href="productForm">Adicionar Produto</a>
	<br>
	<br>
	<form>
		<input type="text" class="searchBar">
		<select class="statusSelector">
			<option value="true" selected="selected">Ativos</option>
			<option value="false">Inativos</option>
		</select>
		<button class="btn btn-submit btn-primary btn-search">buscar</button>
	</form>
	<br>
<!-- 	<ul class="productsList"> -->
<%-- 		<c:forEach items="${products}" var="product"> --%>
<!-- 			<li> -->
<%-- 				${product.category} - ${product.subcategory} <br/> --%>
<%-- 				Nome: <a href="productForm?id=${product.id}">${product.name}</a> - ShortDesc: ${product.shortDescription } - LongDesc: ${product.longDescription }<br/> --%>
<%-- 				Weight: ${product.weight } - Warranty: ${product.warranty } - Model: ${product.model }<br/> --%>
<%-- 				Brand: ${product.brand } - Dimensions: ${product.dimensions.width }x${product.dimensions.height }x${product.dimensions.depth } --%>
<%-- 				<br/><a href="deleteproduct?id=${product.id}">deletar</a> --%>
<!-- 			</li> -->
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
	
	<div id="productContainer"></div>

</body>
</html>