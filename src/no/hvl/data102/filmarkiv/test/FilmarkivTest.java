package no.hvl.data102.filmarkiv.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.FilmArkiv;
import no.hvl.data102.filmarkiv.impl.Sjanger;

public class FilmarkivTest { // Testklassen
    

    private FilmArkiv arkiv;      
    
    // Film objekt til testformål
    private Film film1;
    private Film film2;
    private Film film3;

    @BeforeEach
    public void setup() {
        // Metode som resetter state før hver test. 
        arkiv = new FilmArkiv(10);  

        // Initialiserer noen filmobjekter
        film1 = new Film(1, "Quentin Tarantino", "Pulp Fiction", 1994, Sjanger.ACTION, "Miramax");
        film2 = new Film(2, "Christopher Nolan", "Interstellar", 2014, Sjanger.SCIFI, "Paramount");
        film3 = new Film(3, "James Cameron", "Titanic", 1997, Sjanger.DRAMA, "Paramount");
    }

    @Test
    public void testLeggTilFilm() {
        // Tomt arkiv?
        assertEquals(0, arkiv.antall(), "arkiv skal være tomt til å begynne med");

        // Legger til en film og tester at antall oppdateres
        arkiv.leggTilFilm(film1);
        assertEquals(1, arkiv.antall(), "arkiv skal ha én film etter å ha lagt til én film");
        assertEquals(film1, arkiv.finnFilm(1), "film1 bør kunne hentes ut fra film nummer");

        // Legger til en film til og tester at antall oppdateres 
        arkiv.leggTilFilm(film2);
        assertEquals(2, arkiv.antall(), "arkiv bør ha to arkiv.");
        assertEquals(film2, arkiv.finnFilm(2), "film2 bør kunne hentes ut fra film nummer");

        
    }
    
    @Test
    public void testFinnFilm() {
        arkiv.leggTilFilm(film1);
        arkiv.leggTilFilm(film2);

        // Sjekker at film1 er i arkiv
        Film found = arkiv.finnFilm(1);
        assertNotNull(found, "Skal finne film1");
        assertEquals(film1, found, "Film skal matche film1.");

        // Sjekker at film2 er i arkiv
        found = arkiv.finnFilm(2);
        assertNotNull(found, "Skal finne film2");
        assertEquals(film2, found, "Skal matche film2");

        // Sjekker at film med nr 999 ikke er i arkiv
        found = arkiv.finnFilm(999);
        assertNull(found, "Skal returnere null for film som ikke er i arkiv");
    }
    
    @Test
    public void testSlettFilm() {
        arkiv.leggTilFilm(film1);
        arkiv.leggTilFilm(film2);
        arkiv.leggTilFilm(film3);

        // Sletting av eksisterende film skal returnere true.
        boolean result = arkiv.slettFilm(2);  // film2
        assertTrue(result, "Returnerer true om film2 blir slettet");
        assertEquals(2, arkiv.antall(), "Det skal være igjen 2 arkiv etter å ha slettet én.");
        assertNull(arkiv.finnFilm(2), "Film2 skal ikke finnes etter å ha blitt slettet.");

        // Sletting av ikke eksisterende film skal returnere false. 
        result = arkiv.slettFilm(999);
        assertFalse(result, "Skal returnere false.");
        assertEquals(2, arkiv.antall(), "Ingen arkiv skal bli slettet når ikke funnet.");
    }
    
    @Test
    public void testSoekTittel() {
        arkiv.leggTilFilm(film1);  // "Pulp Fiction"
        arkiv.leggTilFilm(film2);  // "Interstellar"
        arkiv.leggTilFilm(film3);  // "Titanic"

        // Søk med null skal returnere tom tabell
        Film[] result = arkiv.soekTittel(null);
        assertEquals(0, result.length, "Null substring skal returnere tom tabell.");

        result = arkiv.soekTittel("");
        assertEquals(0, result.length, "Tom string skal returnere tom tabell.");
        
        // Søk etter "pulp"
        result = arkiv.soekTittel("pulp");
        assertEquals(1, result.length, "Skal finne nøyaktig 1 film (Pulp Fiction) for 'pulp'");
        assertEquals(film1, result[0], "Skal være Pulp Fiction");

        // Søk etter "titanic"
        result = arkiv.soekTittel("tanic");
        assertEquals(1, result.length, "Kun Titanic har 'tanic' i seg.");
        assertEquals(film3, result[0], "Skal være Titanic");
    }
    
    @Test
    public void testSoekProdusent() {
        arkiv.leggTilFilm(film1);  // "Quentin Tarantino"
        arkiv.leggTilFilm(film2);  // "Christopher Nolan"
        arkiv.leggTilFilm(film3);  // "James Cameron"

        // Søk med tom streng skal returnere tom tabell
        Film[] result = arkiv.soekProdusent("");
        assertEquals(0, result.length, "Søk med tom streng skal returnere tom tabell");

        // Søk etter "Nolan"
        result = arkiv.soekProdusent("Nolan");
        assertEquals(1, result.length, "Skal finne nøyaktig 1 film av Nolan");
        assertEquals(film2, result[0], "Skal finne -> Interstellar (Christopher Nolan)");

        // Søk etter "James"
        result = arkiv.soekProdusent("James");
        assertEquals(1, result.length, "Skal finne nøyaktig 1 film av James Cameron");
        assertEquals(film3, result[0], "Skal finne -> Titanic (James Cameron)");

        // Søk etter noe som ikke er i arkiv
        result = arkiv.soekProdusent("Peter Jackson");
        assertEquals(0, result.length, "Ingen av arkiv av Peter Jackson er i arkiv");
    }
    
    @Test
    public void testAntallSjanger() {
        // film1 = ACTION
        // film2 = SCIFI
        // film3 = DRAMA

        arkiv.leggTilFilm(film1);
        arkiv.leggTilFilm(film2);
        arkiv.leggTilFilm(film3);

        assertEquals(1, arkiv.antall(Sjanger.ACTION), "Skal være 1 ACTION film");
        assertEquals(1, arkiv.antall(Sjanger.SCIFI), "Skal være 1 SCIFI film");
        assertEquals(1, arkiv.antall(Sjanger.DRAMA), "Skal være 1 DRAMA film");

        // Ikke i arkiv: HISTORY, forventer 0
        assertEquals(0, arkiv.antall(Sjanger.HISTORY), "Det skal ikke være HISTORY sjanger i arkiv");
    }
    
    @Test
    public void testAntall() {
        assertEquals(0, arkiv.antall(), "arkiv starter tom");

        arkiv.leggTilFilm(film1);
        assertEquals(1, arkiv.antall(), "Skal være 1 etter å ha lagt til film1");

        arkiv.leggTilFilm(film2);
        arkiv.leggTilFilm(film3);
        assertEquals(3, arkiv.antall(), "Skal være 3 etter å ha lagt til film2 og film3");
    }





}
