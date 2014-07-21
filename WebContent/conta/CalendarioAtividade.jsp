<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="br.unirondon.cop5.database.AtividadeCompleto"%>
<%@page import="br.unirondon.cop5.database.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Calnedário de Atividades</title>
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
								<li><a href="AdicionarAtividade.jsp">Adicionar Atividade</a></li>
								<li><a href="RemoverAtividade.jsp">Remover Atividade</a></li>
								<li><a href="EditarAtividade.jsp">Editar Atividade</a></li>
								<li class="divider"></li>
								<li class="active"><a href="CalendarioAtividade.jsp">Calendário de Atividades</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Boletim
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="AdicionarBoletim.jsp">Adicionar Boletim</a></li>
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
	AtividadeCompleto atividade = new AtividadeCompleto();
	List<AtividadeCompleto> atividades = atividade.recuperarAtividadePorUsuario(usuario.getIdUsuario());
	String[] date;
	int aux;
	%>
	
	<div class="container">
		<table class="span12 table table-striped table-bordered table-hover table-condensed">
			<caption>
				<h3><%=Calendar.getInstance().get(Calendar.YEAR)%></h3>
			</caption>
			<thead>
				<tr class="btn-inverse span12">
					<th class="span4">Janeiro</th>
					<th class="span4">Fevereiro</th>
					<th class="span4">Março</th>
					<th class="span4">Abril</th>
				</tr>
			</thead>
			<tbody>
				<tr class="span12">
					<td class="span4">
					<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 1) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 2) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 3) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 4) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr class="btn-inverse span12">
					<th class="span4">Maio</th>
					<th class="span4">Junho</th>
					<th class="span4">Julho</th>
					<th class="span4">Agosto</th>
				</tr>
			</thead>
			<tbody>
				<tr class="span12">
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 5) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 6) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 7) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 8) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr class="btn-inverse span12">
					<th class="span4">Setembro</th>
					<th class="span4">Outubro</th>
					<th class="span4">Novembro</th>
					<th class="span4">Dezembro</th>
				</tr>
			</thead>
			<tbody>
				<tr class="span12">
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 9) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 10) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 11) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
					<td class="span4">
						<%
						for (int i = 0; i < atividades.size(); i++) {
							date = atividades.get(i).getDtEntrega().split("/");
							if (Integer.parseInt(date[2]) == Calendar.getInstance().get(Calendar.YEAR)
									&& Integer.parseInt(date[1]) == 12) {
								out.print("<b>" + atividades.get(i).getNomeAtividade() +
										" - " + atividades.get(i).getDtEntrega() + "<b/><br />");
							}
						}
						%>
					</td>
				</tr>
			</tbody>
		</table>
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