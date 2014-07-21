package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class UsuarioException extends Exception {
	
	public static final String NOMEOBRIGATORIO = "Nome é de preenchimento obrigatório";
	public static final String EMAILOBRIGATORIO = "E-mail é de preenchimento obrigatório";
	public static final String EMAILEXISTENTE = "E-mail já cadastrado no sistema";
	public static final String EMAILININVALIDO = "E-mail invalido";
	public static final String LOGINOBRIGATORIO = "Login é de preenchimento obrigatório";
	public static final String LOGINEXISTENTE = "Login já é cadastrado no sistema";
	public static final String SENHAOBRIGATORIO = "Senha é de preenchimento obrigatório";
	public static final String CONFSENHAOBRIGATORIO = "Confirmação de Senha é de preenchimento obrigatório";
	public static final String CONFSENHAINVALIDA = "Senhas Diferentes";
	public static final String USUARIOONLINE = "O usuário já está conectado ao sistema";
	
	public UsuarioException(String msg) {
		super(msg);
	}
	
}
