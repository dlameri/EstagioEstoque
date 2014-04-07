<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Login de Usuário</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800|Open+Sans+Condensed:300,700"
	rel="stylesheet" />
<link href="./css/default.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="./css/fonts.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="./css/bootstrap.css" rel="stylesheet">

<link href="./css/login.css" rel="stylesheet">
<script type="text/javascript" src="./js/catalogo.js"></script>

</head>
<body>
	<div id="logo" class="container">
		<h1>
			<img src="./images/logobolinha.png"> <a href=""><span><b>Ideais
						Electronics</b></span></a>
		</h1>
		<p>Sistema de Controle e Administração de Estoque</p>
	</div>
	<div class="container">
		<form action="LoginServlet" method="post" class="form-signin">
			<h3 class="form-signin-heading">Área de Acesso Restrito</h3>
			<input type="text" class="form-control" placeholder="Login"
				name="email" value="admin@teste.com" required autofocus> <input
				type="password" class="form-control" placeholder="Senha" name="pwd"
				value="123" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
		</form>
	</div>
	<!-- /container -->
	<footer id="copyright">
		<p>Copyright (c) 2014 Stock Team Enterprise All rights reserved. |
			Design by Stock Team Enterprises.</p>
	</footer>
</body>
</html>