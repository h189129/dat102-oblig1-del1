package no.hvl.data102.filmarkiv.impl;

import java.util.Objects;

public class Film {
	private int filmNr;
	private String produsent;
	private String tittel; 
	private int aarstall;
	private Sjanger sjanger;
	private String filmSelskap; 	
	
	public Film() {
	    this.filmNr = -1;
	    this.produsent = "Ukjent";
	    this.tittel = "Ingen tittel";
	    this.aarstall = 0;
	    this.sjanger = null; 
	    this.filmSelskap = "Ukjent";
	}


	public Film(int filmNr, String produsent, String tittel, int aarstall, Sjanger sjanger, String filmSelskap) {
	    this.filmNr = filmNr;
	    this.produsent = produsent;
	    this.tittel = tittel;
	    this.aarstall = aarstall;
	    this.sjanger = sjanger;
	    this.filmSelskap = filmSelskap;
	}

	public int getFilmNr() {
	    return filmNr;
	}

	public void setFilmNr(int filmNr) {
	    this.filmNr = filmNr;
	}

	public String getProdusent() {
	    return produsent;
	}

	public void setProdusent(String produsent) {
	    this.produsent = produsent;
	}

	public String getTittel() {
	    return tittel;
	}

	public void setTittel(String tittel) {
	    this.tittel = tittel;
	}

	public int getAarstall() {
	    return aarstall;
	}

	public void setAarstall(int aarstall) {
	    this.aarstall = aarstall;
	}

	public Sjanger getSjanger() {
	    return sjanger;
	}

	public void setSjanger(Sjanger sjanger) {
	    this.sjanger = sjanger;
	}

	public String getFilmSelskap() {
	    return filmSelskap;
	}

	public void setFilmSelskap(String filmSelskap) {
	    this.filmSelskap = filmSelskap;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Film other = (Film) obj;
	    return filmNr == other.filmNr;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(filmNr);
	}

}
