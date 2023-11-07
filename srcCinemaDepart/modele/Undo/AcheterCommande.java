package modele.Undo;

import modele.Cinema;
import modele.Salle;
import modele.Seance;
import CinemaExceptions.ErreurSalle;
import CinemaExceptions.ErreurSeanceEnCours;

public class AcheterCommande implements Commande {
    private Cinema cinema;
    private int nbBillets;
    private int numSalle;
    private int placesDisponiblesAvantAchat; // État à restaurer

    public AcheterCommande(Cinema cinema, int nbBillets, int numSalle) {
        this.cinema = cinema;
        this.nbBillets = nbBillets;
        this.numSalle = numSalle;

        try {
            Salle salle = cinema.getSalle(numSalle);
            Seance seanceEnCours = salle.getSeanceEnCours();
            this.placesDisponiblesAvantAchat = seanceEnCours.getNbPlacesDispo();
        } catch (ErreurSalle | ErreurSeanceEnCours e) {
            System.out.println("Erreur A");
        }
    }

    @Override
    public void execute() {
        try {
            cinema.acheter(nbBillets, numSalle);
        } catch (Exception e) {
            System.out.println("Erreur B");
        }
    }

    @Override
    public void undo() {
        try {
            Salle salle = cinema.getSalle(numSalle);
            Seance seanceEnCours = salle.getSeanceEnCours();

            // Restaurer le nombre de places disponibles avant l'achat
            seanceEnCours.setPlacesDisponible(placesDisponiblesAvantAchat);
        } catch (ErreurSalle | ErreurSeanceEnCours e) {
            System.out.println("Erreur lors de l'annulation de l'achat des billets: " + e.getMessage());
        }
    }

}
