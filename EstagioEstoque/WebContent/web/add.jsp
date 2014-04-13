
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produtos</title>

<script type="text/javascript" src="../js/jquery.mask.min.js"></script>
<script type="text/javascript" src="../js/add.js"></script>
<link href="../css/add.css" rel="stylesheet" type="text/css" media="all" />

</head>
<body>

<form action="addcategory" method="post">
	<p class="error_message hidden categoryNameError">O nome da categoria deve ter pelo menos 3 caracteres.</p>
	<p class="error_message hidden subcategoryNameError">O nome da subcategoria deve ter pelo menos 3 caracteres.</p>
	<fieldset>
		<label>Nome categoria:</label> 
				<input type="text" label="Nome" id="form-control" placeholder="Categoria"
					name="category.name" class="categoryName" maxlength="15"/>
					<input type="text" label="Nome" id="form-control" placeholder="Subcategoria"
					name="subcategory.name" class="subcategoryName" />
				<input type="submit" class="btn btn-primary" value="Salvar" />
	</fieldset>
</form>

</body>
</html>