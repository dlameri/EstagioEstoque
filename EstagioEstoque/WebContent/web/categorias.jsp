<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>
</head>
<body>

	<div id="three-column" class="container">
		<div id="tbox2">
			<h3>Nova Categoria</h3>
			<s:form action="web/saveCategory">
				<s:hidden name="category.id" value="%{category.id}" />
				<s:textfield label="Nome" id="form-control" placeholder="Categoria"
					name="category.name" value="%{category.name}" />
				<s:submit class="btn btn-primary" value="Salvar" />
			</s:form>
		</div>
		<div id="tbox3">
			<h3>Categorias Cadastradas</h3>
			<c:forEach items="${categories}" var="category">
				<li>${category.name}&nbsp;<a href="categorias?id=${category.id}">
				<button	type="button" class="btn btn-xs btn-warning">Editar</button></a>
				 <a href="deletecategory?id=${category.id}"><button type="button" class="btn btn-xs btn-danger">Deletar</button></a>
				</li>
			</c:forEach>
		</div>
	</div>

</body>