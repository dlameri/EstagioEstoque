<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800|Open+Sans+Condensed:300,700"
	rel="stylesheet" />
<link href="../css/default.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/fonts.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="./js/update.js"></script>

</head>
<body>
				<div class="title">
					<h2>Adicionar Nova Categoria</h2>
					<s:form action="addcategory">
						<s:textfield name="category.name" />
						<s:submit value="Add new category" />
					</s:form>
				</div>
</body>
</html>