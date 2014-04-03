
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produtos</title>
<script type="text/javascript" src="../js/product.js"></script>
</head>
<body>

<form action="addproduct" method="post">
		<fieldset>
			<label>Selecione uma subcategoria:</label>
			<select name="category.id" id="categoryList" style="display: block;">
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
			</select>	
			
			<c:forEach items="${categories}" var="category">
			<select name="subcategory.id" id="categoryList" class="subcategoryList hidden" data-categoryid="${category.id}">
				<c:forEach items="${category.subcategories}" var="subcategory">
					<option value="${subcategory.id}">${subcategory.name}</option>
				</c:forEach>
			</select>
			</c:forEach>
			<label>Nome produto:</label>
			<input type="text" name="product.name" /><br/>
			<label>Descrição curta:</label>
			<input type="text" name="product.shortDescription" /><br/>
			<label>Descrição longa:</label>
			<TextArea type="text" name="product.longDescription"></TextArea><br/>
			<label>Peso:</label>
			<input type="text" name="product.weight" /><br/>
			<label>Garantia:</label>
			<input type="text" name="product.warranty" /><br/>
			<label>Marca:</label>
			<input type="text" name="product.brand" /><br/>
			<label>Modelo:</label>
			<input type="text" name="product.model" /><br/>
			<label>Dimensões:</label>
			<input type="text" name="dimensions.width" size="10" />
			<input type="text" name="dimensions.height" size="10" />
			<input type="text" name="dimensions.depth" size="10" /><br/>
			<input type="submit" id="btn" value="Add" />
		</fieldset>	
	</form>


</body>
</html>