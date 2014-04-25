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
	<div id="three-column" class="container">
		<div id="tbox2">
			<h3>Nova SubCategoria</h3>
			<form action="addsubcategory" class="form" method="post">

				<fieldset>
					<input type="hidden" name="subcategory.id" value="${subcategory.id}" />
					<input type="hidden" name="subcategory.category.id" value="${subcategory.category.id}" /> 
					<label>Selecione uma categoria:</label> 
					<select name="category.id" id="categoryList" style="display: block;">
						<c:forEach items="${categories}" var="category">
							<option value="${category.id}" ${category.id == subcategory.category.id ? 'selected' : ''}>${category.name}</option>
						</c:forEach>
					</select>
					<label>Nome da subcategoria:</label>
					<s:textfield id="form-control" placeholder="Subcategoria"
						name="subcategory.name" cssClass="subcategoryName"  maxlength="15" />
					<input type="submit" class="btn btn-submit btn-primary" value="Salvar" /> 
					<span class="error_message hidden nameError">O nome da subcategoria deve ter pelo menos 3 caracteres.</span><br /> <br />
				</fieldset>
			</form>

		</div>
		<div id="tbox3">
			<h3>Subcategorias Cadastradas</h3>
			<ul>
				<c:forEach items="${categories}" var="category">
					<li>${category.name}</li>
					<ul>
						<c:if test="${category.subcategories != null }">
							<c:forEach items="${category.subcategories}" var="subcategory">
								<li><c:if test="${subcategory.active != false }">
						<span class ="subcategoryNameList" >${subcategory.name}</span> <a href="subcategorias?id=${subcategory.id}"
											type="button" class="btn btn-xs btn-warning"">editar</a>
										<a href="deletesubcategory?id=${subcategory.id}" type="button"
											class="btn btn-xs btn-danger">deletar</a>
									</c:if></li>
							</c:forEach>
						</c:if>
					</ul>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>