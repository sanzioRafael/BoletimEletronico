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
import br.unirondon.cop5.database.TurmaDisciplina;

@WebServlet("/conta/ExcluirDisciplina")
public class ExcluirDisciplina extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExcluirDisciplina() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
		int idTurma = Integer.parseInt(request.getParameter("idTurma"));
		
		TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
		Disciplina disciplina = new Disciplina();
		BoletimAnual ba = new BoletimAnual();
		BoletimSemestral bs = new BoletimSemestral();
		Atividade atividade = new Atividade();
		AtividadeDisciplina atividadeDisciplina = new AtividadeDisciplina();
		AtividadeUsuario atividadeUsuario = new AtividadeUsuario();
		List<AtividadeDisciplina> atvDisc = new ArrayList<AtividadeDisciplina>();
		atvDisc = atividadeDisciplina.recuperarAtividadePorDisciplina(idDisciplina);
		
		for (AtividadeDisciplina atvD : atvDisc) {
			atvD.removerAtividade(atvD.getCodAtividade());
			atividadeUsuario.removerAtividade(atvD.getCodAtividade());
			atividade.removerAtividade(atvD.getCodAtividade());
		}
		
		ba.deletarBoletimAnualPorDisciplina(idDisciplina);
		bs.deletarBoletimSemestralPorDisciplina(idDisciplina);
		turmaDisciplina.deletarPorIds(idTurma, idDisciplina);
		disciplina.deletarDisciplinaPorTurma(idDisciplina);
		
		response.sendRedirect("RemoverDisciplina.jsp");
	}

}
