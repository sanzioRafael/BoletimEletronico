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

@WebServlet("/conta/SelecionarBoletim")
public class SelecionarBoletim extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SelecionarBoletim() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher edtBlm = request.getRequestDispatcher("EditarBoletim.jsp");
		int id = Integer.parseInt(request.getParameter("codBoletim"));
		int size = Integer.parseInt(request.getParameter("size"));
		BoletimAnual ba = new BoletimAnual();
		BoletimSemestral bs = new BoletimSemestral();
		
		if (size == 2) {
			bs.recuperarBoletimSemestralPorId(id);
			
			request.setAttribute("id", bs.getCodBoletimSemestral());
			request.setAttribute("tipo", "Semestral");
			request.setAttribute("size", size);
			request.setAttribute("notaMedia", bs.getNotaMedia());
			request.setAttribute("recuperacao", bs.getRecuperacao());
			request.setAttribute("pf", bs.getProvaFinal());
			
			edtBlm.forward(request, response);
		} else if (size == 4) {
			ba.recuperarBoletimAnualPorId(id);
			
			request.setAttribute("id", bs.getCodBoletimSemestral());
			request.setAttribute("tipo", "Anual");
			request.setAttribute("size", size);
			request.setAttribute("notaMedia", ba.getNotaMedia());
			request.setAttribute("recuperacao", ba.getRecuperacao());
			request.setAttribute("pf", ba.getProvaFinal());
			
			edtBlm.forward(request, response);
		}
		
	}

}
