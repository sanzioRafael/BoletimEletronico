package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.unirondon.cop5.execao.TurmaException;

public class Turma {

	private int idTurma;
	private int idUsuario;
	private String instEnsino;
	private String periodoTurma;
	private String periodoLetivoTurma;
	private String cursoTurma;
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;

	public Turma() {

	}

	public Turma(int idTurma, int idUsuario, String instEnsino,
			String periodoTurma, String periodoLetivoTurma, String cursoTurma) {
		this.idTurma = idTurma;
		this.idUsuario = idUsuario;
		this.instEnsino = instEnsino;
		this.periodoTurma = periodoTurma;
		this.periodoLetivoTurma = periodoLetivoTurma;
		this.cursoTurma = cursoTurma;
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) throws TurmaException {
		if(String.valueOf(idTurma) == null || String.valueOf(idTurma).isEmpty() || idTurma == 0)
			throw new TurmaException(TurmaException.IDTURMAOBRIGATORIO);
		this.idTurma = idTurma;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getInstEnsino() {
		return instEnsino;
	}

	public void setInstEnsino(String instEnsino) throws TurmaException {
		if (instEnsino.isEmpty())
			throw new TurmaException(TurmaException.INTITUICAOOBRIGATORIO);
		this.instEnsino = instEnsino;
	}

	public String getPeriodoTurma() {
		return periodoTurma;
	}

	public void setPeriodoTurma(String periodoTurma) {
		this.periodoTurma = periodoTurma;
	}

	public String getPeriodoLetivoTurma() {
		return periodoLetivoTurma;
	}

	public void setPeriodoLetivoTurma(String periodoLetivoTurma) {
		this.periodoLetivoTurma = periodoLetivoTurma;
	}

	public String getCursoTurma() {
		return cursoTurma;
	}

	public void setCursoTurma(String cursoTurma) throws TurmaException {
		if (cursoTurma.isEmpty())
			throw new TurmaException(TurmaException.CURSOOBRIGATORIO);
		this.cursoTurma = cursoTurma;
	}

	public void adicionarTurma() {
		sql = "INSERT INTO turma(id_usuario, instituicao_turma, periodo_turma"
				+ ", periodo_letivo_turma, curso_turma) VALUES (?, ?, ?, ?, ?);";

		try {
			pstmt = DataBase.con.prepareStatement(sql);

			pstmt.setInt(1, idUsuario);
			pstmt.setString(2, instEnsino);
			pstmt.setString(3, periodoTurma);
			pstmt.setString(4, periodoLetivoTurma);
			pstmt.setString(5, cursoTurma);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Turma> relatorioTurmaDoUsuario(Usuario usuario) {
		sql = "SELECT * FROM turma WHERE id_usuario = "
				+ usuario.getIdUsuario() + ";";
		ArrayList<Turma> turmas = new ArrayList<Turma>();

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				idTurma = rs.getInt("id_turma");
				idUsuario = rs.getInt("id_usuario");
				instEnsino = rs.getString("instituicao_turma");
				periodoTurma = rs.getString("periodo_turma");
				periodoLetivoTurma = rs.getString("periodo_letivo_turma");
				cursoTurma = rs.getString("curso_turma");

				turmas.add(new Turma(idTurma, idUsuario, instEnsino,
						periodoTurma, periodoLetivoTurma, cursoTurma));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return turmas;

	}
	
	public void removerTurma (int idTurma) {
		sql = "DELETE FROM turma WHERE id_turma = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, idTurma);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editarTurma () {
		sql = "UPDATE turma SET instituicao_turma = ?, periodo_turma = ?," +
		" periodo_letivo_turma = ?, curso_turma = ? WHERE id_turma = ? AND id_usuario = ?;";
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, instEnsino);
			pstmt.setString(2, periodoTurma);
			pstmt.setString(3, periodoLetivoTurma);
			pstmt.setString(4, cursoTurma);
			pstmt.setInt(5, idTurma);
			pstmt.setInt(6, idUsuario);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Turma recuperarTurmaPorID (int id) {
		sql = "SELECT * FROM turma WHERE id_turma = ?;";
		Turma turma = new Turma();
		
		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				idTurma = rs.getInt("id_turma");
				idUsuario = rs.getInt("id_usuario");
				instEnsino = rs.getString("instituicao_turma");
				periodoTurma = rs.getString("periodo_turma");
				periodoLetivoTurma = rs.getString("periodo_letivo_turma");
				cursoTurma = rs.getString("curso_turma");
				
				turma = new Turma(idTurma, idUsuario, instEnsino, periodoTurma, periodoLetivoTurma, cursoTurma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return turma;
	}
	
}
