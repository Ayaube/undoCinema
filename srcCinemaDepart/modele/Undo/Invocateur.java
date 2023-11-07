package modele.Undo;

import java.util.Stack;

public class Invocateur {
    private Stack<Commande> historique = new Stack<>();
    private Stack<Commande> redoStack = new Stack<>();

    public void executeCommande(Commande commande) {
        commande.execute();
        historique.push(commande);
        redoStack.clear();
    }

    public void undo() {
        if (!historique.isEmpty()) {
            Commande commande = historique.pop();
            commande.undo();
            redoStack.push(commande);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Commande commande = redoStack.pop();
            commande.execute();
            historique.push(commande);
        }
    }
}
