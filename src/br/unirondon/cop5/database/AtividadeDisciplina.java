package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unirondon.cop5.execao.AtividadeException;

public class AtividadeDisciplina {

	private int codAtividade;
	private int idDisciplina;
	private String nomeProfessor;
	private String emailProfessor;
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;

	public AtividadeDisciplina() {

	}

	public AtividadeDisciplina(int codAtividade, int idDisciplina,
			String nomeProfessor, String emailProfessor) {
		this.codAtividade = codAtividade;
		this.idDisciplina = idDisciplina;
		this.nomeProfessor = nomeProfessor;
		this.emailProfessor = emailProfessor;
	}

	public int getCodAtividade() {
		return codAtividade;
	}

	public void setCodAtividade(int codAtividade) {
		this.codAtividade = codAtividade;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor)
			throws AtividadeException {
		if (nomeProfessor.isEmpty())
			throw new AtividadeException(
					AtividadeException.NOMEPROFESSOROBRIGATORIO);
		this.nomeProfessor = nomeProfessor;
	}

	public String getEmailProfessor() {
		return emailProfessor;
	}

	public void setEmailProfessor(String emailProfessor)
			throws AtividadeException {
		if (emailProfessor.isEmpty())
			throw new AtividadeException(
					AtividadeException.EMAILALUNOENVOLVIDOBRIGATORIO);
		this.emailProfessor = emailProfessor;
	}

	public void adicionarAtividadeDisciplina() {
		sql = "INSERT INTO atividade_disciplina(cod_atividade, "
				+ "id_disciplina, nome_professor, email_professor) "
				+ "VALUES (?, ?, ?, ?);";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, codAtividade);
			pstmt.setInt(2, idDisciplina);
			pstmt.setString(3, nomeProfessor);
			pstmt.setString(4, emailProfessor);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editarAtividadeDisciplina(int codAtividade, int idDisciplina) {
		sql = "UPDATE atividade_disciplina SET nome_professor = ?, email_professor = ?"
				+ " WHERE cod_atividade = ? AND id_disciplina = ?;";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeProfessor);
			pstmt.setString(2, emailProfessor);
			pstmt.setInt(3, codAtividade);
			pstmt.setInt(4, idDisciplina);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerAtividade(int codAtividade) {
		sql = "DELETE FROM atividade_disciplina WHERE cod_atividade = ?;";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, codAtividade);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<AtividadeDisciplina> recuperarAtividadePorDisciplina(
			int idDisciplina) {
		sql = "SELECT * FROM atividade_disciplina WHERE id_disciplina = ?;";
		List<AtividadeDisciplina> atividades = new ArrayList<AtividadeDisciplina>();

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				codAtividade = rs.getInt("cod_atividade");
				this.idDisciplina = rs.getInt("id_disciplina");
				nomeProfessor = rs.getString("nome_professor");
				emailProfessor = rs.getString("email_professor");

				atividades.add(new AtividadeDisciplina(codAtividade,
						this.idDisciplina, nomeProfessor, emailProfessor));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return atividades;
	}

	public void removerAtividadePorDisciplina(int idDisciplina) {
		sql = "DELETE FROM atividade_disciplina WHERE cod_atividade = ?;";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
