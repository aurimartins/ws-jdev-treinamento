<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Responsividade de tela -->
	
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		
	<title>Curso JSP</title>

<style type="text/css">
h5{
position: absolute;
top:30%;
left:33%;
}

form{
position: absolute;
top:40%;
left: 33%;
}
</style>	

</head>
<body>
	<h5>Bem vindo ao curso de JSP!</h5>
	
	
<form action="ServletLogin" method="post" class="row g-3">
<input type="hidden" value="<%= request.getParameter("url")%>" name="url">

<div class="col-md-6">
 	<label class="form-label">Login</label>
	<input class="form-control" name= "login" type="text">
</div>

<div class="col-md-6">
	<label class="form-label">Senha</label>
	<input class="form-control" name= "senha" type="password">
</div>
 	
	<input class="btn btn-primary" type="submit" value="Enviar">



</form>

<h4>${msg}</h4>

 <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


</body>
</html>