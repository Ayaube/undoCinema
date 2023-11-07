package modele;

import CinemaExceptions.NombrePlacesErreur;

public class Seance {
    private Film film;
    private int placesDisponible;

    public Seance(Film f, int nbPlacesDispo) {
	this.film = f;
	this.placesDisponible = nbPlacesDispo;
    }

    public int getNbPlacesDispo() {
	return placesDisponible;
    }

    public Film getFilm() {
	return film;
    }

    public void setPlacesDisponibles(int places) {
        this.placesDisponibles = places;
    }

    public void acheter(int nbBillets) throws NombrePlacesErreur {
	if (this.getNbPlacesDispo() >= nbBillets) {
	    placesDisponible = placesDisponible - nbBillets;
	} else
	    throw new NombrePlacesErreur("pas assez de places");
    }

    @Override
    public String toString() {
	return (" film : " + film.getNom() + " places disponibles : "
		+ placesDisponible);
    }

}
