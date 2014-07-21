package br.unirondon.cop5.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.SimpleEmail;

import br.unirondon.cop5.database.Usuario;
import br.unirondon.cop5.execao.UsuarioException;

@WebServlet("/CadastroUsuario")
public class CadastroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario novoUsuario;

	public CadastroUsuario() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SimpleEmail mail = new SimpleEmail();
		RequestDispatcher cadastroUser = request.getRequestDispatcher("CadastroUsuario.jsp");
		String nome = request.getParameter("txtNome");
		String email = request.getParameter("txtEmail");
		String login = request.getParameter("txtLogin");
		String senha = request.getParameter("txtSenha");
		String confSenha = request.getParameter("txtConfSenha");
		novoUsuario = new Usuario();
		String erroCadUser = "";
		
		try {
			novoUsuario.setNomeUsuario(nome);
		} catch (UsuarioException e) {
			request.setAttribute("erroNome", e.getMessage());
			erroCadUser += e.getMessage() + "<br />";
		}
		
		try {
			novoUsuario.setEmailUsuario(email);
		} catch (UsuarioException e) {
			request.setAttribute("erroEmail", e.getMessage());
			erroCadUser += e.getMessage() + "<br />";
		}
		
		try {
			novoUsuario.setLoginUsuario(login);
		} catch (UsuarioException e) {
			request.setAttribute("erroLogin", e.getMessage());
			erroCadUser += e.getMessage() + "<br />";
		}
		
		try {
			novoUsuario.setSenhaUsuario(senha);
		} catch (UsuarioException e) {
			request.setAttribute("erroSenha", e.getMessage());
			erroCadUser += e.getMessage() + "<br />";
		}
		
		try {
			novoUsuario.setConfSenhaUsuario(confSenha);
		} catch (UsuarioException e) {
			request.setAttribute("erroConfSenha", e.getMessage());
			erroCadUser += e.getMessage() + "<br />";
		}

		if (erroCadUser.trim().length() == 0) {
			novoUsuario.adicionarUsuario();			
			response.sendRedirect("CadastroConcluido.html");
		} else
			cadastroUser.forward(request, response);
	}

}
