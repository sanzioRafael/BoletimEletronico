package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.unirondon.cop5.execao.BoletimException;

public class BoletimSemestral extends Boletim {
	
	private int codBoletimSemestral;
	private int idDisciplina;
	private String sql = "";
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	public BoletimSemestral() {
		super();
	}

	public int getCodBoletimSemestral() {
		return codBoletimSemestral;
	}

	public void setCodBoletimSemestral(int codBoletimSemestral) {
		this.codBoletimSemestral = codBoletimSemestral;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) throws BoletimException {
		if(existeBoletimSemestral(idDisciplina))
			throw new BoletimException(BoletimException.DISCIPLINAEXISTENTE);
		this.idDisciplina = idDisciplina;
	}
	
	private boolean existeBoletimSemestral(int idDisciplina) {
		sql = "SELECT * FROM boletim_semestral WHERE id_disciplina = ?;";
		boolean existe = false;
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				existe = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return existe;
	}

	public void adicionarBoletimSemestral () {
		sql = "INSERT INTO boletim_semestral(id_disciplina, nota1, "
				+ "nota2, media, recuperacao, prova_final, "
				+ "nota_media) VALUES(?, ?, ?, ?, ?, ?, ?);";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);
			pstmt.setFloat(2, this.getNota1());
			pstmt.setFloat(3, this.getNota2());
			pstmt.setFloat(4, this.getMedia());
			pstmt.setFloat(5, this.getRecuperacao());
			pstmt.setFloat(6, this.getProvaFinal());
			pstmt.setFloat(7, this.getNotaMedia());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarBoletimSemestral (int codBoletimSemestral) {
		sql = "DELETE FROM boletim_semestral WHERE cod_boletim_semestral = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, codBoletimSemestral);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarBoletimSemestralPorDisciplina (int idDisciplina) {
		sql = "DELETE FROM boletim_semestral WHERE id_disciplina = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void recuperarBoletimSemestralPorId (int id) {
		sql = "SELECT * FROM boletim_semestral WHERE cod_boletim_semestral = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				codBoletimSemestral = rs.getInt("cod_boletim_semestral");
				idDisciplina = rs.getInt("id_disciplina");
				nota1 = rs.getFloat("nota1");
				nota2 = rs.getFloat("nota2");
				media = rs.getFloat("media");
				recuperacao = rs.getFloat("recuperacao");
				provaFinal = rs.getFloat("prova_final");
				notaMedia = rs.getFloat("nota_media");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterarBoletimSemestralPorId () {
		sql = "UPDATE boletim_semestral SET nota1 = ?, nota2 = ?, "
				+ "media = ?, recuperacao = ?, prova_final = ?, "
				+ "nota_media = ? WHERE cod_boletim_semestral = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setFloat(1, nota1);
			pstmt.setFloat(2, nota2);
			pstmt.setFloat(3, media);
			pstmt.setFloat(4, recuperacao);
			pstmt.setFloat(5, provaFinal);
			pstmt.setFloat(6, notaMedia);
			pstmt.setInt(7, codBoletimSemestral);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
