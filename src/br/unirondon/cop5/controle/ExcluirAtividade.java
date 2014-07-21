package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.Atividade;
import br.unirondon.cop5.database.AtividadeDisciplina;
import br.unirondon.cop5.database.AtividadeUsuario;

@WebServlet("/conta/ExcluirAtividade")
public class ExcluirAtividade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExcluirAtividade() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int codAtividade = Integer.parseInt(request.getParameter("codAtividade"));
		Atividade atividade = new Atividade();
		AtividadeDisciplina atividadeDisciplina = new AtividadeDisciplina();
		AtividadeUsuario atividadeUsuario = new AtividadeUsuario();
		
		atividadeUsuario.removerAtividade(codAtividade);
		atividadeDisciplina.removerAtividade(codAtividade);
		atividade.removerAtividade(codAtividade);
		
		response.sendRedirect("RemoverAtividade.jsp");
	}

}
