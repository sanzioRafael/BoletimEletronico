package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unirondon.cop5.database.Usuario;
import br.unirondon.cop5.execao.UsuarioException;

@WebServlet("/LogarConta")
public class LogarConta extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	public LogarConta() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		usuario = new Usuario();
		String login = request.getParameter("txtLogin");
		String senha = request.getParameter("txtSenha");
		RequestDispatcher logar = request.getRequestDispatcher("/");
		
		if (!login.isEmpty() && !senha.isEmpty()) {
			try {
								
				if (usuario.logarConta(login, senha)) {
					usuario.setLoagado(true);
					usuario.logado();
					
					request.getSession().invalidate();
					request.getSession().setMaxInactiveInterval(60 * 60 * 30);
					request.getSession().setAttribute("usuario", usuario);
					response.sendRedirect("conta/PaginaInicial.jsp");
				} else {
					request.setAttribute("erroLogar",
							"Login e senha não cadastrados!");
					logar.forward(request, response);
				}
			} catch (UsuarioException e) {
				request.setAttribute("erroLogar", e.getMessage());
				logar.forward(request, response);
			}
		} else {
			request.setAttribute("erroLogar",
					"Login e senha são de preenchimento obrigatório!");
			logar.forward(request, response);
		}
	}

}
