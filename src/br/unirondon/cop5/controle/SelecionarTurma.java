package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.Turma;

@WebServlet("/conta/SelecionarTurma")
public class SelecionarTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelecionarTurma() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher editPag = request.getRequestDispatcher("EditarTurma.jsp");
		int idTurma = Integer.parseInt(request.getParameter("idTurma"));
		Turma turma = new Turma();
		turma = turma.recuperarTurmaPorID(idTurma);
		
		request.setAttribute("id", turma.getIdTurma());
		request.setAttribute("instEnsino", turma.getInstEnsino());
		request.setAttribute("curso", turma.getCursoTurma());
		
		editPag.forward(request, response);
		
	}

}
