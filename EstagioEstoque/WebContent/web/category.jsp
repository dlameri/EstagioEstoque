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
	<div id="logo" class="container">
		<h1>
			<img src="../images/logobolinha.png"> <a href="#"><span>Estágio
					Ideais</span></a>
		</h1>
		<p>Sistema de Controle e Administração de Estoque</p>
	</div>
	<div id="wrapper" class="container">
		<div id="menu" class="container">
			<ul>
				<li><a href="home.jsp" accesskey="1" title="">Sistema de
						Cadastro</a></li>
				<li class="current_page_item"><a accesskey="1" title="">Produtos
						Cadastrados</a></li>
			</ul>
		</div>
		<div id="three-column" class="container">
			<div>
				<span class="icon icon-cogs"></span>
				<div class="title">
					<h2>Adicionar Nova Categoria</h2>
					<s:form action="addcategory">
						<s:textfield name="category.name" />
						<s:submit value="Add new category" />
					</s:form>
				</div>
			</div>
		</div>
	</div>
	<div id="copyright">
		<p>Copyright (c) 2013 Stock Team Enterprise All rights
			reserved. | Design by Stock Team Enterprises.</p>
	</div>
</body>
</html>