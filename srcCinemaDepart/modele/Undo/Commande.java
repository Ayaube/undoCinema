package modele.Undo;

public interface Commande {
    void execute();
    void undo();
}
