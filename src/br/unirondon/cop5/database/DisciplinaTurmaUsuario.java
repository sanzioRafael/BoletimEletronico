package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaTurmaUsuario {
	
	private int idUsuario;
	private int idTurma;
	private int idDisciplina;
	private String nomeDisciplina;
	private String diaDisciplina;
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DisciplinaTurmaUsuario() {
		
	}
	
	public DisciplinaTurmaUsuario(int idUsuario, int idTurma, int idDisciplina,
			String nomeDisciplina, String diaDisciplina) {
		this.idUsuario = idUsuario;
		this.idTurma = idTurma;
		this.idDisciplina = idDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.diaDisciplina = diaDisciplina;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getIdTurma() {
		return idTurma;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public String getDiaDisciplina() {
		return diaDisciplina;
	}
	
	public List<DisciplinaTurmaUsuario> recuperarDisciplinaPorUsuario(int idUsuario) {
		sql = "SELECT * FROM DisciplinaTurmaUsuario WHERE id_usuario = ?;";
		List<DisciplinaTurmaUsuario> disciplinas = new ArrayList<DisciplinaTurmaUsuario>();
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idUsuario);
						
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				this.idUsuario = rs.getInt("id_usuario");
				idTurma = rs.getInt("id_turma");
				idDisciplina = rs.getInt("id_disciplina");
				nomeDisciplina = rs.getString("nome_disciplina");
				diaDisciplina = rs.getString("dia_disciplina");
				
				disciplinas.add(new DisciplinaTurmaUsuario(this.idUsuario,
						idTurma, idDisciplina, nomeDisciplina, diaDisciplina));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return disciplinas;
	}
	
}
