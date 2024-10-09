package modele;

import java.util.Objects;

/**
 * Created by Desnyder Rémi
 * Date: 11/09/2024
 */

public class Livre {

    private String isbn;
    private String nom;
    private String auteur;
    private String editeur;
    private int nbPages;
    private int anneeParution;

    public Livre(String isbn, String nom, String auteur, String editeur, int nbPages, int anneeParution) {
        this.isbn = isbn;
        this.nom = nom;
        this.auteur = auteur;
        this.editeur = editeur;
        this.nbPages = nbPages;
        this.anneeParution = anneeParution;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getNom() {
        return nom;
    }

    public String getAffichageStandard() {
        return  "'" + this.nom + "', " + this.auteur + ", (" + this.editeur + ")";
    }

    @Override
    public String toString() {
        return "Livre{" +
                "isbn=" + isbn +
                ", nom='" + nom + '\'' +
                ", auteur='" + auteur + '\'' +
                ", editeur='" + editeur + '\'' +
                ", nbPages=" + nbPages +
                ", anneeParution=" + anneeParution +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return nbPages == livre.nbPages && anneeParution == livre.anneeParution && Objects.equals(isbn, livre.isbn) && Objects.equals(nom, livre.nom) && Objects.equals(auteur, livre.auteur) && Objects.equals(editeur, livre.editeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, nom, auteur, editeur, nbPages, anneeParution);
    }
}
