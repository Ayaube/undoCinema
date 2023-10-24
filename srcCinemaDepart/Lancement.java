import java.util.ArrayList;

import modele.Cinema;
import modele.Film;
import vue.VueCinema;

public class Lancement {

    public static void main(String[] args) {
	Cinema melies = new Cinema();
	try {
	    ArrayList<Integer> l = new ArrayList<>();
	    l.add(20);
	    l.add(10);
	    //melies = new Cinema(2, l);
	    melies = new Cinema(l);
	    ArrayList<Film> filmsDeLaSalle0 = new ArrayList<>();
	    filmsDeLaSalle0.add(new Film("Les combattants"));
	    filmsDeLaSalle0.add(new Film("Lucy"));
	    filmsDeLaSalle0.add(new Film("Maintenant ou jamais"));
	    ArrayList<Film> filmsDeLaSalle1 = new ArrayList<>();
	    filmsDeLaSalle1.add(new Film("Delivre nous du mal"));
	    filmsDeLaSalle1.add(new Film("Les combattants"));
	    filmsDeLaSalle1.add(new Film("Hunger games"));
	    melies.getSalle(0).creerProgrammation(filmsDeLaSalle0);
	    melies.getSalle(1).creerProgrammation(filmsDeLaSalle1);
	    //			 melies.CreerProgrammeSalle(0, filmsDeLaSalle0);
	    //			 melies.CreerProgrammeSalle(1, filmsDeLaSalle1);
	    //			 melies.DemarrerJournee();
	    System.out.println(melies);

	} catch (Exception e) {
	    System.out.println("erreur d'initalisation");
	}

	VueCinema vue = new VueCinema(melies);
	vue.go();
    }
}
