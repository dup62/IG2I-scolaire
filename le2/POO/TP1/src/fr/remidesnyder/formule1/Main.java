package fr.remidesnyder.formule1;

public class Main {
    public static void main(String[] args) {
        Moteur v1 = new Moteur();
        Moteur v2 = new Moteur("DIESEL", 740);
        Moteur v3 = new Moteur("Diesel", 100);
        Moteur v4 = new Moteur("Electrique", 100);

        Moteur vcopy = new Moteur(v2);

        System.out.println(v1.toString());
        System.out.println(v2.toString());
        System.out.println(v3.toString());
        System.out.println(v4.toString());

        System.out.println(vcopy.toString());

        v3.setPuissance(1230);
        System.out.println(v3.toString());

        Voiture voit1 = new Voiture();
        Voiture voit2 = new Voiture("Ferrari");
        Voiture voit3 = new Voiture("Peugeot", 760, "DIESEL");
        Voiture voit4 = new Voiture("Tesla", v4);

        System.out.println("\n Voiture:");
        System.out.println(voit1.toString());
        System.out.println(voit2.toString());
        System.out.println(voit3.toString());
        System.out.println(voit4.toString());

        Personne p1 = new Personne();
        Personne p2 = new Personne("Nom1", "Prenom1");
        Personne p3 = new Personne("Nom2", "Prenom2", "13 rue Jean Souvraz");

        System.out.println("\n Personnes:");
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());

        Moteur testMoteur = new Moteur("DIESEL", 1432);
        Voiture testVoiture = new Voiture("M", testMoteur);
        Personne testPersonne = new Personne("Nom", "Prenom", "Adresse");
        Personne testPersonne2 = new Personne("Nom2", "Prenom2", "Adresse2");

        System.out.println("\n Init");
        System.out.println(testVoiture.toString());
        System.out.println(testPersonne.toString());

        System.out.println("\n Affecter");

        testPersonne.affecterVoiture(testVoiture);
        System.out.println(testVoiture.toString());
        System.out.println(testPersonne.toString());

        System.out.println("\n Restituer");

        testPersonne.restituerVoiture();
        System.out.println(testVoiture.toString());
        System.out.println(testPersonne.toString());

        System.out.println("\n Affecter 2");

        testPersonne.affecterVoiture(testVoiture);
        System.out.println(testVoiture.toString());
        System.out.println(testPersonne.toString());

        System.out.println("\n Affecter 2 - autres personne en même temps");

        System.out.println(testPersonne2.toString());
        testPersonne.affecterVoiture(testVoiture);
        System.out.println(testVoiture.toString());
        System.out.println(testPersonne.toString());
        System.out.println(testPersonne2.toString());


    }
}