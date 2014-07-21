<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="estilo/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="estilo/bootstrap/css/bootstrap-responsive.css"/>
<link rel="stylesheet" href="estilo/login.css"/>
<script type="text/javascript" src="estilo/bootstrap/js/bootstrap.js"></script>
<link rel="shortcut icon" href="img/notes-edit-icon.png"/>
<title>Login - Boletim Eletrônico</title>
</head>
<body>

	<div class="container">
		<div id="content" class="span4 offset4">
			<h3>Logar Conta</h3>
			<form class="form-vertical" method="post" action="LogarConta">
				<div class="control-group">
					<label for="userName" class="control-label">Usuário:</label>
					<div class="controla">
						<input type="text" name="txtLogin" maxlength="20" title="Nome do usuário"/>
					</div>
				</div>
				<div class="control-group">
					<label for="password" class="control-label">Senha:</label>
					<div class="controla">
						<input type="password" name="txtSenha" maxlength="20" title="Senha do usuário"/>
					</div>
				</div>
				<div class="form-actions">
					<input type="submit" class="btn btn-primary btn-sm" value="Entrar" />
					 ou <a href="CadastroUsuario.jsp">Cadastre-se no Boletim</a>
					<font color="red">${erroLogar}</font>
				</div>
			</form>
			
		</div>
	</div>

</body>
</html>