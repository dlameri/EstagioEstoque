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
			<s:textfield type="text" placeholder="Nome" name="product.name" value="%{product.name}" cssClass="productName"  /><br/>	
			<label>Descrição curta:</label>
			<s:textfield type="text" placeholder="Descrição" name="product.shortDescription"  value="%{product.shortDescription}" cssClass="shortDesc" /><br/>
			<label>Descrição longa:</label>
			<s:textfield type="textarea" name="product.longDescription" value="%{product.longDescription}" class="longDesc" /><br/>
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