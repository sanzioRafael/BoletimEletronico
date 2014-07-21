package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.AtividadeCompleto;

@WebServlet("/conta/SelecionarAtividade")
public class SelecionarAtividade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelecionarAtividade() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher edtAtv = request.getRequestDispatcher("EditarAtividade.jsp");
		AtividadeCompleto atividade = new AtividadeCompleto();
		int codAtividade = Integer.parseInt(request.getParameter("codAtividade"));
		
		atividade.recuperarAtividade(codAtividade);
		
		request.setAttribute("id", codAtividade);
		request.setAttribute("idDisciplina", atividade.getIdDisciplina());
		request.setAttribute("atividade", atividade.getNomeAtividade());
		request.setAttribute("dtEntrega", atividade.getDtEntrega());
		request.setAttribute("professor", atividade.getNomeProfessor());
		request.setAttribute("emailProfessor", atividade.getEmailProfessor());
		request.setAttribute("alunoEnvolvido", atividade.getAlunoEnvolvido());
		request.setAttribute("emailAlunoEnvolvido", atividade.getEmailAlunoEnvolvido());
		edtAtv.forward(request, response);
	}

}
