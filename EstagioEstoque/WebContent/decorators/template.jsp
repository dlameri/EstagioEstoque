<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Ideais Stock</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800|Open+Sans+Condensed:300,700" rel="stylesheet" />
<link href="../css/fonts.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jQuery/jquery-ui-1.10.4.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jQuery/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/jQuery/jTable/themes/metro/lightgray/jtable.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/default.css" rel="stylesheet" type="text/css" media="all" />


<script type="text/javascript" src="../js/jQuery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="../js/jQuery/jquery-ui-1.10.4.min.js"></script>
<script type="text/javascript" src="../js/jQuery/jquery.mask.min.js"></script>
<script type="text/javascript" src="../js/jQuery/external/json2.min.js"></script>
<script type="text/javascript" src="../js/jQuery/jquery.jtable.js"></script>
<script type="text/javascript" src="../js/jQuery/jquery.jtable.pt-BR.js"></script>

<script type="text/javascript" src="../js/jQuery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="../js/jQuery/jquery.validationEngine-pt_BR.js"></script>
<decorator:head />
</head>
<body>
  <div id="logo" class="container">
		<h1>
			<img src="../images/logobolinha.png"> <a href="/EstagioEstoque/web/"><span>Ideais
					Electronics</span></a>
		</h1>
		<p>Sistema de Controle e Administração de Estoque</p>
	</div>
	<div id="wrapper" class="container">
		<div id="menu" class="container">
			<ul>
				<li><a href="/EstagioEstoque/web/">Home</a></li>
				<li><a href="categorias">Categoria & Subcategoria</a></li>
				<li><a href="produtos">Produto & Item</a></li>
				<li><a href="/EstagioEstoque/LogoutServlet">Logout</a></li>
			</ul>
		</div>	
    <decorator:body />
    <div id="light" class="white_content hidden"></div>
			<div id="fade" class="black_overlay hidden"></div>
   </div>	
   <footer id="copyright">
		<p>Copyright (c) 2014 Stock Team Enterprise All rights reserved. Design by Stock Team Enterprises.</p>
	</footer>
</body>
</html>