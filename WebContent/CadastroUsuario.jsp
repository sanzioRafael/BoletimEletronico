<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="estilo/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="estilo/bootstrap/css/bootstrap-responsive.css"/>
<link rel="stylesheet" href="estilo/cadastro.css"/>
<script type="text/javascript" src="estilo/bootstrap/js/bootstrap.js"></script>
<link rel="shortcut icon" href="img/notes-edit-icon.png"/>
<title>Cadastrar Usuário</title>
</head>
<body>
	
	
	<div class="jumbotron">
		<header class="page-header">
			<div class="container">
				<h1>Área de Cadastro</h1>
				<p class="lead">Caso não seja cadastrado</p>
			</div>
		</header>
		<form class="navbar-form" action="CadastroUsuario" method="post">
			<div class="control-group">
				<div class="controla">
					<label for="userName" class="control-label">Nome Completo:</label>
					<input type="text" placeholder="Nome" name="txtNome" maxlength="50"/>
				</div>
					<font color="red">${erroNome}</font>
			</div>
			<div class="control-group">
				<div class="controla">
					<label for="userEmail" class="control-label">E-mail:</label>
					<input type="email" placeholder="xxxxx@xxxx.com" name="txtEmail" maxlength="50"/>
				</div>
					<font color="red">${erroEmail}</font>
			</div>
			<div class="control-group">
				<div class="controla">
					<label for="userLogin" class="control-label">Login:</label>
					<input type="text" placeholder="Login" name="txtLogin" maxlength="20"/>
				</div>
					<font color="red">${erroLogin}</font>
			</div>
			<div class="control-group">
				<div class="controla">
					<label for="userSenha" class="control-label">Senha:</label>
					<input type="password" placeholder="Senha" name="txtSenha" maxlength="20"/>
				</div>
					<font color="red">${erroSenha}</font>
			</div>
			<div class="control-group">
				<div class="controla">
					<label for="userConfSenha" class="control-label">Confirmação Senha:</label>
					<input type="password" placeholder="Confirmação Senha" name="txtConfSenha" maxlength="20"/>
				</div>
					<font color="red">${erroConfSenha}</font>
			</div>
			<div class="control-group">
				<div class="controla">
					<input type="submit" class="btn btn-primary btn-sm pull-left" value="Cadastrar Usuário" />
				</div>
			</div>
		</form>
	</div>
	
</body>
</html>