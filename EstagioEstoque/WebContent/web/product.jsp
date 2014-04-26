
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
<%-- <script type="text/javascript" src="../js/validate.product.js"></script> --%>
</head>
<body>

<form action="addproduct" class="form" method="post">
		<fieldset>
			<label>Selecione uma subcategoria:</label>
			<select id="categoryList" style="display: block;">
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
			</select>	
			
			<select name="subcategory.id" id="categoryList" class="subcategoryList" style="display: block;">
			</select>

			<label for="name">Nome produto:</label>
			<s:textfield  placeholder="Nome" name="product.name" cssClass="productName"  /><br/>
			<label>Descrição curta:</label>
			<s:textfield placeholder="Descrição" name="product.shortDescription" cssClass="shortDesc" /><br/>
			<label>Descrição longa:</label>
			<s:textarea name="product.longDescription" cssClass="longDesc" cols="40" rows="10" /><br/>
			<label>Peso:</label>
			<s:textfield  placeholder="Peso" name="product.weight" cssClass="weight"  /><br/>
			<label>Garantia:</label>
			<s:textfield  placeholder="Garantia" name="product.warranty" cssClass="warranty"  /><br/>
			<label>Marca:</label>
			<s:textfield  placeholder="Marca" name="product.brand" cssClass="brand"  /><br/>
			<label>Modelo:</label>
			<s:textfield  placeholder="Modelo" name="product.model" cssClass="model"  /><br/>
			<label class="btn-submit">Dimensões:</label>
			<s:textfield  placeholder="Largura" name="dimensions.width" cssClass="width"  />
			<s:textfield  placeholder="Altura" name="dimensions.height" cssClass="height"  />
			<s:textfield  placeholder="Profundidade" name="dimensions.depth" cssClass="depth"  /><br/>
			<input type="submit" class="btn btn-submit btn-primary" value="Salvar" />
		</fieldset>	
	</form>
	
	<ul>
		<c:forEach items="${products}" var="product">
			<li>
				${product.category} - ${product.subcategory} <br/>
				Nome: ${product.name} - ShortDesc: ${product.shortDescription } - LongDesc: ${product.longDescription }<br/>
				Weight: ${product.weight } - Warranty: ${product.warranty } - Model: ${product.model }<br/>
				Brand: ${product.brand } - Dimensions: ${product.dimensions.width }x${product.dimensions.height }x${product.dimensions.depth }
				<br/><a href="deleteproduct?id=${product.id}">deletar</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>