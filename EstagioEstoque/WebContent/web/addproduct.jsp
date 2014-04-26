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
<script type="text/javascript" src="../js/validate.product.js"></script>
</head>
<body>

	<form action="addproduct?id=${id}" class="form" method="post">
		<fieldset>
			<label>Selecione uma subcategoria:</label>
			<select id="categoryList" style="display: block;">
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}" ${category.id == product.category.id ? 'selected' : ''}>${category.name}</option>
				</c:forEach>
			</select>	
			
			<input type="hidden" class="subcategoryId" value="${product.subcategory.id}" />
			<select name="subcategory.id" id="categoryList" class="subcategoryList" style="display: block;">
			</select>

			<label for="name">Nome produto:</label>
			<s:textfield type="text" placeholder="Nome" name="product.name" value="%{product.name}" cssClass="productName"  /><br/>	
			<label>Descrição curta:</label>
			<s:textfield type="text" placeholder="Descrição" name="product.shortDescription"  value="%{product.shortDescription}" cssClass="shortDesc" /><br/>
			<label>Descrição longa:</label>
			<s:textarea name="product.longDescription" value="%{product.longDescription}" class="longDesc" /><br/>
			<label>Peso:</label>
			<s:textfield  placeholder="Peso" name="product.weight" value="%{product.weight}" cssClass="weight"  /><br/>
			<label>Garantia:</label>
			<s:textfield  placeholder="Garantia" name="product.warranty" value="%{product.warranty}" cssClass="warranty"  /><br/>
			<label>Marca:</label>
			<s:textfield  placeholder="Marca" name="product.brand" value="%{product.brand}" cssClass="brand"  /><br/>
			<label>Modelo:</label>
			<s:textfield  placeholder="Modelo" name="product.model" value="%{product.model}" cssClass="model"  /><br/>
			<label class="btn-submit">Dimensões:</label>
			<s:textfield  placeholder="Largura" name="dimensions.width" value="%{product.dimensions.width}" cssClass="width"  />
			<s:textfield  placeholder="Altura" name="dimensions.height" value="%{product.dimensions.height}" cssClass="height"  />
			<s:textfield  placeholder="Profundidade" name="dimensions.depth" value="%{product.dimensions.depth}" cssClass="depth"  /><br/>
			<input type="submit" class="btn btn-submit btn-primary" value="Salvar" />
		</fieldset>	
	</form>
</body>
</html>