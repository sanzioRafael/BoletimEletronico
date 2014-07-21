package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.unirondon.cop5.execao.AtividadeException;

public class AtividadeUsuario {

	private int codAtividade;
	private int idUsuario;
	private String alunoEnvolvido;
	private String emailAlunoEnvolvido;
	private String sql = "";
	private PreparedStatement pstmt;

	public AtividadeUsuario() {

	}

	public int getCodAtividade() {
		return codAtividade;
	}

	public void setCodAtividade(int codAtividade) {
		this.codAtividade = codAtividade;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getAlunoEnvolvido() {
		return alunoEnvolvido;
	}

	public void setAlunoEnvolvido(String alunoEnvolvido)
			throws AtividadeException {
		if (alunoEnvolvido.isEmpty())
			throw new AtividadeException(
					AtividadeException.ALUNOENVOLVIDOBRIGATORIO);
		this.alunoEnvolvido = alunoEnvolvido;
	}

	public String getEmailAlunoEnvolvido() {
		return emailAlunoEnvolvido;
	}

	public void setEmailAlunoEnvolvido(String emailAlunoEnvolvido)
			throws AtividadeException {
		if (emailAlunoEnvolvido.isEmpty())
			throw new AtividadeException(
					AtividadeException.EMAILPROFESSOROBRIGATORIO);
		this.emailAlunoEnvolvido = emailAlunoEnvolvido;
	}

	public void adicionarAtividadeUsuario() {
		sql = "INSERT INTO atividade_usuario(cod_atividade, "
				+ "id_usuario, aluno_envolvido, email_aluno_envolvido) "
				+ "VALUES (?, ?, ?, ?);";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, codAtividade);
			pstmt.setInt(2, idUsuario);
			pstmt.setString(3, alunoEnvolvido);
			pstmt.setString(4, emailAlunoEnvolvido);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editarAtividadeUsuario(int codAtividade, int idUsuario) {
		sql = "UPDATE atividade_usuario SET aluno_envolvido = ?, email_aluno_envolvido = ?"
				+ " WHERE cod_atividade = ? AND id_usuario = ?;";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, alunoEnvolvido);
			pstmt.setString(2, emailAlunoEnvolvido);
			pstmt.setInt(3, codAtividade);
			pstmt.setInt(4, idUsuario);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerAtividade(int codAtividade) {
		sql = "DELETE FROM atividade_usuario WHERE cod_atividade = ?;";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, codAtividade);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
