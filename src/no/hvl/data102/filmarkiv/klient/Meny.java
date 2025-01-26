package no.hvl.data102.filmarkiv.klient;
import java.util.Scanner;
import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Sjanger;

public class Meny {

    private Tekstgrensesnitt tekstgr;
    private FilmarkivADT filmarkiv;
    private Scanner tastatur;  // For å lese inn valg fra brukeren

    public Meny(FilmarkivADT filmarkiv) {
        tekstgr = new Tekstgrensesnitt();
        this.filmarkiv = filmarkiv;
        tastatur = new Scanner(System.in);
    }

    public void start() {
        leggInnStandardFilmer(); // Evt. legg inn noen testfilmer

        boolean fortsett = true;

        while (fortsett) {
            skrivHovedMeny();
            System.out.print("Angi valg: ");
            String input = tastatur.nextLine();

            switch (input) {
                case "0":
                    // Avslutt
                    fortsett = false;
                    System.out.println("Avslutter program...");
                    break;

                case "1":
                    // Legg til ny film
                    Film ny = tekstgr.lesFilm();
                    filmarkiv.leggTilFilm(ny);
                    System.out.println("Film lagt til!");
                    break;

                case "2":
                    // Søk etter tittel
                    System.out.print("Skriv inn del av tittel: ");
                    String delAvTittel = tastatur.nextLine();
                    tekstgr.skrivUtFilmDelstrengITittel(filmarkiv, delAvTittel);
                    break;

                case "3":
                    // Søk etter produsent
                    System.out.print("Skriv inn del av produsent: ");
                    String delAvProdusent = tastatur.nextLine();
                    tekstgr.skrivUtFilmProdusent(filmarkiv, delAvProdusent);
                    break;

                case "4":
                    // Slett en film
                    System.out.print("Hvilket filmnummer ønsker du å slette? ");
                    int filmnr = Integer.parseInt(tastatur.nextLine());
                    boolean slettet = filmarkiv.slettFilm(filmnr);
                    if (slettet) {
                        System.out.println("Film med nr " + filmnr + " ble slettet.");
                    } else {
                        System.out.println("Fant ikke film med nr " + filmnr + ".");
                    }
                    break;

                case "5":
                    // Vis statistikk (antall filmer, antall pr sjanger, osv.)
                    tekstgr.skrivUtStatistikk(filmarkiv);
                    break;

                default:
                    System.out.println("Ugyldig valg! Prøv på nytt.\n");
                    break;
            }
        }

        // Lukker ressursen før programmet avsluttes
        tastatur.close();
    }

    /**
     * Metode for å skrive ut menyvalg til brukeren.
     */
    private void skrivHovedMeny() {
        System.out.println("\n*** HOVEDMENY ***");
        System.out.println("0) Avslutt");
        System.out.println("1) Legg til ny film");
        System.out.println("2) Søk etter filmer (tittel)");
        System.out.println("3) Søk etter filmer (produsent)");
        System.out.println("4) Slett en film");
        System.out.println("5) Vis statistikk (antall filmer, sjangerfordeling)");
    }

    /**
     * Legger inn et sett med "standardfilmer" for testformål.
     */
    private void leggInnStandardFilmer() {
        // Eksempel med et par filmer:
        Film f1 = new Film(1, "Quentin Tarantino", "Pulp Fiction", 1994, Sjanger.ACTION, "Miramax");
        Film f2 = new Film(2, "Christopher Nolan", "Interstellar", 2014, Sjanger.SCIFI, "Warner Bros");
        Film f3 = new Film(3, "James Cameron", "Titanic", 1997, Sjanger.DRAMA, "Paramount");
        Film f4 = new Film(4, "Ridley Scott", "Gladiator", 2000, Sjanger.HISTORY, "DreamWorks Pictures");
		Film f5 = new Film(5, "Steven Spielberg", "Schindler's List", 1993, Sjanger.DRAMA, "Universal Pictures");		
		Film f6 = new Film(6, "Denis Villeneuve", "Arrival", 2016, Sjanger.SCIFI, "Paramount");		
		Film f7 = new Film(7, "David Fincher", "Fight Club", 1999, Sjanger.DRAMA, "20th Century Fox");		
		Film f8 = new Film(8, "Stanley Kubrick", "The Shining", 1980, Sjanger.DRAMA, "Warner Bros");		
		Film f9 = new Film(9, "George Lucas", "Star Wars: A New Hope", 1977, Sjanger.SCIFI, "Lucasfilm");		
		Film f10 = new Film(10, "Peter Jackson", "The Lord of the Rings: The Return of the King", 2003, Sjanger.ACTION, "New Line Cinema");


        filmarkiv.leggTilFilm(f1);
        filmarkiv.leggTilFilm(f2);
        filmarkiv.leggTilFilm(f3);
        filmarkiv.leggTilFilm(f4);
        filmarkiv.leggTilFilm(f5);
        filmarkiv.leggTilFilm(f6);
        filmarkiv.leggTilFilm(f7);
        filmarkiv.leggTilFilm(f8);
        filmarkiv.leggTilFilm(f9);
        filmarkiv.leggTilFilm(f10);
    }
}
