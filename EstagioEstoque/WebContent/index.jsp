<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="./css/default.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="./css/fonts.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="./js/catalogo.js"></script>

</head>
<body>
	<div id="logo" class="container">
		<h1>
			<img src="./images/logobolinha.png"> <a href="#"><span>Ideais Electronics</span></a>
		</h1>
		<p>Sistema de Controle e Administração de Estoque</p>
	</div>
	<div id="login">
		<table id="tabela">
			<tr>
				<td>
					<form action="LoginServlet" method="post">
						<h3>ACESSO RESTRITO</h3><br>						
						Email:<input type="text" name="email" value="admin@teste.com">
						Senha:<input type="password" name="pwd" value="123">
						<input type="submit" value="Login"> 
					</form>
					<label>Rodar o teste create do admin para criar o administrador no banco.</label>
				</td>
			</tr>
		</table>
	</div>	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<footer id="copyright">
		<p>Copyright (c) 2013 Stock Team Enterprise All rights reserved. |
			Design by Stock Team Enterprises.</p>
	</footer>
</body>
</html>