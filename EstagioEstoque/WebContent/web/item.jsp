
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produtos</title>
<%-- <script type="text/javascript" src="../js/validatorItem.js"></script> --%>
<link href="../css/validator.css" rel="stylesheet" type="text/css" media="all" />
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
			<label>Imagens Site:</label>
<!-- 			radioboxes para main  -->
			<s:textfield  placeholder="URL Vitrine" name="images.showcaseUrl" cssClass="showcaseUrl"  /><br/>
			<s:textfield  placeholder="URL Superzoom" name="images.superzoomUrl" cssClass="superzoomUrl"  /><br/>
			<s:textfield  placeholder="URL Carrinho" name="images.shoppingCartUrl" cssClass="shoppingCartUrl"  /><br/>
			<s:textfield  placeholder="URL Promo" name="images.promo" cssClass="promo"  /><br/>
			<label>Imagens Android:</label>
			<s:textfield  placeholder="URL Vitrine" name="images.androidShowcaseUrl" cssClass="androidShowcaseUrl"  /><br/>
			<s:textfield  placeholder="URL Página Produto" name="images.androidProductUrl" cssClass="androidProductUrl"  /><br/>
			
			
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
</body>
</html>