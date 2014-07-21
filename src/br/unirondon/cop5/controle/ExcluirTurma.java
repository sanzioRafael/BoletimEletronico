package br.unirondon.cop5.controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.Atividade;
import br.unirondon.cop5.database.AtividadeDisciplina;
import br.unirondon.cop5.database.AtividadeUsuario;
import br.unirondon.cop5.database.BoletimAnual;
import br.unirondon.cop5.database.BoletimSemestral;
import br.unirondon.cop5.database.Disciplina;
import br.unirondon.cop5.database.Turma;
import br.unirondon.cop5.database.TurmaDisciplina;

@WebServlet("/conta/ExcluirTurma")
public class ExcluirTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExcluirTurma() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idTurma = request.getParameter("idTurma");
		Turma turma = new Turma();
		Disciplina disciplina = new Disciplina();
		BoletimAnual ba = new BoletimAnual();
		BoletimSemestral bs = new BoletimSemestral();
		TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
		Atividade atividade = new Atividade();
		AtividadeDisciplina atividadeDisciplina = new AtividadeDisciplina();
		AtividadeUsuario atividadeUsuario = new AtividadeUsuario();
		List<AtividadeDisciplina> atvDisc = new ArrayList<AtividadeDisciplina>();
		List<TurmaDisciplina> turmaDisciplinas = new ArrayList<TurmaDisciplina>();
		
		turma = turma.recuperarTurmaPorID(Integer.parseInt(idTurma));
		turmaDisciplinas = turmaDisciplina.recuperarTurmas(turma.getIdTurma());
		
		if(turmaDisciplinas.size() > 0) {
			
			for (TurmaDisciplina t : turmaDisciplinas) {
				
				atvDisc = atividadeDisciplina.recuperarAtividadePorDisciplina(t.getDisciplina().getIdDisciplina());
				
				for (AtividadeDisciplina atvD : atvDisc) {
					atvD.removerAtividade(atvD.getCodAtividade());
					atividadeUsuario.removerAtividade(atvD.getCodAtividade());
					atividade.removerAtividade(atvD.getCodAtividade());
				}
				
				ba.deletarBoletimAnual(t.getDisciplina().getIdDisciplina());
				bs.deletarBoletimSemestral(t.getDisciplina().getIdDisciplina());
				t.deletarPorIds(t.getTurma().getIdTurma(), t.getDisciplina().getIdDisciplina());
				disciplina.deletarDisciplinaPorTurma(t.getDisciplina().getIdDisciplina());
			}
			
			response.sendRedirect("ExcluirTurma?idTurma="+turma.getIdTurma());
			
		} else if(turmaDisciplinas.size() == 0) {
			turma.removerTurma(turma.getIdTurma());
			response.sendRedirect("RemoverTurma.jsp");
		}
		
	}

}
