package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.BoletimSemestral;

@WebServlet("/conta/ExcluirBoletimSemestral")
public class ExcluirBoletimSemestral extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExcluirBoletimSemestral() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BoletimSemestral bs = new BoletimSemestral();
		int codBoletimSemestral = Integer.parseInt(request.getParameter("codBoletimSemestral"));
		
		bs.deletarBoletimSemestral(codBoletimSemestral);
		
		response.sendRedirect("RemoverBoletim.jsp");
	}

}
