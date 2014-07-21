package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.Disciplina;
import br.unirondon.cop5.database.Turma;
import br.unirondon.cop5.execao.DisciplinaException;
import br.unirondon.cop5.execao.TurmaException;

@WebServlet("/conta/SelecionarDisciplina")
public class SelecionarDisciplina extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Disciplina disciplina;
	private Turma turma;
	
	public SelecionarDisciplina() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher edtDisc = request.getRequestDispatcher("EditarDisciplina.jsp");
		RequestDispatcher addBlm = request.getRequestDispatcher("AdicionarBoletim.jsp");
		RequestDispatcher addAtv = request.getRequestDispatcher("AdicionarAtividade.jsp");
		disciplina = new Disciplina();
		turma = new Turma();
		int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
		int idTurma = Integer.parseInt(request.getParameter("idTurma"));
		String page = request.getParameter("page");
		
		if(page.equals("EditarDisciplina")) {
			try {
				disciplina.setIdDisciplina(idDisciplina);
				disciplina.recuperarDisciplinaPorId();
			} catch (DisciplinaException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("id", disciplina.getIdDisciplina());
			request.setAttribute("disciplina", disciplina.getNomeDisciplina());
			
			edtDisc.forward(request, response);
		} else if (page.equals("AdicionarBoletim")) {
			try {
				disciplina.setIdDisciplina(idDisciplina);
				disciplina.recuperarDisciplinaPorId();
			} catch (DisciplinaException e) {
				e.printStackTrace();
			}
			
			try {
				turma.setIdTurma(idTurma);
			} catch (TurmaException e) {
				e.printStackTrace();
			}
			
			turma.recuperarTurmaPorID(idTurma);
			request.setAttribute("id", disciplina.getIdDisciplina());
			
			if(turma.getPeriodoLetivoTurma().equalsIgnoreCase("Semestral")) {
				request.setAttribute("size", 2);
				request.setAttribute("tipo", "Semestral");
			} else {
				request.setAttribute("size", 4);
				request.setAttribute("tipo", "Anual");
			}
			
			addBlm.forward(request, response);
		} else if (page.equals("AdicionarAtividade")) {
			try {
				disciplina.setIdDisciplina(idDisciplina);
				disciplina.recuperarDisciplinaPorId();
			} catch (DisciplinaException e) {
				e.printStackTrace();
			}
			
			try {
				turma.setIdTurma(idTurma);
			} catch (TurmaException e) {
				e.printStackTrace();
			}
			
			turma.recuperarTurmaPorID(idTurma);
			request.setAttribute("id", disciplina.getIdDisciplina());
			addAtv.forward(request, response);
		}
		
	}

}
