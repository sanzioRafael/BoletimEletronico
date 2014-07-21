package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class UsuarioException extends Exception {
	
	public static final String NOMEOBRIGATORIO = "Nome � de preenchimento obrigat�rio";
	public static final String EMAILOBRIGATORIO = "E-mail � de preenchimento obrigat�rio";
	public static final String EMAILEXISTENTE = "E-mail j� cadastrado no sistema";
	public static final String EMAILININVALIDO = "E-mail invalido";
	public static final String LOGINOBRIGATORIO = "Login � de preenchimento obrigat�rio";
	public static final String LOGINEXISTENTE = "Login j� � cadastrado no sistema";
	public static final String SENHAOBRIGATORIO = "Senha � de preenchimento obrigat�rio";
	public static final String CONFSENHAOBRIGATORIO = "Confirma��o de Senha � de preenchimento obrigat�rio";
	public static final String CONFSENHAINVALIDA = "Senhas Diferentes";
	public static final String USUARIOONLINE = "O usu�rio j� est� conectado ao sistema";
	
	public UsuarioException(String msg) {
		super(msg);
	}
	
}
