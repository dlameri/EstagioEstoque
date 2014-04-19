
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produtos</title>
<script type="text/javascript" src="../js/item.js"></script>
<script type="text/javascript" src="../js/validate.item.js"></script>
<link href="../css/item.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>

<form action="addItem" class="form" method="post">
		<fieldset>
			<label>Selecione um produto:</label>
			<s:textfield placeholder="ID do Produto" name="product.id" cssClass="productId" /><br/>

			<label>SKU:</label>
			<s:textfield placeholder="SKU" name="item.sku" cssClass="sku" /><br/>
			<label>Preço De:</label>
			<s:textfield  placeholder="Preço De" name="item.priceFrom" cssClass="priceFrom"  /><br/>
			<label>Preço por:</label>
			<s:textfield  placeholder="Preço Por" name="item.priceFor" cssClass="priceFor"  /><br/>
			<label>Chave SKU:</label>
			<s:textfield  placeholder="Chave SKU" name="item.optionName" cssClass="optionName"  /><br/>
			<label>Valor SKU:</label>
			<s:textfield  placeholder="Valor SKU" name="item.optionValue" cssClass="optionValue"  /><br/>
			<label>Estoque:</label>
			<s:textfield  placeholder="Estoque" name="item.stock" cssClass="stock"  /><br/>
			<span id="imageAddButton">Adicionar Imagens</span>
			<div class="images">
			</div>
			
			<input type="submit" class="btn btn-submit btn-primary" value="Salvar" />
		</fieldset>	
	</form>
	
	<ul>
		<c:forEach items="${items}" var="item">
			<li>
				Nome: ${item.name}
				<br/><a href="deleteitem?id=${item.id}">deletar</a>
			</li>
		</c:forEach>
	</ul>
	
	<div class="imagesTemplate">
		<label>Imagens Site:</label><br/>
			<s:textfield  placeholder="URL Vitrine" name="images.showcaseUrl" cssClass="showcaseUrl"  disabled="disabled" /><br/>
			<s:textfield  placeholder="URL Superzoom" name="images.superzoomUrl" cssClass="superzoomUrl" disabled="disabled" /><br/>
			<s:textfield  placeholder="URL Carrinho" name="images.shoppingCartUrl" cssClass="shoppingCartUrl" disabled="disabled" /><br/>
			<s:textfield  placeholder="URL Promo" name="images.promo" cssClass="promo" disabled="disabled" /><br/>
		<label>Imagens Android:</label><br/>
			<s:textfield  placeholder="URL Vitrine" name="images.androidShowcaseUrl" cssClass="androidShowcaseUrl" disabled="disabled" /><br/>
			<s:textfield  placeholder="URL Página Produto" name="images.androidProductUrl" cssClass="androidProductUrl" disabled="disabled" /><br/>
		<span>Principal: </span><input type="radio" name="images.main" />	
		<span class="removeButton">Remover</span>
	</div>
	
</body>
</html>