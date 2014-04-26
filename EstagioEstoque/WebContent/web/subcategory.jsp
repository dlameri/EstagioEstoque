<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/validate.subcategory.js"></script>
<script type="text/javascript" src="../js/subcategory.js"></script>
<title>Subcategoria</title>
</head>
<body>
	<div id="three-column" class="container">
		<div id="tbox2">
			<h3>Nova SubCategoria</h3>
			<form action="addsubcategory" class="form" method="post">

				<fieldset>
					<label>Selecione uma categoria:</label> <select name="category.id"
						id="categoryList" style="display: block;">
						<c:forEach items="${categories}" var="category">
							<option value="${category.id}" ${category.id == subcategory.category.id ? 'selected' : ''}>${category.name}</option>
						</c:forEach>
					</select> 
					<input type="hidden" name="subcategory.id" value="${subcategory.id}" /> 
					<label>Nome da subcategoria:</label>
					<s:textfield id="form-control" placeholder="Subcategoria"
						name="subcategory.name" value="%{subcategory.name}" cssClass="subcategoryName" maxlength="15" />
					<input type="submit" class="btn btn-submit btn-primary"
						value="Salvar" />
				</fieldset>
			</form>

			<span  id="toBeDeleted">
				<c:if test="${deleted == \"true\" }">
					<p>Subcategoria deletada com sucesso.</p>
				</c:if>
			</span>
		</div>
		<div id="tbox3">
			<h3>Subcategorias Cadastradas</h3>
			<ul>
				<c:forEach items="${categories}" var="category">
					<li>${category.name}</li>
					<ul>
						<c:forEach items="${category.subcategories}" var="subcategory">
							<c:if test="${subcategory.active == true}">
								<li id="${subcategory.id}">
									<span id="name-${subcategory.id}" class ="subcategoryNameList" >${subcategory.name}</span> 
									<a id="edit-${subcategory.id}" href="subcategorias?id=${subcategory.id}" type="button" class="btn btn-xs btn-warning">editar</a>
									<button id="delete-${subcategory.id}" class="btn btn-xs btn-danger">deletar</button>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>