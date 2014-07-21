package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.Disciplina;
import br.unirondon.cop5.execao.DisciplinaException;

@WebServlet("/conta/EditarDisciplina")
public class EditarDisciplina extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Disciplina disciplina;
	
	public EditarDisciplina() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher edtDisc = request.getRequestDispatcher("EditarDisciplina.jsp");
		disciplina = new Disciplina();
		String idDisciplina = request.getParameter("txtIdDisciplina");
		String nomeDisciplina = request.getParameter("txtDisciplina");
		String[] dias = request.getParameterValues("dias");
		int erro = 0;
		
		try {
			disciplina.setIdDisciplina(Integer.parseInt(idDisciplina));
		} catch (DisciplinaException e) {
			request.setAttribute("erroIdDisciplina", e.getMessage());
			erro++;
		} catch (NumberFormatException e) {
			request.setAttribute("erroIdDisciplina", DisciplinaException.DISCIPLINAOBRIGATORIO);
			erro++;
		}
		
		try {
			disciplina.setNomeDisciplina(nomeDisciplina);
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
			disciplina.alterarDisciplina();
			request.setAttribute("sucesso", "Disciplina Alterada com sucesso");
			edtDisc.forward(request, response);
		} else {
			edtDisc.forward(request, response);
		}
	}

}
