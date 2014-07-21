package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class DisciplinaException extends Exception {
	
	public static final String DISCIPLINAOBRIGATORIO = "Selecionar disciplina é obrigatório!";
	public static final String TURMAOBRIGATORIO = "Selecionar turma é obrigatório!";
	public static final String NOMEDISCIPLINAOBRIGATORIO = "O nome da disciplina é de preenchimento obrigatório!";
	public static final String NOMEDISCIPLINAEXISTENTE = "A disciplina já cadastrada no sistema";
	public static final String DIASDISCIPLINAOBRIGATORIO = "Dias da semana é de preenchimento obrigatório!";
	
	public DisciplinaException(String msg) {
		super(msg);
	}
	
}
