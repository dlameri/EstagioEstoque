<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Estoque</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800|Open+Sans+Condensed:300,700" rel="stylesheet" />
<link href="../css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="./js/catalogo.js"></script>
<decorator:head />
</head>
<body>
  <div id="logo" class="container">
		<h1>
			<img src="../images/logobolinha.png"> <a href="#"><span>Ideais
					Electronics</span></a>
		</h1>
		<p>Sistema de Controle e Administração de Estoque</p>
	</div>
	<div id="wrapper" class="container">
		<div id="menu" class="container">
			<ul>
				<li><a href="categorias">Categorias</a></li>
				<li><a href="subcategorias">Subcategorias</a></li>
				<li><form action="/EstagioEstoque/LogoutServlet" method="post">
						<input type="submit" value="Logout" >
					</form>
				</li>
			</ul>
		</div>
    <decorator:body />
   <footer id="copyright">
		<p>Copyright (c) 2014 Stock Team Enterprise All rights reserved. Design by Stock Team Enterprises.</p>
	</footer>
</body>
</html>