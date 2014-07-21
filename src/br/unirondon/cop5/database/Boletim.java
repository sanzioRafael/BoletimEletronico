package br.unirondon.cop5.database;

import br.unirondon.cop5.execao.BoletimException;

public class Boletim {
	
	protected float nota1, nota2;
	protected float recuperacao;
	protected float provaFinal;
	protected float media;
	protected float notaMedia;
	
	public Boletim() {
		
	}
	
	public Boletim(float nota1, float nota2, float recuperacao,
			float provaFinal, float media, float notaMedia) {
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.recuperacao = recuperacao;
		this.media = media;
		this.notaMedia = notaMedia;
	}

	public float getNota1() {
		return nota1;
	}

	public void setNota1(String nota1) throws BoletimException {
		if(nota1.isEmpty())
			throw new BoletimException(BoletimException.NOTA1OBRIGATORIO);
		if(isFloat(nota1))
			throw new BoletimException(BoletimException.NUMEROINVALIDO);
		float var = Float.valueOf(nota1);
		if(!(var >= 0 && var <= 10))
			throw new BoletimException(BoletimException.NUMEROLIMITE);
		this.nota1 = var;
	}

	public float getNota2() {
		return nota2;
	}

	public void setNota2(String nota2) throws BoletimException {
		if(nota2.isEmpty())
			throw new BoletimException(BoletimException.NOTA2OBRIGATORIO);
		if(isFloat(nota2))
			throw new BoletimException(BoletimException.NUMEROINVALIDO);
		float var = Float.valueOf(nota2);
		if(!(var >= 0 && var <= 10))
			throw new BoletimException(BoletimException.NUMEROLIMITE);
		this.nota2 = var;
	}
	
	public float getRecuperacao() {
		return recuperacao;
	}

	public void setRecuperacao(String recuperacao) throws BoletimException {
		if(recuperacao.isEmpty())
			throw new BoletimException(BoletimException.RECUPERACAOOBRIGATORIO);
		if(isFloat(recuperacao))
			throw new BoletimException(BoletimException.NUMEROINVALIDO);
		float var = Float.valueOf(recuperacao);
		if(!(var >= 0 && var <= 10))
			throw new BoletimException(BoletimException.NUMEROLIMITE);
		this.recuperacao = var;
	}

	public float getProvaFinal() {
		return provaFinal;
	}

	public void setProvaFinal(String provaFinal) throws BoletimException {
		if(provaFinal.isEmpty())
			throw new BoletimException(BoletimException.PFOBRIGATORIO);
		if(isFloat(provaFinal))
			throw new BoletimException(BoletimException.NUMEROINVALIDO);
		float var = Float.valueOf(provaFinal);
		if(!(var >= 0 && var <= 10))
			throw new BoletimException(BoletimException.NUMEROLIMITE);
		this.provaFinal = var;
	}
	
	public float getNotaMedia() {
		return notaMedia;
	}
	
	public void setNotaMedia(String notaMedia) throws BoletimException {
		if(notaMedia.isEmpty())
			throw new BoletimException(BoletimException.MEDIADAINSTOBRIGATORIO);
		if(isFloat(notaMedia))
			throw new BoletimException(BoletimException.NUMEROINVALIDO);
		float var = Float.valueOf(notaMedia);
		if(!(var >= 0 && var <= 10))
			throw new BoletimException(BoletimException.NUMEROLIMITE);
		this.notaMedia = var;
	}
	
	public float getMedia() {
		return media;
	}
	
	public void setMedia(String media) throws BoletimException {
		if(media.isEmpty())
			throw new BoletimException(BoletimException.MEDIAOBRIGATORIO);
		if(isFloat(media))
			throw new BoletimException(BoletimException.NUMEROINVALIDO);
		float var = Float.valueOf(media);
		if(!(var >= 0 && var <= 10))
			throw new BoletimException(BoletimException.NUMEROLIMITE);
		this.media = var;
	}
	
	protected boolean isFloat(String string) {
		Object object = new Object();
		object = (Object) string;
		
		if(object instanceof Float)
			return true;
		else
			return false;
	}
	
}
