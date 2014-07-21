package br.unirondon.cop5.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.unirondon.cop5.execao.AtividadeException;

public class Atividade {

	private int codAtividade;
	private String nomeAtividade;
	private Date dtEntrega;
	private String sql = "";
	private PreparedStatement pstmt;
	private ResultSet rs;

	public Atividade() {

	}

	public int getCodAtividade() {
		return codAtividade;
	}

	public void setCodAtividade(int codAtividade) {
		this.codAtividade = codAtividade;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade)
			throws AtividadeException {
		if (nomeAtividade.isEmpty())
			throw new AtividadeException(AtividadeException.NOMEATIVIDADEOBRIGATORIO);
		this.nomeAtividade = nomeAtividade;
	}

	public Date getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(String dtEntrega) throws AtividadeException {
		if (dtEntrega.isEmpty())
			throw new AtividadeException(AtividadeException.DTENTREGAOBRIGATORIO);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.dtEntrega = sdf.parse(dtEntrega);

			sdf.applyLocalizedPattern("dd/MM/yyyy");

			dtEntrega = sdf.format(this.dtEntrega);
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.dtEntrega = sdf.parse(dtEntrega);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void adicionarAtividade() {
		sql = "INSERT INTO atividade(nome_atividade, data_entrega) VALUES(?, ?);";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeAtividade);
			pstmt.setDate(2, new java.sql.Date(dtEntrega.getTime()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editarAtividade(int codAtividade) {
		sql = "UPDATE atividade SET nome_atividade = ?, data_entrega = ? WHERE cod_atividade = ?;";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeAtividade);
			pstmt.setDate(2, new java.sql.Date(dtEntrega.getTime()));
			pstmt.setInt(3, codAtividade);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerAtividade(int codAtividade) {
		sql = "DELETE FROM atividade WHERE cod_atividade = ?;";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setInt(1, codAtividade);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void recuperarCodAtividade(String nomeAtividade, Date dtEntrega) {
		sql = "SELECT cod_atividade FROM atividade WHERE nome_atividade = ? AND data_entrega = ?;";

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeAtividade);
			pstmt.setDate(2, new java.sql.Date(dtEntrega.getTime()));

			rs = pstmt.executeQuery();

			if (rs.next())
				codAtividade = rs.getInt("cod_atividade");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean existeAtividade(String nomeAtividade, Date dtEntrega) {
		sql = "SELECT * FROM atividade WHERE nome_atividade = ? AND data_entrega = ?;";
		boolean existe = false;

		try {
			pstmt = DataBase.con.prepareStatement(sql);
			pstmt.setString(1, nomeAtividade);
			pstmt.setDate(2, new java.sql.Date(dtEntrega.getTime()));

			rs = pstmt.executeQuery();

			if (rs.next())
				existe = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return existe;
	}

}
