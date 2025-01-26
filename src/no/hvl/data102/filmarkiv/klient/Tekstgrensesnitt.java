package no.hvl.data102.filmarkiv.klient;
import java.util.Scanner;
import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Sjanger;

public class Tekstgrensesnitt {
	
	// Leser inn opplysninger om en film fra tastatur og returnere et Film-objekt
	public Film lesFilm(){
		try (Scanner scanner = new Scanner(System.in)) {
	
	        System.out.print("Skriv inn filmnummer: ");
	        int filmNr = Integer.parseInt(scanner.nextLine());
	
	        System.out.print("Skriv inn produser: ");
	        String produsent = scanner.nextLine();
	
	        System.out.print("Skriv inn tittel: ");
	        String tittel = scanner.nextLine();
	
	        System.out.print("Skriv inn utgivelsesår: ");
	        int aarstall = Integer.parseInt(scanner.nextLine());
	
	        System.out.println("Choose genre (ACTION, DRAMA, COMEDY, HORROR, DOCUMENTARY): ");
	        Sjanger sjanger = null;
	        try {
	            sjanger = Sjanger.valueOf(scanner.nextLine().toUpperCase());
	        } catch (IllegalArgumentException e) {
	            System.out.println("Sjanger ikke tilgjengelig.");
	        }
	
	        System.out.print("Skriv inn filmselskap: ");
	        String filmSelskap = scanner.nextLine();
	
	        // Returnerer et nytt Film-objekt med de innleste verdiene
	        return new Film(filmNr, produsent, tittel, aarstall, sjanger, filmSelskap);
		}
	}
	
	// Skriver ut en film med alle opplysninger på skjerm (husk tekst for sjanger)
	public void skrivUtFilm(Film film) {
        
        System.out.println("Film Nummer: " + film.getFilmNr());
        System.out.println("Produsent: " + film.getProdusent());
        System.out.println("Tittel: " + film.getTittel());
        System.out.println("Utgitt: " + film.getAarstall());
        System.out.println("Sjanger: " + film.getSjanger());
        System.out.println("Filmselskap: " + film.getFilmSelskap());
        System.out.println();
	}
	
	// Skriver ut alle filmer med en spesiell delstreng i tittelen
	public void skrivUtFilmDelstrengITittel(FilmarkivADT arkiv, String delstreng) {
		Film[] resultater = arkiv.soekTittel(delstreng);
		
		if (resultater.length == 0) {
			System.out.println();
			System.out.println("Ingen titler samsvarer med: " + delstreng);
		} else {
			System.out.println();
			System.out.println("FILMER FUNNET: ");
			System.out.println();
			for (Film film : resultater) {
				skrivUtFilm(film);
			}	
		}
	}
	
	// Skriver ut alle Filmer av en produsent (produsent er delstreng)
	public void skrivUtFilmProdusent(FilmarkivADT arkiv, String delstreng) {
		Film[] resultater = arkiv.soekProdusent(delstreng);
		
		if (resultater.length == 0) {
			System.out.println();
			System.out.println("Ingen titler samsvarer med: " + delstreng);
		} else {
			System.out.println();
			System.out.println("FILMER FUNNET: ");
			System.out.println();
			for (Film film : resultater) {
				skrivUtFilm(film);
			}	
		}
	}
	
	// Skriver ut en enkel statistikk som inneholder antall filmer totalt
	// og hvor mange det er i hver sjanger.
	public void skrivUtStatistikk(FilmarkivADT arkiv) {
		System.out.println();
		System.out.println("Statistikk fra filmarkivet: ");
		System.out.println();
		System.out.println("Filmer totalt: " + arkiv.antall() + " stk.");
		System.out.println("Antall pr sjanger: ");
		System.out.println("Action: " + arkiv.antall(Sjanger.ACTION) + " stk.");
		System.out.println("History: " + arkiv.antall(Sjanger.HISTORY) + " stk.");
		System.out.println("Drama: " + arkiv.antall(Sjanger.DRAMA) + " stk.");
		System.out.println("Scifi: " + arkiv.antall(Sjanger.SCIFI) + " stk.");

	}
}

