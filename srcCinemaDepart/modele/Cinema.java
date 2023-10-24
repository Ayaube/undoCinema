package modele;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import CinemaExceptions.ErreurCapacite;
import CinemaExceptions.ErreurSalle;
import CinemaExceptions.ErreurSeanceEnCours;
import CinemaExceptions.NombrePlacesErreur;

public class Cinema {
    private List<Salle> salles;

    public Cinema(List<Integer> capacitesDesSalles) throws ErreurCapacite {
	if (capacitesDesSalles.size() == 0) {
	    throw new ErreurCapacite(
		    "il faut au moins une salle pour creer un cinema");
	} else if (capacitesDesSalles.stream().anyMatch(n -> n < 0)) {
	    throw new ErreurCapacite(
		    "les salles doivent avoir un nombre de sièges positif");
	}

	this.salles = capacitesDesSalles.stream().map(n -> new Salle(n))
		.collect(Collectors.toList());
    }

    public Cinema() {
	// TODO Auto-generated constructor stub
    }

    public boolean journeeFinie() {
	return salles.stream().allMatch(salle -> salle.pasDeseanceEnCours());
    }

    public Salle getSalle(int numeroSalle) throws ErreurSalle {
	if (numeroSalle >= 0 && numeroSalle < this.salles.size()) {
	    return this.salles.get(numeroSalle);
	} else
	    throw new ErreurSalle("ce n'est pas un numero de salle");
    }

    @Override
    public String toString() {
	//return salles.stream().map(x -> x.toString()).reduce("Cinema : " + "\n",
	//	(x, y) -> x + (y + "\n"));
    	String s = "Cinema  \n" ;
    	for (int i=0; i<this.getNbSalles(); i++) {
    		s+= "Salle " + i + "\n" + this.salles.get(i).toString() + "\n";
    	}
    	return s;
    }

    public int getNbSalles() {
	return salles.size();
    }

    public void setSalles(List<Salle> salles) {
	this.salles = salles;
    }

    public List<Salle> getSalles() {
	return salles;
    }

    public void acheter(int nbBillets, int numSalle)
	    throws ErreurSalle, NombrePlacesErreur, ErreurSeanceEnCours {
	Salle s = this.getSalle(numSalle);
	s.acheterSeanceEnCours(nbBillets);
    }

    public void cloturerSeanceEnCours(int numSalle)
	    throws ErreurSeanceEnCours, ErreurSalle {
	this.getSalle(numSalle).finirSeance();
    }

    public void demarrerJournee() throws ErreurSeanceEnCours {
	// si une salle n'est pas a -1, il ne faut toucher aucune salle :
	// rare cas ou on ne peut programmer offensivement.
	if (this.journeeFinie()) {
	    for (int i = 0; i < this.getNbSalles(); i++) {
		try {
		    Salle salle = this.getSalle(i);
		    salle.demarrerSalle();

		} catch (ErreurSeanceEnCours e) {
		    System.out
			    .println("erreur impossible dans DemarrerJournee");
		} catch (ErreurSalle e) {
		    System.out
			    .println("erreur impossible dans DemarrerJournee");
		}
	    }
	} else
	    throw new ErreurSeanceEnCours(
		    "une des salles n'a pas termine sa journee");
    }

    public void CreerProgramme(List<List<Film>> lesFilms)
	    throws ErreurSeanceEnCours, ErreurSalle {
	for (int i = 0; i < this.getNbSalles(); i++) {
	    this.getSalle(i).creerProgrammation(lesFilms.get(i));
	}

    }

}
