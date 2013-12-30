<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ADD CATEGORIA</title>
</head>
<body>
	<marquee><h1>ADD CATEGORIA</h1></marquee>
	<ul>
		<s:iterator value="cart.products">
			<li> <s:property value="id" /> : <s:property value="name" /> </li>	
		</s:iterator>
	</ul>
	
	<s:form action="addcategory">
		<s:textfield name="category.name" />

		<s:submit value="Add"/>
	</s:form>
</body>
</html>