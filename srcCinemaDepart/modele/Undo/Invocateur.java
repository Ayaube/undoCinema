package modele.Undo;

import java.util.Stack;

public class Invocateur {
    private Stack<Commande> historique = new Stack<>();

    public void executeCommande(Commande commande) {
        try {
            commande.execute();
            historique.push(commande);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'exécution de la commande: " + e.getMessage());
        }
    }


    public void undo() {
        if (!historique.isEmpty()) {
            Commande commande = historique.pop();
            commande.undo();
        }
    }
}
