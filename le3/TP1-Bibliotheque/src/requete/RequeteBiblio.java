package requete;

import modele.Emprunt;
import modele.Livre;
import modele.Usager;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Desnyder Rémi
 * Date: 11/09/2024
 */

public class RequeteBiblio {

    private Connection connection;

    public RequeteBiblio(Connection connection) throws SQLException {

        String urlBDD = "jdbc:mysql://localhost:8889/tp1-le3-poo-bibliotheque";
        String utilisateur = "root";
        String motDePasse = "root";

        Connection connexionBdd = DriverManager.getConnection(urlBDD,
                utilisateur, motDePasse);

        this.connection = connexionBdd;
    }

    public List<Livre> getLivresOrderByTitle() throws SQLException {

        List<Livre> livres = new ArrayList<>();

        String requete = "SELECT * FROM livre ORDER BY titre";
        /*Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery(requete);

        while (result.next()) {
            String titre = result.getString("titre");
            String auteur = result.getString("auteur");
            String editeur = result.getString("editeur");
            int nbPages = result.getInt("nb_pages");
            int anneeParution = result.getInt("annee_publication");
            String isbn = result.getString("isbn");

            Livre livre = new Livre(isbn, titre, auteur, editeur, nbPages, anneeParution);
            livres.add(livre);
        }

        result.close();
        statement.close();

        return livres;*/
        try (Statement statement = this.connection.createStatement();
             ResultSet result = statement.executeQuery(requete)) {

            while (result.next()) {
                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String editeur = result.getString("editeur");
                int nbPages = result.getInt("nb_pages");
                int anneeParution = result.getInt("annee_publication");
                String isbn = result.getString("isbn");

                Livre livre = new Livre(isbn, titre, auteur, editeur, nbPages, anneeParution);
                livres.add(livre);
            }
        }

        return livres;
    }

    public List<Usager> getUsagerOrderByName() throws SQLException {

        List<Usager> usagers = new ArrayList<>();

        String requete = "SELECT * FROM usager ORDER BY nom,prenom";
        /*Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery(requete);

        while (result.next()) {
            int idUsager = result.getInt("id_usager");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adresse = result.getString("adresse");
            Date dateNaissance = result.getDate("date_naissance");

            Usager usager = new Usager(idUsager, nom, prenom, adresse, dateNaissance);
            usagers.add(usager);

        }

        result.close();
        statement.close();

        return usagers;*/
        try (Statement statement = this.connection.createStatement();
             ResultSet result = statement.executeQuery(requete)) {

            while (result.next()) {
                int idUsager = result.getInt("id_usager");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adresse = result.getString("adresse");
                Date dateNaissance = result.getDate("date_naissance");

                Usager usager = new Usager(idUsager, nom, prenom, adresse, dateNaissance);
                usagers.add(usager);
            }
        }

        return usagers;
    }

    public HashSet<Emprunt> getEmpruntActuel() throws SQLException {

        HashSet<Emprunt> emprunts = new HashSet<>();

        String requete = "SELECT *\n" +
                "    FROM emprunt\n" +
                "    INNER JOIN usager ON emprunt.fk_id_usager = usager.id_usager\n" +
                "    INNER JOIN livre ON emprunt.fk_isbn = livre.isbn\n" +
                "    WHERE date_retour IS NULL";
        try (Statement statement = this.connection.createStatement();
             ResultSet result = statement.executeQuery(requete)) {

            while (result.next()) {
                int idEmprunt = result.getInt("id_emprunt");
                Date dateEmprunt = result.getDate("date_emprunt");
                Date dateRetour = result.getDate("date_retour");
                int idUsager = result.getInt("fk_id_usager");
                String isbnLivre = result.getString("fk_isbn");

                String nomUsager = result.getString("nom");
                String prenomUsager = result.getString("prenom");
                String adresseUsager = result.getString("adresse");
                Date dateNaissanceUsager = result.getDate("date_naissance");
                Usager usager = new Usager(idUsager, nomUsager, prenomUsager, adresseUsager, dateNaissanceUsager);

                String titreLivre = result.getString("titre");
                String auteurLivre = result.getString("auteur");
                String editeurLivre = result.getString("editeur");
                int nbPagesLivre = result.getInt("nb_pages");
                int anneeParutionLivre = result.getInt("annee_publication");
                Livre livre = new Livre(isbnLivre, titreLivre, auteurLivre, editeurLivre, nbPagesLivre, anneeParutionLivre);

                Emprunt emprunt = new Emprunt(idEmprunt, dateEmprunt, dateRetour, usager, livre);
                emprunts.add(emprunt);
            }
        }

        return emprunts;
    }

    public List<Livre> getLivreWhereTitleOrEditeurOrAuteur(String recherche) throws SQLException {

        List<Livre> livres = new ArrayList<>();

        String requete = "SELECT * FROM livre WHERE titre LIKE ? OR editeur LIKE ? OR auteur LIKE ?";
        PreparedStatement prepStatement =
                this.connection.prepareStatement(requete);
        prepStatement.setString(1, "%" + recherche + "%");
        prepStatement.setString(2, "%" + recherche + "%");
        prepStatement.setString(3, "%" + recherche + "%");
        ResultSet result = prepStatement.executeQuery();

        while (result.next()) {
            String titre = result.getString("titre");
            String auteur = result.getString("auteur");
            String editeur = result.getString("editeur");
            int nbPages = result.getInt("nb_pages");
            int anneeParution = result.getInt("annee_publication");
            String isbn = result.getString("isbn");

            Livre livre = new Livre(isbn, titre, auteur, editeur, nbPages, anneeParution);
            livres.add(livre);
        }

        result.close();
        prepStatement.close();

        return livres;
    }

    public List<Usager> getUsagerWhereNomOrPrenom(String recherche) throws SQLException {
        List<Usager> usagers = new ArrayList<>();

        String requete = "SELECT * FROM usager WHERE nom LIKE ? OR prenom LIKE ?";
        PreparedStatement prepStatement =
                this.connection.prepareStatement(requete);
        prepStatement.setString(1, "%" + recherche + "%");
        prepStatement.setString(2, "%" + recherche + "%");
        ResultSet result = prepStatement.executeQuery();

        while (result.next()) {
            int idUsager = result.getInt("id_usager");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adresse = result.getString("adresse");
            Date dateNaissance = result.getDate("date_naissance");

            Usager usager = new Usager(idUsager, nom, prenom, adresse, dateNaissance);
            usagers.add(usager);
        }

        result.close();
        prepStatement.close();

        return usagers;
    }

    public List<Emprunt> getLateEmprunt(int nbJourRetard) throws SQLException {

        List<Emprunt> emprunts = new ArrayList<>();

        String requete = "SELECT * FROM emprunt WHERE date_retour IS NULL AND DATEDIFF(CURRENT_DATE, date_emprunt) >= ?";
        PreparedStatement prepStatement =
                this.connection.prepareStatement(requete);
        prepStatement.setInt(1, nbJourRetard);
        ResultSet result = prepStatement.executeQuery();

        while (result.next()) {
            int idEmprunt = result.getInt("id_emprunt");
            Date dateEmprunt = result.getDate("date_emprunt");
            Date dateRetour = result.getDate("date_retour");
            int idUsager = result.getInt("fk_id_usager");
            String isbnLivre = result.getString("fk_isbn");

            Emprunt emprunt = new Emprunt(idEmprunt, dateEmprunt, dateRetour, null, null);
            emprunts.add(emprunt);
        }

        result.close();
        prepStatement.close();

        return emprunts;
    }

    public void giveBackLivre(Livre livre) throws SQLException {

        if (livre == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être null");
        }

        String requete = "UPDATE emprunt SET date_retour = ? WHERE fk_isbn = ? AND date_retour IS NULL";
        PreparedStatement prepStatement =
                this.connection.prepareStatement(requete);
        prepStatement.setDate(1, new Date(System.currentTimeMillis()));
        prepStatement.setString(2, livre.getIsbn());
        prepStatement.executeUpdate();
        prepStatement.close();
    }

    public void emprunterLivre(Livre livre, Usager usager) throws SQLException {

        if (livre == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être null");
        }

        if (usager == null) {
            throw new IllegalArgumentException("L'usager ne peut pas être null");
        }

        String requete = "INSERT INTO emprunt (date_emprunt, fk_id_usager, fk_isbn) VALUES (?,?,?)";
        PreparedStatement prepStatement =
                this.connection.prepareStatement(requete);
        prepStatement.setDate(1, new Date(System.currentTimeMillis()));
        prepStatement.setInt(2, usager.getIdUsager());
        prepStatement.setString(3, livre.getIsbn());
        prepStatement.executeUpdate();
        prepStatement.close();
    }

    public static void main(String[] args) {
        try {
            RequeteBiblio biblio = new RequeteBiblio(null);

            List<Livre> livres = biblio.getLivresOrderByTitle();
            System.out.println(livres.size() + " livres");
            System.out.println(livres);

            List<Usager> usagers = biblio.getUsagerOrderByName();
            System.out.println(usagers.size() + " usagers");
            System.out.println(usagers);

            Set<Emprunt> emprunts = biblio.getEmpruntActuel();
            System.out.println(emprunts.size() + " emprunts");
            System.out.println(emprunts);

            List<Livre> livresRecherche = biblio.getLivreWhereTitleOrEditeurOrAuteur("Gallimard");
            System.out.println("    ----> Recherche de livres contenant 'Gallimard'");
            System.out.println(livresRecherche.size() + " livres");
            System.out.println(livresRecherche);

            List<Usager> usagersRecherche = biblio.getUsagerWhereNomOrPrenom("Dufresne");
            System.out.println("    ----> Recherche d'usagers contenant 'Dufresne'");
            System.out.println(usagersRecherche.size() + " usagers");
            System.out.println(usagersRecherche);

            List<Emprunt> empruntsRetard = biblio.getLateEmprunt(10);
            System.out.println(empruntsRetard.size() + " emprunts en retard");
            System.out.println(empruntsRetard);

            Livre unLivre = null;
            for (Livre livre : livres) {
                if (livre.getIsbn().equals("9782846820820")) {
                    unLivre = livre;
                    break;
                }
            }
            assert unLivre != null;
            System.out.println(unLivre);
            biblio.giveBackLivre(unLivre);

            Livre unAutreLivre = null;
            Usager unUsager = null;
            for (Livre livre : livres) {
                if (livre.getIsbn().equals("9782846820820")) {
                    unAutreLivre = livre;
                    break;
                }
            }
            for (Usager usager : usagers) {
                if (usager.getIdUsager() == 1) {
                    unUsager = usager;
                    break;
                }
            }
            assert unAutreLivre != null;
            assert unUsager != null;
            biblio.emprunterLivre(unAutreLivre, unUsager);

            // Liste de livre puis liste des livres empruntés puis check

            List<Livre> livres2 = biblio.getLivresOrderByTitle();
            System.out.println("\n\n\n   ----> Liste des livres (" + livres2.size() + ")");
            HashSet<Emprunt> emprunts2 = biblio.getEmpruntActuel();

            String ANSI_RED = "\u001B[31m";
            String ANSI_GREEN = "\u001B[32m";

            for (Livre livre : livres2) {
                String livreName = livre.getNom();
                if (emprunts2.stream().anyMatch(emprunt -> emprunt.getLivre().getNom().equals(livreName))) {
                    // Livre en rouge
                    System.out.println("\t" + ANSI_RED + livreName);
                } else {
                    // Livre en vert
                    System.out.println("\t" +  ANSI_GREEN + livreName);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
