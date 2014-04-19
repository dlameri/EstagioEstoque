<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/validate.subcategory.js"></script>
<title>Subcategoria</title>
</head>
<body>
	<h1>Subcategoria</h1>
	
		<form action="addsubcategory" method="get">
		
		<fieldset>
			<label>Selecione uma categoria:</label>
			<select name="category.id" id="categoryList" style="display: block;">
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
			</select>

			<label>Nome subcategria:</label><br/>
			<s:textfield id="form-control" placeholder="Subcategoria" name="subcategory.name" cssClass="subcategoryName" maxlength="15" />
			<span class="error_message hidden nameError">O nome da subcategoria deve ter pelo menos 3 caracteres.</span><br/><br/>
			<input type="submit" class="btn btn-submit btn-primary" value="Salvar" />
		</fieldset>	
	</form>

		<ul>
		<c:forEach items="${categories}" var="category">
			<li>${category.name}</li>
			<ul>
			<c:if test="${category.subcategories != null }">
			<c:forEach items="${category.subcategories}" var="subcategory">
				<li>
					<c:if test="${subcategory.active != false }" >
						${subcategory.name} - editar - <a href="deletesubcategory?id=${subcategory.id}">deletar</a>
					</c:if>
				</li>
			</c:forEach>
			</c:if>
			</ul>
		</c:forEach>
	</ul>

</body>
</html>