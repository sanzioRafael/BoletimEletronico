<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.unirondon.cop5.database.Usuario"%>
<%@page import="br.unirondon.cop5.database.BoletimAnualCompleto"%>
<%@page import="br.unirondon.cop5.database.BoletimSemestralCompleto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
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
<title>Editar Boletim</title>
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
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Atividade
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="AdicionarAtividade.jsp">Adicionar Atividade</a></li>
								<li><a href="RemoverAtividade.jsp">Remover Atividade</a></li>
								<li><a href="EditarAtividade.jsp">Editar Atividade</a></li>
								<li class="divider"></li>
								<li><a href="CalendarioAtividade.jsp">Calendário de Atividades</a></li>
							</ul>
						</li>
						<li class="dropdown active">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Boletim
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="AdicionarBoletim.jsp">Adicionar Boletim</a></li>
								<li><a href="RemoverBoletim.jsp">Remover Boletim</a></li>
								<li class="active"><a href="EditarBoletim.jsp">Editar Boletim</a></li>
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
			<p><font class="span12" color="green">${sucesso}</font></p>
		</div>
	</header>
	
	<%
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	BoletimAnualCompleto bac = new BoletimAnualCompleto();
	BoletimSemestralCompleto bsc = new BoletimSemestralCompleto();
	List<BoletimAnualCompleto> bacs = new ArrayList<BoletimAnualCompleto>();
	List<BoletimSemestralCompleto> bscs = new ArrayList<BoletimSemestralCompleto>();
	
	bac.setIdUsuario(usuario.getIdUsuario());
	bsc.setIdUsuario(usuario.getIdUsuario());
	
	bacs = bac.recuperarBoletimAnualPorUsuario();
	bscs = bsc.recuperarBoletimSemestralPorUsuario();
	%>
	
	<div class="container">
		<table class="span12 table table-striped table-bordered table-hover table-condensed">
			<caption>
				<h3>Boletins Anuais</h3>
			</caption>
			<thead>
				<tr class="btn-inverse">
					<th>Disciplina</th>
					<th>Nota 1° Bimestre</th>
					<th>Nota 2° Bimestre</th>
					<th>Nota 3° Bimestre</th>
					<th>Nota 4° Bimestre</th>
					<th>Média</th>
					<th>Recuperação</th>
					<th>Prova Final</th>
					<th>Ação</th>
				</tr>
			</thead>
			<%
			for (BoletimAnualCompleto ba : bacs) {
				out.println("<tbody class=\"default\">");
				out.println("<tr>");
				out.println("<td>" + ba.getNomeDisciplina() + "</td>");
				out.println(ba.getNota1() >= ba.getNotaMedia()
						?"<td><font color=\"green\">" + ba.getNota1() + "</font></td>"
						:"<td><font color=\"red\">" + ba.getNota1() + "</font></td>");
				out.println(ba.getNota2() >= ba.getNotaMedia()
						?"<td><font color=\"green\">" + ba.getNota2() + "</font></td>"
						:"<td><font color=\"red\">" + ba.getNota2() + "</font></td>");
				out.println(ba.getNota3() >= ba.getNotaMedia()
						?"<td><font color=\"green\">" + ba.getNota3() + "</font></td>"
						:"<td><font color=\"red\">" + ba.getNota3() + "</font></td>");
				out.println(ba.getNota4() >= ba.getNotaMedia()
						?"<td><font color=\"green\">" + ba.getNota4() + "</font></td>"
						:"<td><font color=\"red\">" + ba.getNota4() + "</font></td>");
				out.println(ba.getMedia() >= ba.getNotaMedia()
						?"<td><font color=\"green\">" + ba.getMedia() + "</font></td>"
						:"<td><font color=\"red\">" + ba.getMedia() + "</font></td>");
				out.println(ba.getRecuperacao() >= ba.getNotaMedia()
						?"<td><font color=\"green\">" + ba.getRecuperacao() + "</font></td>"
						:"<td><font color=\"red\">" + ba.getRecuperacao() + "</font></td>");
				out.println(ba.getProvaFinal() >= ba.getNotaMedia()
						?"<td><font color=\"green\">" + ba.getProvaFinal() + "</font></td>"
						:"<td><font color=\"red\">" + ba.getProvaFinal() + "</font></td>");
				out.println("<td><a href=\"SelecionarBoletim?codBoletim=" +
						ba.getCodBoletimAnual() + "&size=4\">Selecionar Boletim Anual</a></td>");
				out.println("</tr>");
				out.println("</tbody>");
			}
			%>
		</table>
	</div>
	
	<div class="container">
		<table class="span12 table table-striped table-bordered table-hover table-condensed">
			<caption>
				<h3>Boletins Semestrais</h3>
			</caption>
			<thead>
				<tr class="btn-inverse">
					<th>Disciplina</th>
					<th>Nota 1° Bimestre</th>
					<th>Nota 2° Bimestre</th>
					<th>Média</th>
					<th>Recuperação</th>
					<th>Prova Final</th>
					<th>Ação</th>
				</tr>
			</thead>
			<%
			for (BoletimSemestralCompleto bs : bscs) {
				out.println("<tbody class=\"default\">");
				out.println("<tr>");
				out.println("<td>" + bs.getNomeDisciplina() + "</td>");
				out.println(bs.getNota1() >= bs.getNotaMedia()
						?"<td><font color=\"green\">" + bs.getNota1() + "</font></td>"
						:"<td><font color=\"red\">" + bs.getNota1() + "</font></td>");
				out.println(bs.getNota2() >= bs.getNotaMedia()
						?"<td><font color=\"green\">" + bs.getNota2() + "</font></td>"
						:"<td><font color=\"red\">" + bs.getNota2() + "</font></td>");
				out.println(bs.getMedia() >= bs.getNotaMedia()
						?"<td><font color=\"green\">" + bs.getMedia() + "</font></td>"
						:"<td><font color=\"red\">" + bs.getMedia() + "</font></td>");
				out.println(bs.getRecuperacao() >= bs.getNotaMedia()
						?"<td><font color=\"green\">" + bs.getRecuperacao() + "</font></td>"
						:"<td><font color=\"red\">" + bs.getRecuperacao() + "</font></td>");
				out.println(bs.getProvaFinal() >= bs.getNotaMedia()
						?"<td><font color=\"green\">" + bs.getProvaFinal() + "</font></td>"
						:"<td><font color=\"red\">" + bs.getProvaFinal() + "</font></td>");
				out.println("<td><a href=\"SelecionarBoletim?codBoletim=" +
						bs.getCodBoletimSemestral() + "&size=2\">Selecionar Boletim Semestral</a></td>");
				out.println("</tr>");
				out.println("</tbody>");
			}
			%>
		</table>
		<center>
			<font color="red">${erroBoletim}</font>
		</center>
	</div>
	
	<div class="container">
		<header class="page-header">
			<div class="container">
				<h1>Editar Boletim</h1>
				<p class="lead">Edite o Boletim</p>
				<p><font class="span12" color="green">${sucesso}</font></p>
			</div>
		</header>
		<center>
			<form class="navbar-form pull-left" method="post" action="EditarBoletim">
				<input type="hidden" name="txtIdBoletim" value="${id}"/>
				<input type="hidden" name="txtTipo" value="${tipo}"/>
				<%
				int size = request.getAttribute("size") == null ? 0 : (Integer) request.getAttribute("size");
				
				for(int i = 0; i < size; i++) {
					out.println("<div class=\"control-group input-prepend span12\">");
					out.println("<span class=\"add-on btn-inverse\">"+(i+1)+"ª Nota</span>");
					out.println("<input type=\"text\" name=\"txtNota"+(i+1)+"\" class=\"span4\" id=\"prependInput\""
							+"placeholder=\"Digite a "+(i+1)+"ª nota\"/>");
					out.println("</div><br />");
				}
				%>
				<font color="red">${erroNota}</font>
				<div class="control-group input-prepend span12">
					<span class="add-on btn-inverse">Nota Média</span>
					<input type="text" name="txtNotaMedia" class="span4" id="prependInput"
					placeholder="Recuperação" value="${notaMedia}"/>
				</div><br />
				<font color="red">${erroNotaMedia}</font>
				<div class="control-group input-prepend span12">
					<span class="add-on btn-inverse">Recuperação</span>
					<input type="text" name="txtRecuperacao" class="span4" id="prependInput"
					placeholder="Recuperação" value="${recuperacao}"/>
				</div><br />
				<font color="red">${erroRecuperacao}</font>
				<div class="control-group input-prepend span12">
					<span class="add-on btn-inverse">Prova Final</span>
					<input type="text" name="txtPF" class="span4" id="prependInput"
					placeholder="Prova Final" value="${pf}"/>
				</div><br />
				<font color="red">${erroPF}</font>
				<div class="control-group input-prepend span12">
					<input type="submit" class="btn btn-primary btn-sm pull-left span5" value="Editar Boletim" />
				</div>
				<div class="control-group input-prepend span12">
					<input type="reset" class="btn btn-primary btn-sm pull-left span5" value="Cancelar" />
				</div>
			</form>
		</center>
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