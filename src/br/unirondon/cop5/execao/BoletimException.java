package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class BoletimException extends Exception {
	
	public static final String DISCIPLINAEXISTENTE = "Este boletim j� existe";
	public static final String NOTA1OBRIGATORIO = "Nota 1 � de prenchimento obrigat�rio";
	public static final String NOTA2OBRIGATORIO = "Nota 2 � de prenchimento obrigat�rio";
	public static final String NOTA3OBRIGATORIO = "Nota 3 � de prenchimento obrigat�rio";
	public static final String NOTA4OBRIGATORIO = "Nota 4 � de prenchimento obrigat�rio";
	public static final String MEDIAOBRIGATORIO = "M�dia � de prenchimento obrigat�rio";
	public static final String MEDIADAINSTOBRIGATORIO = "Nota M�dia � de prenchimento obrigat�rio";
	public static final String RECUPERACAOOBRIGATORIO = "Recupera��o � de prenchimento obrigat�rio";
	public static final String PFOBRIGATORIO = "PF � de prenchimento obrigat�rio";
	public static final String NUMEROINVALIDO = "Est� nota n�o � um n�mero";
	public static final String NUMEROLIMITE = "N�meros aceitos entre 0,00 at� 10,00";
	
	public BoletimException(String msg) {
		super(msg);
	}
	
}
