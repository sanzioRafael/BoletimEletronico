package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class BoletimException extends Exception {
	
	public static final String DISCIPLINAEXISTENTE = "Este boletim já existe";
	public static final String NOTA1OBRIGATORIO = "Nota 1 é de prenchimento obrigatório";
	public static final String NOTA2OBRIGATORIO = "Nota 2 é de prenchimento obrigatório";
	public static final String NOTA3OBRIGATORIO = "Nota 3 é de prenchimento obrigatório";
	public static final String NOTA4OBRIGATORIO = "Nota 4 é de prenchimento obrigatório";
	public static final String MEDIAOBRIGATORIO = "Média é de prenchimento obrigatório";
	public static final String MEDIADAINSTOBRIGATORIO = "Nota Média é de prenchimento obrigatório";
	public static final String RECUPERACAOOBRIGATORIO = "Recuperação é de prenchimento obrigatório";
	public static final String PFOBRIGATORIO = "PF é de prenchimento obrigatório";
	public static final String NUMEROINVALIDO = "Está nota não é um número";
	public static final String NUMEROLIMITE = "Números aceitos entre 0,00 até 10,00";
	
	public BoletimException(String msg) {
		super(msg);
	}
	
}
