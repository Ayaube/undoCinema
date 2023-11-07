package vue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import modele.Cinema;
import modele.Undo.AcheterCommande;
import modele.Undo.Commande;
import modele.Film;

import CinemaExceptions.ErreurSalle;
import CinemaExceptions.ErreurSeanceEnCours;

import CinemaExceptions.NombrePlacesErreur;
import CinemaExceptions.SaisieEntierException;
import modele.Undo.Invocateur;

public class VueCinema implements Commande {
    private Cinema leCinema;
	private Invocateur invocateur = new Invocateur();

    public VueCinema(Cinema bib) {
	this.leCinema = bib;
    }

	public int menuEtSaisie() {
		int rep = 0;
		try {
			System.out.println("1: creer la programmation");
			System.out.println("2 : demarrer la journee ");
			System.out.println("3 : cloturer la seance courante d'une salle ");
			System.out.println("4 : acheter des billets");
			System.out.println("5 : afficher le cinema");
			System.out.println("6 : undo");
			System.out.println("7 : redo"); // Correction ici pour "redo"
			System.out.println("8 : quitter"); // Ajout d'une option pour quitter
			System.out.println("   Entrez votre choix: ");
			rep = UtilSaisie.lireEntierPositifInferieurA(9);
		} catch (SaisieEntierException e) {
			System.out.println(e.getMessage());
			menuEtSaisie();
		}
		return rep;
	}

	public void go() {
		boolean encore = true;
		while (encore) {
			int choix = menuEtSaisie();
			switch (choix) {
				case 1:
					faireProgrammation();
					break;
				case 2:
					demarrerJournee();
					break;
				case 3:
					cloturer();
					break;
				case 4:
					acheter();
					break;
				case 5:
					System.out.println(leCinema);
					break;
				case 6:
					invocateur.undo();
					break;
				case 7:
					invocateur.redo();
					break;
				case 8:
					System.out.println("Au revoir");
					encore = false;
					break;
				default:
					System.out.println("Option non reconnue, veuillez réessayer.");
					break;
			}
		}
	}

    }

    private void acheter() {
	int numSalle = UtilSaisie
		.lireEntierPositifEnBoucle("entrer un numero de salle");
	System.out.println(numSalle);
	int nbBillets = UtilSaisie
		.lireEntierPositifEnBoucle("entrer un nombre de billets  ");
	System.out.println(nbBillets);
	try {
	    leCinema.acheter(nbBillets, numSalle);
	    System.out.println("achat effectue ");
	} catch (ErreurSalle e) {
	    System.out.println(e.getMessage());
	    acheter();
	} catch (NombrePlacesErreur e) {
	    System.out.println("erreur dans le nombre de places");
	    System.out.println(e.getMessage());
	    acheter();
	} catch (ErreurSeanceEnCours e) {
	    System.out.println("probleme ");
	    System.out.println(e.getMessage());
	} catch (Exception e) {
	    System.out.println(
		    "il ne devrait pas y avoir d'erreur de ce type ...");
	}

		AcheterCommande acheterCommande = new AcheterCommande(leCinema, nbBillets, numSalle);
		invocateur.executeCommande(acheterCommande);
    }

    private void cloturer() {
	int numSalle = UtilSaisie
		.lireEntierPositifEnBoucle("entrer un numero de salle");
	System.out.println(numSalle);
	try {
	    leCinema.cloturerSeanceEnCours(numSalle);
	    System.out.println("la seance a bien etet cloturee");
	} catch (ErreurSalle e) {
	    System.out.println(e.getMessage());
	    cloturer();
	} catch (ErreurSeanceEnCours e) {
	    System.out.println(e.getMessage());
	    // ici on choisit de remonter au menu
	} catch (Exception e) {
	    System.out.println(
		    "il ne devrait pas y avoir d'erreur de ce type ...");
	}
    }

    private void demarrerJournee() {
	try {
	    leCinema.demarrerJournee();
	    System.out.println("journee demarree");
	} catch (ErreurSeanceEnCours e) {
	    System.out.println(e.getMessage());
	} catch (Exception e) {
	    System.out.println(
		    "il ne devrait pas y avoir d'erreur de ce type ...");
	}

    }

    private void faireProgrammation() {
	if (leCinema.journeeFinie()) {
	    List<List<Film>> lesFilms = Stream.iterate(0, n -> n + 1)
		    .limit(leCinema.getNbSalles())
		    .map(i -> UtilSaisie.saisirFilmDeSalle(i))
		    .collect(Collectors.toList());
	    try {
		leCinema.CreerProgramme(lesFilms);
	    } catch (ErreurSeanceEnCours e) {
		System.out.println("erreur impossible dans faireProgrammation");
	    } catch (Exception e) {
		System.out.println(
			"il ne devrait pas y avoir d'erreur de ce type ...");
		e.printStackTrace();
	    }
	    System.out.println("programmation terminee");
	}

	else
	    System.out.println("les seances ne sont pas toutes terminees");
    }

	@Override
	public void execute() {

	}

	@Override
	public void undo() {

	}
}
