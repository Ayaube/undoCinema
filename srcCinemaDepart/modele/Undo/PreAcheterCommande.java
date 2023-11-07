package modele.Undo;

import modele.Cinema;
import modele.Seance;
import modele.Salle;
import CinemaExceptions.NombrePlacesErreur;
import CinemaExceptions.ErreurSeanceEnCours;
import CinemaExceptions.ErreurSalle;

public class PreAcheterCommande implements Commande {
    private Cinema cinema;
    private int nbBillets;
    private int numSalle;
    private int numSeance;
    private int placesDisponiblesAvantAchat; // État à restaurer

    public PreAcheterCommande(Cinema cinema, int nbBillets, int numSalle, int numSeance) {
        this.cinema = cinema;
        this.nbBillets = nbBillets;
        this.numSalle = numSalle;
        this.numSeance = numSeance;

        // Enregistre l'état avant l'achat
        try {
            Salle salle = cinema.getSalle(numSalle);
            Seance seance = salle.getSeance(numSeance);
            this.placesDisponiblesAvantAchat = seance.getNbPlacesDispo();
        } catch (ErreurSalle | ErreurSeanceEnCours e) {
            // Gérer l'exception
        }
    }

    @Override
    public void execute() {
        try {
            Salle salle = cinema.getSalle(numSalle);
            Seance seance = salle.getSeance(numSeance);
            seance.acheter(nbBillets);
        } catch (NombrePlacesErreur | ErreurSeanceEnCours | ErreurSalle e) {
            // Gérer l'exception
        }
    }

    @Override
    public void undo() {
        try {
            Salle salle = cinema.getSalle(numSalle);
            Seance seance = salle.getSeance(numSeance);
            seance.setPlacesDisponible(placesDisponiblesAvantAchat);
        } catch (ErreurSalle | ErreurSeanceEnCours e) {
            // Gérer l'exception
        }
    }
}
