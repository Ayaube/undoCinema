package modele;

public class Film {
    private String titre;

    public Film(String string) {
	titre = string;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((titre == null) ? 0 : titre.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	Film other = (Film) obj;
	if (titre == null) {
	    if (other.titre != null) return false;
	} else if (!titre.equals(other.titre)) return false;
	return true;
    }

    public String getNom() {
	return this.titre;
    }

    @Override
    public String toString() {
	return "Film [titre=" + titre + "]";
    }

}
