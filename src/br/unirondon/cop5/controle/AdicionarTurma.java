package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.Turma;
import br.unirondon.cop5.database.Usuario;
import br.unirondon.cop5.execao.TurmaException;

@WebServlet("/conta/AdicionarTurma")
public class AdicionarTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Turma turma;
	
	public AdicionarTurma() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		usuario = (Usuario) request.getSession().getAttribute("usuario");
		turma = new Turma();
		String intituicao = request.getParameter("txtInstitucao");
		String curso = request.getParameter("txtCurso");
		String periodo = request.getParameter("cmbPeriodo");
		String periodoLetivo = request.getParameter("cmbPeriodoLetivo");
		int erroAddTurma = 0;
		RequestDispatcher addTurma = request.getRequestDispatcher("AdicionarTurma.jsp");
		
		turma.setIdUsuario(usuario.getIdUsuario());
		try {
			turma.setInstEnsino(intituicao);
		} catch (TurmaException e) {
			request.setAttribute("erroInstituicao", e.getMessage());
			erroAddTurma++;
		}
		turma.setPeriodoTurma(periodo);
		turma.setPeriodoLetivoTurma(periodoLetivo);
		try {
			turma.setCursoTurma(curso);
		} catch (TurmaException e) {
			request.setAttribute("erroCurso", e.getMessage());
			erroAddTurma++;
		}
		
		if(erroAddTurma == 0) {
			turma.adicionarTurma();
			request.setAttribute("sucesso", "Turma adicionada com sucesso!");
			addTurma.forward(request, response);
		} else
			addTurma.forward(request, response);
	}

}
