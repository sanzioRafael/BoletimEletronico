package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class TurmaException extends Exception {
	
	public static final String IDTURMAOBRIGATORIO = "Seleciona Turma � obrigat�rio";
	public static final String INTITUICAOOBRIGATORIO = "Intitui��o de Ensino � de preenchimento obrigat�ria!";
	public static final String CURSOOBRIGATORIO = "Curso � de preenchimento obrigat�ria!";
	
	public TurmaException(String msg) {
		super(msg);
	}
	
}
