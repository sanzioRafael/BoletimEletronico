<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.List"%>
<%@page import="br.unirondon.cop5.database.DisciplinaTurmaUsuario"%>
<%@page import="br.unirondon.cop5.database.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Adicionar Atividade</title>
<link rel="stylesheet" href="../estilo/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="../estilo/bootstrap/css/bootstrap-responsive.css"/>
<link rel="stylesheet" href="../estilo/padrao.css"/>
<link rel="stylesheet" href="../estilo/addTurma.css"/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="../estilo/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#myCarousel').carousel();
	});
</script>
<script>
	window.jQuery || document.write('<script src="js/jquery-1.7.1.min.js"><\/script>');
</script>
<link rel="shortcut icon" href="../img/notes-edit-icon.png"/>
</head>
<body>
	
	<nav id="topo" class="navbar navbar-fixed-top navbar-inverse collapse">
		<div class="navbar-inner">
			<div class="container">
				
				<button class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a href="PaginaInicial.jsp" class="brand">
					<img src="../img/notes-edit-icon.png"/>
					<small>Boletim Eletrônico</small>
				</a>
				<!-- Tudo que for escondido a menos de 940px -->
				<div class="nav-collapse collapse">
					<ul class="nav">
						<!-- class="divider-vertical" -->
						<li><a href="PaginaInicial.jsp">Pagina Inicial</a></li>
						<li class="dropdown">
							<a href="" class="dropdown-toggle" data-toggle="dropdown">
								Turma
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="AdicionarTurma.jsp">Adicionar Turma</a></li>
								<li><a href="RemoverTurma.jsp">Remover Turma</a></li>
								<li><a href="EditarTurma.jsp">Editar Turma</a></li>
								<li class="divider"></li>
								<li><a href="RelatorioTurma.jsp">Relatório Turma</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Disciplina
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="AdicionarDisciplina.jsp">Adicionar Disciplina</a></li>
								<li><a href="RemoverDisciplina.jsp">Remover Disciplina</a></li>
								<li><a href="EditarDisciplina.jsp">Editar Disciplina</a></li>
								<li class="divider"></li>
								<li><a href="RelatorioDisciplina.jsp">Relatório de Disciplina</a></li>
							</ul>
						</li>
						<li class="dropdown active">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Atividade
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li class="active"><a href="AdicionarAtividade.jsp">Adicionar Atividade</a></li>
								<li><a href="RemoverAtividade.jsp">Remover Atividade</a></li>
								<li><a href="EditarAtividade.jsp">Editar Atividade</a></li>
								<li class="divider"></li>
								<li><a href="CalendarioAtividade.jsp">Calendário de Atividades</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Boletim
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li class="active"><a href="AdicionarBoletim.jsp">Adicionar Boletim</a></li>
								<li><a href="RemoverBoletim.jsp">Remover Boletim</a></li>
								<li><a href="EditarBoletim.jsp">Editar Boletim</a></li>
								<li class="divider"></li>
								<li><a href="RelatorioBoletim.jsp">Relatório Boletim</a></li>
							</ul>
						</li>
						<li><a>${sessionScope.usuario.nomeUsuario}, está conectado!</a></li>
						<li>
							<form method="post" action="DeslogarConta" class="navbar-form pull-right">
								<input class="btn-primary span2" type="submit" value="Sair"/>
							</form>
						</li>
					</ul>
				</div>
				
			</div>
		</div>
	</nav>
	
	<header class="jumbotron subhead">
		<div class="container">
			<h1>Boletim Eletrônico</h1>
			<p class="lead">A maneira mais eficiente de controlar as suas notas e atividades!</p>
		</div>
	</header>
	
	<%
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	DisciplinaTurmaUsuario dtu = new DisciplinaTurmaUsuario();
	List<DisciplinaTurmaUsuario> dtus = dtu.recuperarDisciplinaPorUsuario(usuario.getIdUsuario());
	%>
	
	<div class="container">
		<table class="span12 table table-striped table-bordered table-hover table-condensed">
			<caption>
				<h3>Disciplinas</h3>
			</caption>
			<thead>
				<tr class="btn-inverse">
					<th>Disciplina</th>
					<th>Dias da Semana</th>
					<th>Ação</th>
				</tr>
			</thead>
			<%
			for(DisciplinaTurmaUsuario d : dtus) {
				out.println("<tbody class=\"default\">");
				out.println("<tr>");
				out.println("<td>" + d.getNomeDisciplina() + "</td>");
				out.println("<td>" + d.getDiaDisciplina() + "</td>");
				out.println("<td><a href=\"SelecionarDisciplina?idDisciplina="+d.getIdDisciplina()
						+"&idTurma="+d.getIdTurma()+"&page=AdicionarAtividade\">Selecionar Disciplina</a></td>");
				out.println("</tr>");
				out.println("</tbody>");
			}
			%>
		</table>
		<center>
			<font color="red">${erroIdDisciplina}</font>
		</center>
	</div>
	
	<div class="container">
		<header class="page-header">
			<div class="container">
				<h1>Adicionar Atividade</h1>
				<p class="lead">Adicione as atividades de suas disciplinas</p>
				<p><font class="span12" color="green">${sucesso}</font></p>
			</div>
			<center>
				<form class="navbar-form pull-left" method="post" action="AdicionarAtividade">
					<input type="hidden" name="txtIdDisciplina" value="${id}"/>
					<div class="control-group input-prepend span12">
						<span class="add-on btn-inverse">Nome da Atividade</span>
							<input type="text" name="txtNomeAtividade" class="span4"
							id="prependInput" placeholder="Nome da Atividade"
							maxlength="20"/>
					</div><br />
					<font color="red">${erroNomeAtividade}</font>
					<div class="control-group input-prepend span12">
						<span class="add-on btn-inverse">Data de Entrega</span>
							<input type="date" name="txtDtEntrega" class="span4"
							id="prependInput" placeholder="Data de Entrega da Atividade"/>
					</div><br />
					<font color="red">${erroDtEntrega}</font>
					<div class="control-group input-prepend span12">
						<span class="add-on btn-inverse">Professor:</span>
							<input type="text" name="txtProfessor" class="span4"
							id="prependInput" placeholder="Nome do Professor"/>
					</div><br />
					<font color="red">${erroProfessor}</font>
					<div class="control-group input-prepend span12">
						<span class="add-on btn-inverse">E-mail do Professor:</span>
							<input type="text" name="txtEmailProfessor" class="span4"
							id="prependInput" placeholder="E-mail do Professor"/>
					</div><br />
					<font color="red">${erroEmailProfessor}</font><br />
					<font color="red"><b>OBS: Separar nomes por ",". Sem espaço antes e depois da ",".</b></font>
					<div class="control-group input-prepend span12">
						<span class="add-on btn-inverse">Colegas envolvidos:</span>
							<input type="text" name="txtAlunoEnv" class="span4"
							id="prependInput" placeholder="Nome dos alunos envolvidos"/>
					</div><br />
					<font color="red">${erroAlunoEnv}</font><br />
					<font color="red"><b>OBS: Separar e-mails por ",". Sem espaço antes e depois da ",".</b></font>
					<div class="control-group input-prepend span12">
						<span class="add-on btn-inverse">E-mails Colegas envolvidos:</span>
							<input type="text" name="txtEmailAlunoEnv" class="span4"
							id="prependInput" placeholder="E-mail dos alunos envolvidos"/>
					</div><br />
					<font color="red">${erroEmailAlunoEnv}</font>
					<div class="control-group input-prepend span12">
						<input type="submit"
						class="btn btn-primary btn-sm pull-left span5"
						value="Adicionar Atividade" />
					</div>
					<div class="control-group input-prepend span12">
						<input type="reset"
						class="btn btn-primary btn-sm pull-left span5"
						value="Cancelar" />
					</div>
				</form>
			</center>
		</header>
	</div>
	
	<footer class="footer">
		<div class="container">
			<p class="pull-rght">
				<a href="#topo">Voltar ao Topo</a>
			</p>
			<p>
				Projeto desenvolvido e criado por 
				<a href="" target="_blank">Antônio Gilvan</a>, 
				<a href="" target="_blank">Rafael Sanzio</a> e
				<a href="" target="_blank">Ricardo Rocha</a>.
			</p>
			<p>
				E-mail para contato:
				<p>Antônio Gilvan: antoniogilvan52@gmail.com,</p>
				<p>Rafael Sanzio: rafael.sanzio@outlook.com e</p>
				<p>Ricardo Rocha: ricardorochak9@gmail.com.</p>
			</p>
		</div>
	</footer>
	
</body>
</html>