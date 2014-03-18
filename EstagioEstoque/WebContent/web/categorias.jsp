<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>
</head>
<body>

	<h1>Categoria</h1>
	
	<div class="title">
		<h2>Adicionar Nova Categoria</h2>
		<s:form action="web/saveCategory" >
			<s:hidden name="category.id" value="%{category.id}" />
			<s:textfield label="Nome" name="category.name" value="%{category.name}"/>
			<s:submit value="Salvar" />
			<c:if test="${category.id == null}">
  				<p><s:reset value="Limpar Campos" /></p>
			</c:if>
		</s:form>
	</div>
	
	<ul>
		<c:forEach items="${categories}" var="category">
			<li> ${category.name} - <a href="categorias?id=${category.id}">editar</a> - <a href="deletecategory?id=${category.id}">deletar</a> </li>	
		</c:forEach>
	</ul>

</body>