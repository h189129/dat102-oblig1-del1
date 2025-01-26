package no.hvl.data102.filmarkiv.klient;
import no.hvl.data102.filmarkiv.adt.*;
import no.hvl.data102.filmarkiv.impl.*;

public class FilmArkivMain {

	public static void main(String[] args) {
		FilmarkivADT filmer = new FilmArkiv(100);
			Meny meny = new Meny(filmer);
			meny.start();
		}
}
