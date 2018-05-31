package it.polito.tdp.borders.model;

public class Country {
	
	private String abbrevStato;
	private String nomeStato;
	private int codiceContinente;
	private int numVicini;
	
	public Country() {	
		
	}
	
	
	public int getNumVicini() {
		return numVicini;
	}
	public void setNumVicini(int numVicini) {
		this.numVicini = numVicini;
	}


	public Country(int codiceContinente, String abbrevStato, String nomeStato) {
		this.abbrevStato = abbrevStato;
		this.nomeStato = nomeStato;
		this.codiceContinente = codiceContinente;
		numVicini = 0;
	}

	public String getAbbrevStato() {
		return abbrevStato;
	}

	public void setAbbrevStato(String abbrevStato) {
		this.abbrevStato = abbrevStato;
	}

	public String getNomeStato() {
		return nomeStato;
	}

	public void setNomeStato(String nomeStato) {
		this.nomeStato = nomeStato;
	}

	public int getCodiceContinente() {
		return codiceContinente;
	}

	public void setCodiceContinente(int codiceContinente) {
		this.codiceContinente = codiceContinente;
	}

	@Override
	public String toString() {
		
		return abbrevStato + ", " + nomeStato + ", "
				+ codiceContinente + " , "+ this.getNumVicini();
	}
	
	public String toStringWithVicini() {
		
		StringBuilder risultato = new StringBuilder();
		
		risultato.append(this.nomeStato + "\n");
		
		risultato.append("Codice Stato: " + this.codiceContinente + " - "
				+ "Abbreviazione: " + this.abbrevStato + "\n");
		
		risultato.append("Numero Stati confinanti: " + this.numVicini + "\n");
		
		risultato.append("\n");
		return risultato.toString();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codiceContinente;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (codiceContinente != other.codiceContinente)
			return false;
		return true;
	}
	
	
	
	

}
