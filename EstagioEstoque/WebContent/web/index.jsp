<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<body>
	
		<div id="three-column" class="container">
			<div id="tbox2">
				<span class="icon icon-wrench"></span>
				<c:choose>
					<c:when test="${ product.id == null }">
						<div class="title">
							<h2>Cadastro de Produtos</h2>
						</div>
						<p>Pagina criada para administrar o cadastro de produtos. Aqui
							será possí­vel cadastrar os produtos em nosso sistema de
							administração e controle de estoque.</p>
					</c:when>
					<c:otherwise>
						<div class="title">
							<h2>Edição de Produtos</h2>
						</div>
						<p>Pagina criada para administrar a edição de produtos. Aqui
							será possíÂ­vel alterar os produtos em nosso sistema de
							administração e controle de estoque.</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="tbox3">

				<s:form action="addcategory">
					<s:label value="Categoria " />
					<s:textfield name="category.name" />
					<s:submit value="Cadastrar Categoria" />
				</s:form>
				
			</div>
		</div>
	
</body>
</html>
