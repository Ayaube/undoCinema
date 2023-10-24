package vue;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import CinemaExceptions.SaisieEntierException;
import modele.Film;

public class UtilSaisie {

    public static int lireEntierPositif(String question)
	    throws SaisieEntierException {
	Scanner scanner = new Scanner(System.in);
	System.out.println(question);
	String chaineEntier = scanner.nextLine();
	int nombre;
	try {
	    nombre = Integer.parseInt(chaineEntier);
	} catch (NumberFormatException e) {
	    throw new SaisieEntierException(chaineEntier + " pas un nombre ");
	}
	if (nombre < 0) {
	    throw new SaisieEntierException("l'entier doit �tre positif");
	}
	return nombre;
    }

    public static int lireEntierPositifEnBoucle(String question) {
	int nombre = -1;
	do {
	    try {
		nombre = lireEntierPositif(question);
	    } catch (SaisieEntierException e) {
		System.out.println(e.getMessage());
	    }
	} while (nombre == -1);
	return nombre;
    }

    public static int lireEntierPositifInferieurA(int n)
	    throws SaisieEntierException {
	if (n >= 1) {

	    Scanner scanner = new Scanner(System.in);
	    String chaineEntier = scanner.nextLine();
	    int nombre;
	    try {
		nombre = Integer.parseInt(chaineEntier);
	    } catch (NumberFormatException e) {
		throw new SaisieEntierException(
			chaineEntier + " pas un nombre ");
	    }
	    if (nombre < 0 || nombre > n) {
		throw new SaisieEntierException(
			"l'entier doit etre compris entre 1 et " + n);
	    }
	    return nombre;
	} else
	    throw new SaisieEntierException("le parametre doit etre positif");
    }

    public static List<Film> saisirFilmDeSalle(int i) {
	System.out.println(
		"entrer les noms des films (sous la forme nomfilm1 , nomfilm2 ...) pour la salle "
			+ i);

	Scanner sc = new Scanner(System.in);
	String[] nomFilms = sc.nextLine().split(",");
	List<Film> films = Arrays.stream(nomFilms).map(x -> new Film(x))
		.collect(Collectors.toList());
	return films;
    }

}
