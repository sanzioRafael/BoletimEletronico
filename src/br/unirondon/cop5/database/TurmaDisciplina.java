package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unirondon.cop5.execao.DisciplinaException;
import br.unirondon.cop5.execao.TurmaException;

public class TurmaDisciplina {

	private Turma turma;
	private Disciplina disciplina;
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;

	public TurmaDisciplina() {

	}

	public TurmaDisciplina(Turma turma, Disciplina disciplina) {
		this.turma = turma;
		this.disciplina = disciplina;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public void adicionarTurmaDisciplina() {
		sql = "INSERT INTO turma_disciplina(id_disciplina, id_turma) VALUES (?, ?);";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, disciplina.getIdDisciplina());
			pstmt.setInt(2, turma.getIdTurma());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean existeDisciplina(int idDisciplina, int idTurma) {
		sql = "SELECT * FROM turma_disciplina WHERE "
				+ "id_disciplina = ? AND  id_turma = ?;";
		boolean existe = false;
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);
			pstmt.setInt(2, idTurma);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				existe = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}
	
	public void deletarPorIds (int idTurma, int idDisciplina) {
		sql = "DELETE FROM turma_disciplina WHERE id_turma = ? AND id_disciplina = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idTurma);
			pstmt.setInt(2, idDisciplina);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<TurmaDisciplina> recuperarTurmas(int idTurma) {
		sql = "SELECT * FROM turma_disciplina WHERE id_turma = ?;";
		List<TurmaDisciplina> turmaDisciplinas = new ArrayList<TurmaDisciplina>();
		turma = new Turma();
		disciplina = new Disciplina();
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idTurma);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				turma.setIdTurma(rs.getInt("id_turma"));
				try {
					disciplina.setIdDisciplina(rs.getInt("id_disciplina"));
				} catch (DisciplinaException e) {
					e.printStackTrace();
				}
				turmaDisciplinas.add(new TurmaDisciplina(turma, disciplina));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (TurmaException e) {
			e.printStackTrace();
		}
		
		return turmaDisciplinas;
	}
	
}
