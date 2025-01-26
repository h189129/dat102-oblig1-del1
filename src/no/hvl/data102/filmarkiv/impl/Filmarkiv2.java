package no.hvl.data102.filmarkiv.impl;
import no.hvl.data102.filmarkiv.adt.FilmarkivADT;

public class Filmarkiv2 implements FilmarkivADT {

	private LinearNode<Film> start;
	private int antall;
	
	/**
     * Standardkonstruktør.
     * Initialiserer en tom kjede (start = null) og antall til 0.
     */
    public Filmarkiv2() {
        this.start = null;   // Ingen noder ennå
        this.antall = 0;     // Tomt arkiv
    }

	@Override
	public Film finnFilm(int nr) {
	    LinearNode<Film> current = start;

	    // Gå gjennom alle noder i listen
	    while (current != null) {
	        // Hent ut Film-objektet fra noden
	        Film film = current.getElement();

	        // Sjekk om denne filmen har ønsket filmnummer
	        if (film.getFilmNr() == nr) {
	            return film; // Returner om finner match
	        }
	        // Flytt til neste node
	        current = current.getNeste();
	    }

	    // Om nr ikke er funnet, returnes null
	    return null;
	}

	@Override
	public void leggTilFilm(Film nyFilm) {
		LinearNode<Film> nyNode = new LinearNode<>(nyFilm);
        nyNode.setNeste(start);
        start = nyNode;
        antall++;
	}

	@Override
	public boolean slettFilm(int filmnr) {
		
        if (start == null) {
            return false;  // Returnerer false ved tom liste
        }

        // 1. Sjekker om første node skal slettes
        if (start.getElement().getFilmNr() == filmnr) {
            start = start.getNeste();
            antall--;
            return true;
        }

        // 2. Sjekk noder fra nr. 2 og utover
        LinearNode<Film> current = start;
        while (current.getNeste() != null) {
            if (current.getNeste().getElement().getFilmNr() == filmnr) {
                current.setNeste(current.getNeste().getNeste());
                antall--;
                return true;
            }
            current = current.getNeste();
        }
        return false; // Fant ikke filmnummeret
	}

	@Override
	public Film[] soekTittel(String delstreng) {
		
		// Returnerer en tom tabell hvis delstrengen er null eller tom
		if (delstreng == null || delstreng.isEmpty()) {
	        return new Film[0]; 
	    }
		
		Film[] resultater = new Film[antall];
		LinearNode<Film> current = start;
		int antTreff = 0;
		delstreng = delstreng.toLowerCase(); 
	    
	    // Søker gjennom kjeden og lagrer resultatene i resultater.
	    while (current != null) {
	        String tittel = current.getElement().getTittel().toLowerCase(); 
	        if (tittel.contains(delstreng)) { 
	            resultater[antTreff] = current.getElement();
	            antTreff++;
	        }
	        current = current.getNeste();
	            
	     }
	    return trimTab(resultater, antTreff);
	}

	@Override
	public Film[] soekProdusent(String delstreng) {
		// Returnerer en tom tabell hvis delstrengen er null eller tom
		if (delstreng == null || delstreng.isEmpty()) {
	        return new Film[0]; 
	    }
		
		Film[] resultater = new Film[antall];
		LinearNode<Film> current = start;
		int antTreff = 0;
		delstreng = delstreng.toLowerCase(); 
	    
	    // Søker gjennom kjeden og lagrer resultatene i resultater.
	    while (current != null) {
	        String produsent = current.getElement().getProdusent().toLowerCase(); 
	        if (produsent.contains(delstreng)) { 
	            resultater[antTreff] = current.getElement();
	            antTreff++;
	        }
	        current = current.getNeste();
	            
	     }
	    return trimTab(resultater, antTreff);
	}

	@Override
	public int antall(Sjanger sjanger) {
		int antall = 0;
		LinearNode<Film> current = start;
		
		while (current != null) {
			if (current.getElement().getSjanger() == sjanger) {
				antall++;
				current = current.getNeste();
			}
		}
		return antall;
	}

	@Override
	public int antall() {
		int antall = 0;
		LinearNode<Film> current = start;
		
		while (current != null) {
			antall++;
			current = current.getNeste();
		}
		
		return antall;
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
