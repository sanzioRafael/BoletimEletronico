package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoletimAnualCompleto {
	
	private int idUsuario;
	private int idDisciplina;
	private int codBoletimAnual;
	private String nomeDisciplina;
	private float nota1, nota2, nota3, nota4;
	private float recuperacao;
	private float provaFinal;
	private float media;
	private float notaMedia;
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BoletimAnualCompleto() {
		
	}

	public BoletimAnualCompleto(int idUsuario, int idDisciplina, int codBoletimAnual,
			String nomeDisciplina, float nota1, float nota2, float nota3, float nota4,
			float recuperacao, float provaFinal, float media, float notaMedia) {
		this.idUsuario = idUsuario;
		this.idDisciplina = idDisciplina;
		this.codBoletimAnual = codBoletimAnual;
		this.nomeDisciplina = nomeDisciplina;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
		this.nota4 = nota4;
		this.recuperacao = recuperacao;
		this.provaFinal = provaFinal;
		this.media = media;
		this.notaMedia = notaMedia;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public int getCodBoletimAnual() {
		return codBoletimAnual;
	}

	public void setCodBoletimAnual(int codBoletimAnual) {
		this.codBoletimAnual = codBoletimAnual;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public float getNota1() {
		return nota1;
	}

	public void setNota1(float nota1) {
		this.nota1 = nota1;
	}

	public float getNota2() {
		return nota2;
	}

	public void setNota2(float nota2) {
		this.nota2 = nota2;
	}

	public float getNota3() {
		return nota3;
	}

	public void setNota3(float nota3) {
		this.nota3 = nota3;
	}

	public float getNota4() {
		return nota4;
	}

	public void setNota4(float nota4) {
		this.nota4 = nota4;
	}

	public float getRecuperacao() {
		return recuperacao;
	}

	public void setRecuperacao(float recuperacao) {
		this.recuperacao = recuperacao;
	}

	public float getProvaFinal() {
		return provaFinal;
	}

	public void setProvaFinal(float provaFinal) {
		this.provaFinal = provaFinal;
	}

	public float getMedia() {
		return media;
	}

	public void setMedia(float media) {
		this.media = media;
	}

	public float getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(float notaMedia) {
		this.notaMedia = notaMedia;
	}

	public List<BoletimAnualCompleto> recuperarBoletimAnualPorUsuario () {
		sql = "SELECT * FROM BoletimAnualCompleto WHERE id_usuario = ?;";
		List<BoletimAnualCompleto> anuais = new ArrayList<BoletimAnualCompleto>();
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idUsuario);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				idUsuario = rs.getInt("id_usuario");
				idDisciplina = rs.getInt("id_disciplina");
				codBoletimAnual = rs.getInt("cod_boletim_anual");
				nomeDisciplina = rs.getString("nome_disciplina");
				nota1 = rs.getFloat("nota1");
				nota2 = rs.getFloat("nota2");
				nota3 = rs.getFloat("nota3");
				nota4 = rs.getFloat("nota4");
				media = rs.getFloat("media");
				recuperacao = rs.getFloat("recuperacao");
				provaFinal = rs.getFloat("prova_final");
				notaMedia = rs.getFloat("nota_media");
				
				anuais.add(new BoletimAnualCompleto(idUsuario, idDisciplina,
						codBoletimAnual,nomeDisciplina, nota1, nota2, nota3,
						nota4, recuperacao, provaFinal, media, notaMedia));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return anuais;
	}
	
}
