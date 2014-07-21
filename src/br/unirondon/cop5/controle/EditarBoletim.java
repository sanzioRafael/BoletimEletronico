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

@WebServlet("/conta/EditarBoletim")
public class EditarBoletim extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoletimAnual ba;
	private BoletimSemestral bs;
	
	public EditarBoletim() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher edtBlm = request.getRequestDispatcher("EditarBoletim.jsp");
		String tipo = request.getParameter("txtTipo");
		int codBoletim = 0;
		int erro = 0;
		String erroNota = "";
		
		System.out.println(tipo);
		
		try {
			codBoletim = Integer.parseInt(request.getParameter("txtIdBoletim"));
		} catch (NumberFormatException e) {
			request.setAttribute("erroIdDisciplina", "Selecione um Boletim");
		}
		
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
				
				ba.setCodBoletimAnual(codBoletim);
				
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
					
					ba.alterarBoletimAnualPorId();
					
					request.setAttribute("sucesso", "Boletim Anual alterado com sucesso!");
					edtBlm.forward(request, response);
				} else {
					request.setAttribute("erroNota", erroNota);
					edtBlm.forward(request, response);
				}
			} else if(tipo.equalsIgnoreCase("Semestral")) {
				bs = new BoletimSemestral();
				String nota1 = request.getParameter("txtNota1");
				String nota2 = request.getParameter("txtNota2");
				String notaMedia = request.getParameter("txtNotaMedia");
				String recuperacao = request.getParameter("txtRecuperacao");
				String pf = request.getParameter("txtPF");

				bs.setCodBoletimSemestral(codBoletim);
				
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
					
					bs.alterarBoletimSemestralPorId();
					
					request.setAttribute("sucesso", "Boletim Semestral alterado com sucesso!");
					edtBlm.forward(request, response);
				} else {
					request.setAttribute("erroNota", erroNota);
					edtBlm.forward(request, response);
				}
			}
		} else {
			request.setAttribute("erroBoletim", "Selecione uma Boletim");
			edtBlm.forward(request, response);
		}
		
	}

}
