package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtividadeCompleto {
	
	private int codAtividade;
	private int idDisciplina;
	private int idUsuario;
	private String nomeAtividade;
	private Date dtEntrega;
	private String nomeProfessor;
	private String emailProfessor;
	private String alunoEnvolvido;
	private String emailAlunoEnvolvido;
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AtividadeCompleto() {
		
	}
	
	public AtividadeCompleto(int codAtividade, int idDisciplina, int idUsuario,
			String nomeAtividade, Date dtEntrega, String nomeProfessor,
			String emailProfessor, String alunoEnvolvido, String emailAlunoEnvolvido) {
		this.codAtividade = codAtividade;
		this.idDisciplina = idDisciplina;
		this.nomeAtividade = nomeAtividade;
		this.dtEntrega = dtEntrega;
		this.nomeProfessor = nomeProfessor;
		this.emailProfessor = emailProfessor;
		this.alunoEnvolvido = alunoEnvolvido;
		this.emailAlunoEnvolvido = emailAlunoEnvolvido;
	}

	public int getCodAtividade() {
		return codAtividade;
	}

	public int getIdDisciplina() {
		return idDisciplina;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public String getDtEntrega() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dtEntrega);
	}
	
	public Date getDataEntrega() {
		return dtEntrega;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public String getEmailProfessor() {
		return emailProfessor;
	}

	public String getAlunoEnvolvido() {
		return alunoEnvolvido;
	}

	public String getEmailAlunoEnvolvido() {
		return emailAlunoEnvolvido;
	}
	
	public List<AtividadeCompleto> recuperarAtividadePorUsuario (int idUsuario) {
		sql = "SELECT * FROM AtividadeCompleto WHERE id_usuario = ?;";
		List<AtividadeCompleto> atividades = new ArrayList<AtividadeCompleto>();
		SimpleDateFormat sdf;
		String dt = "";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idUsuario);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				codAtividade = rs.getInt("cod_atividade");
				idDisciplina = rs.getInt("id_disciplina");
				this.idUsuario = rs.getInt("id_usuario");
				nomeAtividade = rs.getString("nome_atividade");
				dtEntrega = sdf.parse(rs.getString("data_entrega"));
				
				sdf.applyPattern("dd/MM/yyyy");
				dt = sdf.format(dtEntrega);
				sdf = new SimpleDateFormat("dd/MM/yyyy");
				dtEntrega = (Date) sdf.parse(dt);
				
				nomeProfessor = rs.getString("nome_professor");
				emailProfessor = rs.getString("email_professor");
				alunoEnvolvido = rs.getString("aluno_envolvido");
				emailAlunoEnvolvido = rs.getString("email_aluno_envolvido");
				
				atividades.add(new AtividadeCompleto(codAtividade, idDisciplina,
						idUsuario, nomeAtividade, dtEntrega, nomeProfessor,
						emailProfessor, alunoEnvolvido, emailAlunoEnvolvido));
			}
			
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		
		return atividades;
	}
	
	public void recuperarAtividade (int codAtividade) {
		sql = "SELECT * FROM AtividadeCompleto WHERE cod_atividade = ?;";
		SimpleDateFormat sdf;
		String dt = "";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, codAtividade);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				this.codAtividade = rs.getInt("cod_atividade");
				idDisciplina = rs.getInt("id_disciplina");
				idUsuario = rs.getInt("id_usuario");
				nomeAtividade = rs.getString("nome_atividade");
				dtEntrega = sdf.parse(rs.getString("data_entrega"));
				
				sdf.applyPattern("dd/MM/yyyy");
				dt = sdf.format(dtEntrega);
				sdf = new SimpleDateFormat("dd/MM/yyyy");
				dtEntrega = (Date) sdf.parse(dt);
				
				nomeProfessor = rs.getString("nome_professor");
				emailProfessor = rs.getString("email_professor");
				alunoEnvolvido = rs.getString("aluno_envolvido");
				emailAlunoEnvolvido = rs.getString("email_aluno_envolvido");
				
			}
			
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		
	}
	
}
