package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class TurmaException extends Exception {
	
	public static final String IDTURMAOBRIGATORIO = "Seleciona Turma é obrigatório";
	public static final String INTITUICAOOBRIGATORIO = "Intituição de Ensino é de preenchimento obrigatória!";
	public static final String CURSOOBRIGATORIO = "Curso é de preenchimento obrigatória!";
	
	public TurmaException(String msg) {
		super(msg);
	}
	
}
