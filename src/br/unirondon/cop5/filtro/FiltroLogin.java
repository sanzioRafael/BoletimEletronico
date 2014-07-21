package br.unirondon.cop5.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unirondon.cop5.database.Usuario;

@WebFilter("/conta/*")
public class FiltroLogin implements Filter {

	public FiltroLogin() {
		super();
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
				
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario != null || session.getCreationTime() == (60 * 60 * 30)) {
			usuario.deslogado();
			chain.doFilter(request, response);
		} else
			resp.sendRedirect("../");
		
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
