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
import br.unirondon.cop5.database.TurmaDisciplina;
import br.unirondon.cop5.execao.DisciplinaException;
import br.unirondon.cop5.execao.TurmaException;

@WebServlet("/conta/AdicionarDisciplina")
public class AdicionarDisciplina extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Disciplina disciplina;
	private Turma turma;
	private TurmaDisciplina turmaDisciplina;
	
	public AdicionarDisciplina() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		disciplina = new Disciplina();
		turma = new Turma();
		turmaDisciplina = new TurmaDisciplina();
		RequestDispatcher addDisc = request.getRequestDispatcher("AdicionarDisciplina.jsp");
		int idTurma;
		try {
			idTurma = request.getParameter("options") == null
					? 0 : Integer.parseInt(request.getParameter("options"));
		} catch (NullPointerException e) {
			idTurma = 0;
		}
		String curso = request.getParameter("txtCurso");
		String[] dias = request.getParameterValues("dias");
		int erro = 0;
		
		try {
			turma.setIdTurma(idTurma);
			turma.recuperarTurmaPorID(turma.getIdTurma());
			disciplina.setTurma(turma);
			turmaDisciplina.setTurma(turma);
		} catch (TurmaException e) {
			request.setAttribute("erroTurma", e.getMessage());
			erro++;
		}
		
		try {
			disciplina.setNomeDisciplina(curso);
		} catch (DisciplinaException e) {
			request.setAttribute("erroDisciplina", e.getMessage());
			erro++;
		}
		
		String dia = "";
		
		try {
			
			for (int i = 0; i < dias.length; i++) {
				if(i != (dias.length - 1))
					dia += dias[i] + ",";
				else
					dia += dias[i];
			}
		} catch(NullPointerException e) {
			request.setAttribute("erroDias", DisciplinaException.DIASDISCIPLINAOBRIGATORIO);
			erro++;
		} 
		
		try {
			disciplina.setDiaDisciplina(dia);
		} catch (DisciplinaException e) {
			request.setAttribute("erroDias", e.getMessage());
			erro++;
		}
		
		if(erro == 0) {
			disciplina.adicionarDisciplina();
			disciplina.recuperarIdDisciplina();
			turmaDisciplina.setDisciplina(disciplina);
			turmaDisciplina.adicionarTurmaDisciplina();
			request.setAttribute("sucesso", "Disciplina Adicionada Com sucesso");
			addDisc.forward(request, response);
		} else {
			addDisc.forward(request, response);
		}
		
	}

}
