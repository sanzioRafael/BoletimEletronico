package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.unirondon.cop5.execao.UsuarioException;
import br.unirondon.cop5.util.EncripitarSenha;

public class Usuario {

	private int idUsuario;
	private String nomeUsuario;
	private String emailUsuario;
	private String loginUsuario;
	private String senhaUsuario;
	private String confSenhaUsuario;
	private boolean logado;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql = "";

	public Usuario() {

	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) throws UsuarioException {
		if (nomeUsuario.isEmpty())
			throw new UsuarioException(UsuarioException.NOMEOBRIGATORIO);
		
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) throws UsuarioException {
		if(emailUsuario.isEmpty())
			throw new UsuarioException(UsuarioException.EMAILOBRIGATORIO);
		if(!validarEmail(emailUsuario))
			throw new UsuarioException(UsuarioException.EMAILININVALIDO);
		if(verificarUsuarioEmail(emailUsuario))
			throw new UsuarioException(UsuarioException.EMAILEXISTENTE);
		
		this.emailUsuario = emailUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) throws UsuarioException {
		if(loginUsuario.isEmpty())
			throw new UsuarioException(UsuarioException.LOGINOBRIGATORIO);
		if(verificarUsuarioLogin(loginUsuario))
			throw new UsuarioException(UsuarioException.LOGINEXISTENTE);
		
		this.loginUsuario = loginUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	
	public void setSenhaUsuario(String senhaUsuario) throws UsuarioException {
		if(senhaUsuario.isEmpty())
			throw new UsuarioException(UsuarioException.SENHAOBRIGATORIO);
		
		this.senhaUsuario = senhaUsuario;
	}
	
	public String getConfSenhaUsuario() {
		return confSenhaUsuario;
	}
	
	public void setConfSenhaUsuario(String confSenhaUsuario) throws UsuarioException {
		if(confSenhaUsuario.isEmpty())
			throw new UsuarioException(UsuarioException.CONFSENHAOBRIGATORIO);
		if(!confSenhaUsuario.equals(this.senhaUsuario))
			throw new UsuarioException(UsuarioException.CONFSENHAINVALIDA);
		
		this.confSenhaUsuario = confSenhaUsuario;
	}
	
	public boolean getLogado() {
		return logado;
	}
	
	public void setLoagado(boolean logado) {
		this.logado = logado;
	}
	
	public boolean validarEmail(String email) {
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(email);

		return m.find();
	}
	
	public void adicionarUsuario() {
		sql = "INSERT INTO usuario(nome_usuario, email_usuario, "
				+ "login_usuario, senha_usuario, logado) VALUES (?, ?, ?, ?, ?)";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeUsuario);
			pstmt.setString(2, emailUsuario);
			pstmt.setString(3, loginUsuario);
			pstmt.setString(4, EncripitarSenha.encriptar(senhaUsuario));
			pstmt.setBoolean(5, false);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean verificarUsuarioEmail(String email) {
		sql = "SELECT * FROM usuario WHERE email_usuario = '"+email+"';";
		boolean existe = false;
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				existe = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public boolean verificarUsuarioLogin(String login) {
		sql = "SELECT * FROM usuario WHERE login_usuario = '"+login+"';";
		boolean existe = false;
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				existe = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public boolean logarConta (String login, String senha) throws UsuarioException {
		sql = "SELECT * FROM usuario WHERE login_usuario = '"+login+"' " +
				"AND senha_usuario = '"+EncripitarSenha.encriptar(senha)+"';";
		boolean existe = false;
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				idUsuario = rs.getInt("id_usuario");
				nomeUsuario = rs.getString("nome_usuario");
				emailUsuario = rs.getString("email_usuario");
				loginUsuario = rs.getString("login_usuario");
				senhaUsuario = rs.getString("senha_usuario");
				confSenhaUsuario = senha;
				logado = rs.getBoolean("logado");
				
				if(!logado)
					existe = true;
				else
					throw new UsuarioException(UsuarioException.USUARIOONLINE);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public void logado() {
		sql = "UPDATE usuario SET logado = ? WHERE id_usuario = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setBoolean(1, logado);
			pstmt.setInt(2, idUsuario);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deslogado() {
		sql = "UPDATE usuario SET logado = ? WHERE id_usuario = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setBoolean(1, logado);
			pstmt.setInt(2, idUsuario);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
