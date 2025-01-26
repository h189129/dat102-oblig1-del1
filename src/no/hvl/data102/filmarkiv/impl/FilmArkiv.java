package no.hvl.data102.filmarkiv.impl;
import no.hvl.data102.filmarkiv.adt.FilmarkivADT;

public class FilmArkiv implements FilmarkivADT {
	
	private Film[] arkiv;
	private Film[] utvidetTabell;
	private int antFilmer;
	private static final int STD_KAPASITET = 25;
	
	public FilmArkiv() {
	    this(STD_KAPASITET); 
	}

	public FilmArkiv(int kapasitet) {
	    arkiv = new Film[kapasitet];
	    antFilmer = 0;
	}
	

	
	@Override
	public Film finnFilm(int nr) {
		
		for (int i = 0; i < antFilmer; i++) {
			if (arkiv[i].getFilmNr() == nr) {
				return arkiv[i];
			}
		}
		return null;
	}
	
	
	@Override
	public void leggTilFilm(Film nyFilm) {
		
		// Oppretter ny tabell om full, legger til film om ikke
		if (arkiv.length == antFilmer) {
			utvidetTabell = new Film[arkiv.length * 2];
			for (int i = 0; i < arkiv.length; i++) {
				utvidetTabell[i] = arkiv[i];	
			}
			arkiv = utvidetTabell;
			antFilmer++;
			
		} else {
			arkiv[antFilmer] = nyFilm;
			antFilmer++;
			
		}
			
	}

	@Override
	public boolean slettFilm(int filmnr) {
		// Hvis tom
		if (antFilmer == 0) {
			return false;
		}
		
		int index = -1;
		
		// Finner indeks til film som skal slettes
		for (int i = 0; i < antFilmer; i++) {
			if (arkiv[i].getFilmNr() == filmnr) {
				index = i;
				break;
			}
		}
		
		// Film ikke funnet
		if (index == -1) {
			return false;
		}
			
		// Flytter filmer én plass til venstre
		for (int i = index; i < antFilmer - 1; i++) {
			arkiv[i] = arkiv[i + 1];
		}
		
		// Setter siste plass til null for å fjerne referanser til gamle objekter
		arkiv[antFilmer - 1] = null;
		
		// Reduserer ant filmer
		antFilmer--;
		
		return true;
	}

	@Override
	public Film[] soekTittel(String delstreng) {
		
		// Returnerer en tom tabell hvis delstrengen er null eller tom
		if (delstreng == null || delstreng.isEmpty()) {
	        return new Film[0]; 
	    }
		
		delstreng = delstreng.toLowerCase(); 
		Film[] midlertidig = new Film[antFilmer];
		int antTreff = 0;
	    
	    // Søker gjennom tabellen og lagrer resultatene i midlertidig
	    for (int i = 0; i < antFilmer; i++) {
	        String tittel = arkiv[i].getTittel().toLowerCase(); 
	        if (tittel.contains(delstreng)) { 
	            midlertidig[antTreff] = arkiv[i];
	            antTreff++;
	        }	    
	    }
	    return trimTab(midlertidig, antTreff);

	}

	@Override
	public Film[] soekProdusent(String delstreng) {
		// Returnerer en tom tabell hvis delstrengen er null eller tom
		if (delstreng == null || delstreng.isEmpty()) {
	        return new Film[0]; 
	    }
		
		delstreng = delstreng.toLowerCase(); 
		Film[] midlertidig = new Film[antFilmer];
		int antTreff = 0;
	    
	    // Søker gjennom tabellen og lagrer resultatene i midlertidig
	    for (int i = 0; i < antFilmer; i++) {
	        String tittel = arkiv[i].getProdusent().toLowerCase(); 
	        if (tittel.contains(delstreng)) { 
	            midlertidig[antTreff] = arkiv[i];
	            antTreff++;
	        }	    
	    }
	    return trimTab(midlertidig, antTreff);
	}

	@Override
	public int antall(Sjanger sjanger) {
		int antall = 0;
	
		for (int i = 0; i < antFilmer; i++) {
			if (arkiv[i].getSjanger() == sjanger ) {
				antall++;
			}	
		}
		return antall;	
	}

	@Override
	public int antall() {
		return antFilmer;
	}
	
	private Film[] trimTab(Film[] tab, int n) {
		// n er antall elementer
		Film[] nytab = new Film[n];
		int i = 0;
		while (i < n) {
			nytab[i] = tab[i];
			i++;
		}
		return nytab;
	}

	
}
