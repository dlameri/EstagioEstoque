<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>
<script type="text/javascript" src="../js/category.js"></script>
<script type="text/javascript" src="../js/validate.category.js"></script>
</head>
<body>

	<div id="three-column" class="container">
		<div id="tbox2">
			<h3>Nova Categoria</h3>
			<form action="addcategory" class="form" method="post">
				<input type="hidden" name="category.id" value="${category.id}" />
				<s:textfield type="text" id="form-control" placeholder="Categoria" name="category.name" value="%{category.name}" cssClass="categoryName" maxlength="15" />
				<input type="submit" class="btn btn-submit btn-primary" value="Salvar" />
			</form>
			<p class="error_message hidden nameError">O nome da categoria deve ter pelo menos 3 caracteres.</p>
			
		</div>
		<div id="tbox3">
			<h3>Categorias Cadastradas</h3>
			<c:forEach items="${categories}" var="category">
				<li id="${category.id}">
					
				<span id="name-${category.id}" >${category.name}</span>
				&nbsp;
				<a id="edit-${category.id}" href="categorias?id=${category.id}">
					<button	type="button" class="btn btn-xs btn-warning">Editar</button>
				</a>
				<button id="delete-${category.id}" type="button" class="btn btn-xs btn-danger">Deletar</button>
				</li>
			</c:forEach>
		</div>
	</div>
	
	<div id="toBeDeleted">
		<c:if test="${deleted == \"true\" }">
			<p> Categoria deletada com sucesso. </p>
		</c:if>
	</div>

</body>