package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.unirondon.cop5.execao.BoletimException;

public class BoletimAnual extends Boletim {
	
	private int codBoletimAnual;
	private int idDisciplina;
	private float nota3, nota4;
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BoletimAnual() {
		super();
	}

	public int getCodBoletimAnual() {
		return codBoletimAnual;
	}

	public void setCodBoletimAnual(int codBoletimAnual) {
		this.codBoletimAnual = codBoletimAnual;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) throws BoletimException {
		if(existeBoletimAnual(idDisciplina))
			throw new BoletimException(BoletimException.DISCIPLINAEXISTENTE);
		this.idDisciplina = idDisciplina;
	}

	public float getNota3() {
		return nota3;
	}

	public void setNota3(String nota3) throws BoletimException {
		if(nota3.isEmpty())
			throw new BoletimException(BoletimException.NOTA3OBRIGATORIO);
		if(super.isFloat(nota3))
			throw new BoletimException(BoletimException.NUMEROINVALIDO);
		float var = Float.valueOf(nota3);
		if(!(var >= 0 && var <= 10))
			throw new BoletimException(BoletimException.NUMEROLIMITE);
		this.nota3 = var;
	}

	public float getNota4() {
		return nota4;
	}

	public void setNota4(String nota4) throws BoletimException {
		if(nota4.isEmpty())
			throw new BoletimException(BoletimException.NOTA4OBRIGATORIO);
		if(super.isFloat(nota4))
			throw new BoletimException(BoletimException.NUMEROINVALIDO);
		float var = Float.valueOf(nota4);
		if(!(var >= 0 && var <= 10))
			throw new BoletimException(BoletimException.NUMEROLIMITE);
		this.nota4 = var;
	}
	
	private boolean existeBoletimAnual(int idDisciplina) {
		sql = "SELECT * FROM boletim_anual WHERE id_disciplina = ?;";
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
	
	public void adicionarBoletimAnual () {
		sql = "INSERT INTO boletim_anual(id_disciplina, nota1, "
				+ "nota2, nota3, nota4, media, recuperacao, prova_final, "
				+ "nota_media) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);
			pstmt.setFloat(2, this.getNota1());
			pstmt.setFloat(3, this.getNota2());
			pstmt.setFloat(4, nota3);
			pstmt.setFloat(5, nota4);
			pstmt.setFloat(6, this.getMedia());
			pstmt.setFloat(7, this.getRecuperacao());
			pstmt.setFloat(8, this.getProvaFinal());
			pstmt.setFloat(9, this.getNotaMedia());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarBoletimAnual (int codBoletimAnual) {
		sql = "DELETE FROM boletim_anual WHERE cod_boletim_anual = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, codBoletimAnual);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarBoletimAnualPorDisciplina (int idDisciplina) {
		sql = "DELETE FROM boletim_anual WHERE id_disciplina = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idDisciplina);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void recuperarBoletimAnualPorId (int id) {
		sql = "SELECT * FROM boletim_anual WHERE cod_boletim_anual = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				codBoletimAnual = rs.getInt("cod_boletim_anual");
				idDisciplina = rs.getInt("id_disciplina");
				nota1 = rs.getFloat("nota1");
				nota2 = rs.getFloat("nota2");
				nota3 = rs.getFloat("nota3");
				nota4 = rs.getFloat("nota4");
				media = rs.getFloat("media");
				recuperacao = rs.getFloat("recuperacao");
				provaFinal = rs.getFloat("prova_final");
				notaMedia = rs.getFloat("nota_media");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarBoletimAnualPorId () {
		sql = "UPDATE boletim_anual SET nota1 = ?, nota2 = ?, nota3 = ?, "
				+ "nota4 = ?, media = ?, recuperacao = ?, prova_final = ?, "
				+ "nota_media = ? WHERE cod_boletim_anual = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setFloat(1, nota1);
			pstmt.setFloat(2, nota2);
			pstmt.setFloat(3, nota3);
			pstmt.setFloat(4, nota4);
			pstmt.setFloat(5, media);
			pstmt.setFloat(6, recuperacao);
			pstmt.setFloat(7, provaFinal);
			pstmt.setFloat(8, notaMedia);
			pstmt.setInt(9, codBoletimAnual);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
