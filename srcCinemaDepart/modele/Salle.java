package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import CinemaExceptions.ErreurCapacite;
import CinemaExceptions.ErreurSeanceEnCours;
import CinemaExceptions.NombrePlacesErreur;

public class Salle {
    private List<Seance> seances;
    private int indiceSeanceEnCours;
    private int capacite;

    public Salle(Integer capacite) {
	this.seances = new ArrayList<>();
	this.indiceSeanceEnCours = -1;
	if (capacite > 0) {
	    this.capacite = capacite;
	}
    }

    public void finirSeance() throws ErreurSeanceEnCours {
    	  if (derniereSeance()) {
              indiceSeanceEnCours = -1;
          } else if (indiceSeanceEnCours == -1) {
              throw new ErreurSeanceEnCours("pas de seances en cours");
          } else {
              indiceSeanceEnCours = indiceSeanceEnCours + 1;
          }
    }
    
    public boolean derniereSeance() {
        return indiceSeanceEnCours == seances.size() - 1;
    }

    public Seance getSeanceEnCours() throws ErreurSeanceEnCours {
	if (indiceSeanceEnCours != -1) {
	    return seances.get(indiceSeanceEnCours);
	} else
	    throw new ErreurSeanceEnCours(
		    ("les seances sont terminees dans cette salle"));
    }

    public void demarrerSalle() throws ErreurSeanceEnCours {
	if (indiceSeanceEnCours == -1) {
	    indiceSeanceEnCours = 0;
	    for (Seance s : seances) {
		s.setPlacesDisponible(capacite);
	    }
	} else
	    throw new ErreurSeanceEnCours(
		    "on ne peut demarer une salle dont toutes les seances ne sont pas achevees");
    }

    // **** propagation des erreurs de acheter(prob de billets) et
    // de getSeanceenCours(programmation terminee) 
    public void acheterSeanceEnCours(int nbBillets)
	    throws NombrePlacesErreur, ErreurSeanceEnCours {
        Seance seanceEnCours = this.getSeanceEnCours();
        seanceEnCours.acheter(nbBillets);
    }

    public boolean pasDeseanceEnCours() {
	return indiceSeanceEnCours == -1;
    }

    public void creerProgrammation(List<Film> filmsDeLaSalleI)
	    throws ErreurSeanceEnCours {
	if (this.pasDeseanceEnCours()) {
	    this.seances = filmsDeLaSalleI.stream()
		    .map(f -> new Seance(f, this.capacite))
		    .collect(Collectors.toList());
	    this.indiceSeanceEnCours = -1;
	} else
	    throw new ErreurSeanceEnCours(
		    "toutes les seances ne sont pas terminees");
    }

    public int getCapacite() {
	return this.capacite;
    }

    public int getIndiceSeanceEnCours() {
	return indiceSeanceEnCours;
    }

    // pour les tests
    public Seance getSeance(int indice) {
	return this.seances.get(indice);
    }

    // Pour les tests 
    public void setSeanceEnCours(int seanceEnCours) throws ErreurSeanceEnCours {
	if (seanceEnCours >= this.seances.size() || seanceEnCours <= -2) {
	    throw new ErreurSeanceEnCours(
		    "pas de seance numero" + seanceEnCours);
	}
	this.indiceSeanceEnCours = seanceEnCours;
    }

    public int getNbSeance() {
	return seances.size();
    }

    public void setSeances(List<Seance> anciennesSeances) {
	this.seances = anciennesSeances;
    }

    public List<Seance> getSeances() {
	return seances;
    }

    @Override
    public String toString() {
	String s = "     " + " seances en cours : " + indiceSeanceEnCours
		+ " capacite : " + capacite + " \n";
	for (int i = 0; i < seances.size(); i++) {
	    s = s + "     seance " + i + " : " + seances.get(i).toString()
		    + "\n";
	}
	return s;
    }
}
