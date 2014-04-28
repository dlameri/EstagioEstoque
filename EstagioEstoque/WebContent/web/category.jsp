<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>
<script type="text/javascript" src="../js/category/category.js"></script>
<script type="text/javascript" src="../js/category/validate.category.js"></script>
</head>
<body>

	<h1>Categorias</h1>
	<br />

	<form>
		<input type="text" class="searchBar form-control" id="searchProduct">
		<select class="form-control statusSelector">
			<option value="true" selected="selected">Ativos</option>
			<option value="false">Inativos</option>
		</select><br>
		<br>
		<button class="btn btn-submit btn-primary btn-search">Product
			Search</button>
		<button class="btn btn-submit btn-primary btn-search">Clean
			Field</button>
	</form>

	<div id="categoryContainer"></div>
</body>