package br.unirondon.cop5.execao;

@SuppressWarnings("serial")
public class DisciplinaException extends Exception {
	
	public static final String DISCIPLINAOBRIGATORIO = "Selecionar disciplina � obrigat�rio!";
	public static final String TURMAOBRIGATORIO = "Selecionar turma � obrigat�rio!";
	public static final String NOMEDISCIPLINAOBRIGATORIO = "O nome da disciplina � de preenchimento obrigat�rio!";
	public static final String NOMEDISCIPLINAEXISTENTE = "A disciplina j� cadastrada no sistema";
	public static final String DIASDISCIPLINAOBRIGATORIO = "Dias da semana � de preenchimento obrigat�rio!";
	
	public DisciplinaException(String msg) {
		super(msg);
	}
	
}
