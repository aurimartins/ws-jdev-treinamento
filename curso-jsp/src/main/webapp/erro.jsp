<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang= "en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Responsividade de tela -->

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title>Erros!</title>

<style type="text/css">
h1{
	position: 10%;
	top: 30%;
	left: 33%;
	font-size: 30px;
}

</style>
</head>

<body>
	<h1>Mensagem de erro, entre em contato com a equipe de suporte do sistema</h1>
	
	<% out.print(request.getAttribute("msg"));%>
	
</body>
</html>