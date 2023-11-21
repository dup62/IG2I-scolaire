package fr.remidesnyder.livraisonBieres.exceptions;

/**
 * Created by Desnyder Rémi
 * Date: 21/11/2023
 */

public class ExceptionQuantite extends Exception {

    private int quantite;

    public ExceptionQuantite(int quantite) {
        super("La quantité de bière à livrer (" + quantite + ") doit être supérieure à 0");
        this.quantite = quantite;
    }

    public ExceptionQuantite(int quantite, String message) {
        super(message);
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    @Override
    public String toString() {
        return "ExceptionQuantite{" +
                "quantite=" + quantite +
                '}';
    }
}
