
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produtos</title>
<script type="text/javascript">
jQuery.validator.setDefaults({
		  debug: true,
		  success: "valid"
		});
</script>
<script type="text/javascript" src="../js/product.js"></script>
<script type="text/javascript" src="../js/validatorProduct.js"></script>
<link href="../css/validator.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>

<form action="addproduct" class="form" method="post">
		<fieldset>
			<label>Selecione uma subcategoria:</label>
			<select name="category.id" id="categoryList" style="display: block;">
				<option value="-1">SELECIONE UMA CATEGORIA</option>
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
			</select>	
			
			<select name="subcategory.id" id="categoryList" class="subcategoryList" disabled="disabled" style="display: block;">
				<option value="-1">SELECIONE UMA CATEGORIA</option>
			</select>
			
			<div id="teste">
			</div>

			<p class="error_message hidden nameError">O nome da subcategoria deve ter pelo menos 3 caracteres.</p>
			<p class="error_message hidden shortDescError">A descrição curta deve ter pelo menos 3 caracteres.</p>
			<p class="error_message hidden longDescnameError">A descrição longa deve ter pelo menos 3 caracteres.</p>
			<p class="error_message hidden weightError">O peso deve ser um número.</p>
			<p class="error_message hidden warrantyError">A garantia deve ser um número.</p>
			<p class="error_message hidden brandError">A marca deve ter pelo menos 3 caracteres.</p>
			<p class="error_message hidden modelError">O modelo deve ter pelo menos 3 caracteres.</p>
			<p class="error_message hidden widthError">A largura deve ser um número.</p>
			<p class="error_message hidden heightError">A altura deve ser um número.</p>
			<p class="error_message hidden depthError">A profundidade deve ser um número.</p>

			<label for="name">Nome produto:</label>
			<input id="name" type="text" name="product.name" class="productName" minlength="3" required/><br/>
			<label>Descrição curta:</label>
			<input type="text" name="product.shortDescription" class="shortDesc" required/><br/>
			<label>Descrição longa:</label>
			<TextArea name="product.longDescription" class="longDesc" ></TextArea><br/>
			<label>Peso:</label>
			<input type="text" name="product.weight" class="weight" required /><br/>
			<label>Garantia:</label>
			<input type="text" name="product.warranty" class="warranty" required /><br/>
			<label>Marca:</label>
			<input type="text" name="product.brand" class="brand" required /><br/>
			<label>Modelo:</label>
			<input type="text" name="product.model" class="model" required /><br/>
			<label class="btn-submit">Dimensões:</label>
			<input type="text" name="dimensions.width" size="10" class="width" required />
			<input type="text" name="dimensions.height" size="10" class="height" required />
			<input type="text" name="dimensions.depth" size="10" class="depth" required /><br/>
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
			</li>
		</c:forEach>
	</ul>
</body>
</html>