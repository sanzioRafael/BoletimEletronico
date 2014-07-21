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

@WebServlet("/conta/EditarTurma")
public class EditarTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Turma turma;
	
	public EditarTurma() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		usuario = (Usuario) request.getSession().getAttribute("usuario");
		turma = new Turma();
		String idTurma = request.getParameter("txtIdTurma");
		String intituicao = request.getParameter("txtInstitucao");
		String curso = request.getParameter("txtCurso");
		String periodo = request.getParameter("cmbPeriodo");
		String periodoLetivo = request.getParameter("cmbPeriodoLetivo");
		int erroEdtTurma = 0;
		RequestDispatcher editTurma = request.getRequestDispatcher("EditarTurma.jsp");
		
		if(idTurma.isEmpty() || idTurma == null) {
			erroEdtTurma++;
		} else
			turma.setIdUsuario(usuario.getIdUsuario());
			
		try {
			turma.setIdTurma(Integer.parseInt(idTurma));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (TurmaException e) {
			e.printStackTrace();
		}
		
		try {
			turma.setInstEnsino(intituicao);
		} catch (TurmaException e) {
			request.setAttribute("erroInstituicao", e.getMessage());
			erroEdtTurma++;
		}
		turma.setPeriodoTurma(periodo);
		turma.setPeriodoLetivoTurma(periodoLetivo);
		try {
			turma.setCursoTurma(curso);
		} catch (TurmaException e) {
			request.setAttribute("erroCurso", e.getMessage());
			erroEdtTurma++;
		}
		
		if(erroEdtTurma == 0) {
			turma.editarTurma();
			request.setAttribute("sucesso", "Turma alterada com sucesso!");
			editTurma.forward(request, response);
		} else
			editTurma.forward(request, response);
	}

}
