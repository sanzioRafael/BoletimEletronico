package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class AtividadeException extends Exception {
	
	public static final String NOMEATIVIDADEOBRIGATORIO = "O nome da atividade é de preenchimento obrigatório";
	public static final String DTENTREGAOBRIGATORIO = "A data de entrega é de preenchimento obrigatório";
	public static final String NOMEPROFESSOROBRIGATORIO = "Nome do Professor é de preenchimento obrigatório";
	public static final String EMAILPROFESSOROBRIGATORIO = "E-mail do Professor é de preenchimento obrigatório";
	public static final String ALUNOENVOLVIDOBRIGATORIO = "Alunos envolvidos é de preenchimento obrigatório";
	public static final String EMAILALUNOENVOLVIDOBRIGATORIO = "E-mail dos Alunos envolvidos é de preenchimento obrigatório";
	
	public AtividadeException(String msg) {
		super(msg);
	}
	
}
