package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class AtividadeException extends Exception {
	
	public static final String NOMEATIVIDADEOBRIGATORIO = "O nome da atividade � de preenchimento obrigat�rio";
	public static final String DTENTREGAOBRIGATORIO = "A data de entrega � de preenchimento obrigat�rio";
	public static final String NOMEPROFESSOROBRIGATORIO = "Nome do Professor � de preenchimento obrigat�rio";
	public static final String EMAILPROFESSOROBRIGATORIO = "E-mail do Professor � de preenchimento obrigat�rio";
	public static final String ALUNOENVOLVIDOBRIGATORIO = "Alunos envolvidos � de preenchimento obrigat�rio";
	public static final String EMAILALUNOENVOLVIDOBRIGATORIO = "E-mail dos Alunos envolvidos � de preenchimento obrigat�rio";
	
	public AtividadeException(String msg) {
		super(msg);
	}
	
}
