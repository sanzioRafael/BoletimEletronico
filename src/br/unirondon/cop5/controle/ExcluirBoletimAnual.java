package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.BoletimAnual;

@WebServlet("/conta/ExcluirBoletimAnual")
public class ExcluirBoletimAnual extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExcluirBoletimAnual() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BoletimAnual ba = new BoletimAnual();
		int codBoletimAnual = Integer.parseInt(request.getParameter("codBoletimAnual"));
		
		ba.deletarBoletimAnual(codBoletimAnual);
		
		response.sendRedirect("RemoverBoletim.jsp");
	}

}
