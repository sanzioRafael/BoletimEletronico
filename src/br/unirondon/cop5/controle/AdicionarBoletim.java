package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.BoletimAnual;
import br.unirondon.cop5.database.BoletimSemestral;
import br.unirondon.cop5.execao.BoletimException;

@WebServlet("/conta/AdicionarBoletim")
public class AdicionarBoletim extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoletimAnual ba;
	private BoletimSemestral bs;
	
	public AdicionarBoletim() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher addBlm = request.getRequestDispatcher("AdicionarBoletim.jsp");
		String tipo = request.getParameter("txtTipo");
		int idDisciplina = 0;
		try {
			idDisciplina = Integer.parseInt(request.getParameter("txtIdDisciplina"));
		} catch (NumberFormatException e) {
			request.setAttribute("erroIdDisciplina", "Selecione uma Disciplina");
		}
		int erro = 0;
		String erroNota = "";
		
		if(!tipo.isEmpty()) {
			if(tipo.equalsIgnoreCase("Anual")) {
				ba = new BoletimAnual();
				String nota1 = request.getParameter("txtNota1");
				String nota2 = request.getParameter("txtNota2");
				String nota3 = request.getParameter("txtNota3");
				String nota4 = request.getParameter("txtNota4");
				String notaMedia = request.getParameter("txtNotaMedia");
				String recuperacao = request.getParameter("txtRecuperacao");
				String pf = request.getParameter("txtPF");
				
				try {
					ba.setIdDisciplina(idDisciplina);
				} catch (BoletimException e) {
					request.setAttribute("erroIdDisciplina", e.getMessage());
					erro++;
				}
				
				try {
					ba.setNota1(nota1);
				} catch (BoletimException e) {
					erro++;
					erroNota += e.getMessage()+"<br />";
				}
				
				try {
					ba.setNota2(nota2);
				} catch (BoletimException e) {
					erro++;
					erroNota += e.getMessage()+"<br />";
				}
				
				try {
					ba.setNota3(nota3);
				} catch (BoletimException e) {
					erro++;
					erroNota += e.getMessage()+"<br />";
				}
				
				try {
					ba.setNota4(nota4);
				} catch (BoletimException e) {
					erro++;
					erroNota += e.getMessage()+"<br />";
				}
				
				try {
					ba.setNotaMedia(notaMedia);
				} catch (BoletimException e) {
					request.setAttribute("erroNotaMedia", e.getMessage());
					erro++;
				}
				
				try {
					ba.setRecuperacao(recuperacao);
				} catch (BoletimException e) {
					request.setAttribute("erroRecuperacao", e.getMessage());
					erro++;
				}
				
				try {
					ba.setProvaFinal(pf);
				} catch (BoletimException e) {
					request.setAttribute("erroPF", e.getMessage());
					erro++;
				}
				
				if(erro == 0) {
					double media = (ba.getNota1() + ba.getNota2() + ba.getNota3() + ba.getNota4())/4;
					
					try {
						ba.setMedia(String.valueOf(media));
					} catch (BoletimException e) {
						e.printStackTrace();
					}
					ba.adicionarBoletimAnual();
					request.setAttribute("sucesso", "Boletim Anual adicionado com sucesso!");
					addBlm.forward(request, response);
				} else {
					request.setAttribute("erroNota", erroNota);
					addBlm.forward(request, response);
				}
			} else if(tipo.equalsIgnoreCase("Semestral")) {
				bs = new BoletimSemestral();
				String nota1 = request.getParameter("txtNota1");
				String nota2 = request.getParameter("txtNota2");
				String notaMedia = request.getParameter("txtNotaMedia");
				String recuperacao = request.getParameter("txtRecuperacao");
				String pf = request.getParameter("txtPF");

				try {
					bs.setIdDisciplina(idDisciplina);
				} catch (BoletimException e) {
					request.setAttribute("erroIdDisciplina", e.getMessage());
					erro++;
				}
				
				try {
					bs.setNota1(nota1);
				} catch (BoletimException e) {
					erro++;
					erroNota += e.getMessage()+"<br />";
				}
				
				try {
					bs.setNota2(nota2);
				} catch (BoletimException e) {
					erro++;
					erroNota += e.getMessage()+"<br />";
				}

				try {
					bs.setNotaMedia(notaMedia);
				} catch (BoletimException e) {
					request.setAttribute("erroNotaMedia", e.getMessage());
					erro++;
				}
				try {
					bs.setRecuperacao(recuperacao);
				} catch (BoletimException e) {
					request.setAttribute("erroRecuperacao", e.getMessage());
					erro++;
				}
				
				try {
					bs.setProvaFinal(pf);
				} catch (BoletimException e) {
					request.setAttribute("erroPF", e.getMessage());
					erro++;
				}

				if(erro == 0) {
					double media = (bs.getNota1() + bs.getNota2())/2;
					
					try {
						bs.setMedia(String.valueOf(media));
					} catch (BoletimException e) {
						e.printStackTrace();
					}
					bs.adicionarBoletimSemestral();
					request.setAttribute("sucesso", "Boletim Semestral adicionado com sucesso!");
					addBlm.forward(request, response);
				} else {
					request.setAttribute("erroNota", erroNota);
					addBlm.forward(request, response);
				}
			}
		} else {
			request.setAttribute("erroIdDisciplina", "Selecione uma disciplina");
			addBlm.forward(request, response);
		}
	}

}
