package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.unirondon.cop5.execao.DisciplinaException;


public class Disciplina {
	
	private int idDisciplina;
	private Turma turma;
	private String nomeDisciplina;
	private String diaDisciplina;
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Disciplina() {
		
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) throws DisciplinaException {
		if(String.valueOf(idDisciplina).isEmpty() || String.valueOf(idDisciplina) == null)
			throw new DisciplinaException(DisciplinaException.DISCIPLINAOBRIGATORIO);
		this.idDisciplina = idDisciplina;
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) throws DisciplinaException {
		if(nomeDisciplina.isEmpty() || nomeDisciplina == null)
			throw new DisciplinaException(DisciplinaException.NOMEDISCIPLINAOBRIGATORIO);
		if(existeDisciplina(nomeDisciplina))
			throw new DisciplinaException(DisciplinaException.NOMEDISCIPLINAEXISTENTE);
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getDiaDisciplina() {
		return diaDisciplina;
	}

	public void setDiaDisciplina(String diaDisciplina) throws DisciplinaException {
		if(diaDisciplina.isEmpty() || diaDisciplina == null)
			throw new DisciplinaException(DisciplinaException.DIASDISCIPLINAOBRIGATORIO);
		this.diaDisciplina = diaDisciplina;
	}
	
	public void adicionarDisciplina() {
		sql = "INSERT INTO disciplina (nome_disciplina, dia_disciplina) VALUES (?, ?);";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeDisciplina);
			pstmt.setString(2, diaDisciplina);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean existeDisciplina(String nomeDisciplina) {
		sql = "SELECT * FROM disciplina WHERE nome_disciplina = ?;";
		boolean existe = false;
		TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeDisciplina);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if(turma != null) {
				idDisciplina = rs.getInt("id_disciplina");
				if(turmaDisciplina.existeDisciplina(idDisciplina, turma.getIdTurma())) {
					existe = true;
					break;
				} else
					break;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			return true;
		}
		
		return existe;
	}
	
	public void deletarDisciplinaPorTurma(int idDisciplina) {
		sql = "DELETE FROM disciplina WHERE id_disciplina = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void recuperarIdDisciplina() {
		sql = "SELECT * FROM disciplina WHERE nome_disciplina = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeDisciplina);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idDisciplina = rs.getInt("id_disciplina");
				nomeDisciplina = rs.getString("nome_disciplina");
				diaDisciplina = rs.getString("dia_disciplina");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void recuperarDisciplinaPorId() {
		sql = "SELECT * FROM disciplina WHERE id_disciplina = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);
						
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				idDisciplina = rs.getInt("id_disciplina");
				nomeDisciplina = rs.getString("nome_disciplina");
				diaDisciplina = rs.getString("dia_disciplina");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarDisciplina() {
		sql = "UPDATE disciplina SET nome_disciplina = ?, dia_disciplina = ? WHERE id_disciplina = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeDisciplina);
			pstmt.setString(2, diaDisciplina);
			pstmt.setInt(3, idDisciplina);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
