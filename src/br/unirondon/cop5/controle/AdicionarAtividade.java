package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.Atividade;
import br.unirondon.cop5.database.AtividadeDisciplina;
import br.unirondon.cop5.database.AtividadeUsuario;
import br.unirondon.cop5.database.Disciplina;
import br.unirondon.cop5.database.Usuario;
import br.unirondon.cop5.execao.AtividadeException;
import br.unirondon.cop5.execao.DisciplinaException;

@WebServlet("/conta/AdicionarAtividade")
public class AdicionarAtividade extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Disciplina disciplina;
	private Atividade atividade;
	private AtividadeDisciplina atividadeDisciplina;
	private AtividadeUsuario atividadeUsuario;

	public AdicionarAtividade() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher addAtv = request.getRequestDispatcher("AdicionarAtividade.jsp");
		usuario = (Usuario) request.getSession().getAttribute("usuario");
		disciplina = new Disciplina();
		atividade = new Atividade();
		atividadeDisciplina = new AtividadeDisciplina();
		atividadeUsuario = new AtividadeUsuario();
		String nomeAtividade = request.getParameter("txtNomeAtividade");
		String dtEntrega = request.getParameter("txtDtEntrega");
		String nomeProfessor = request.getParameter("txtProfessor");
		String emailProfessor = request.getParameter("txtEmailProfessor");
		String alunoEnvolvido = request.getParameter("txtAlunoEnv");
		String emailEnvolvido = request.getParameter("txtEmailAlunoEnv");
		int erro = 0;
		int idDisciplina = 0;
		
		try {
			idDisciplina = Integer.parseInt(request.getParameter("txtIdDisciplina"));
		} catch (NumberFormatException e) {
			request.setAttribute("erroIdDisciplina", DisciplinaException.DISCIPLINAOBRIGATORIO);
			erro++;
		}
		
		try {
			disciplina.setIdDisciplina(idDisciplina);
			disciplina.recuperarDisciplinaPorId();
		} catch (DisciplinaException e) {
			request.setAttribute("erroIdDisciplina", e.getMessage());
			erro++;
		}
		
		try {
			atividade.setNomeAtividade(nomeAtividade);
		} catch (AtividadeException e) {
			request.setAttribute("erroNomeAtividade", e.getMessage());
			erro++;
		}
		
		try {
			atividade.setDtEntrega(dtEntrega);
		} catch (AtividadeException e) {
			request.setAttribute("erroDtEntrega", e.getMessage());
			erro++;
		}
		
		try {
			atividadeDisciplina.setNomeProfessor(nomeProfessor);
		} catch (AtividadeException e) {
			request.setAttribute("erroProfessor", e.getMessage());
			erro++;
		}
		
		try {
			atividadeDisciplina.setEmailProfessor(emailProfessor);
		} catch (AtividadeException e) {
			request.setAttribute("erroEmailProfessor", e.getMessage());
			erro++;
		}
		
		try {
			atividadeUsuario.setAlunoEnvolvido(alunoEnvolvido);
		} catch (AtividadeException e) {
			request.setAttribute("erroAlunoEnv", e.getMessage());
			erro++;
		}
		
		try {
			atividadeUsuario.setEmailAlunoEnvolvido(emailEnvolvido);
		} catch (AtividadeException e) {
			request.setAttribute("erroEmailAlunoEnv", e.getMessage());
			erro++;
		}
		
		if(erro == 0) {
			if (!atividade.existeAtividade(nomeAtividade, atividade.getDtEntrega())) {
				atividade.adicionarAtividade();
				atividade.recuperarCodAtividade(nomeAtividade, atividade.getDtEntrega());
				
				atividadeDisciplina.setCodAtividade(atividade.getCodAtividade());
				atividadeDisciplina.setIdDisciplina(disciplina.getIdDisciplina());
				atividadeDisciplina.adicionarAtividadeDisciplina();
				
				atividadeUsuario.setCodAtividade(atividade.getCodAtividade());
				atividadeUsuario.setIdUsuario(usuario.getIdUsuario());
				atividadeUsuario.adicionarAtividadeUsuario();
				
				request.setAttribute("sucesso", "Atividade adicionada com sucesso");
				addAtv.forward(request, response);
			} else {
				request.setAttribute("erroNomeAtividade", "Atividade já é existente");
				request.setAttribute("erroDtEntrega", "Atividade já é existente");
				addAtv.forward(request, response);
			}
		} else
			addAtv.forward(request, response);
	}

}
