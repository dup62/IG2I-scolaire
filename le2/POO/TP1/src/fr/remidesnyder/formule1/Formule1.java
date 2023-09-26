package fr.remidesnyder.formule1;

/**
 * Created by Desnyder Rémi
 * Date: 26/09/2023
 */

public class Formule1 extends Voiture {

    private String sponsor;

    public Formule1() {
        super();
        this.setSponsor("???");
    }

    public Formule1(String sponsor) {
        this();
        this.setSponsor(sponsor);
    }

    public Formule1(String marque, String sponsor) {
        super(marque);
        this.setSponsor(sponsor);
    }

    public Formule1(String marque, int puissance, String carburant, String sponsor) {
        super(marque, puissance, carburant);
        this.setSponsor(sponsor);
    }

    public String getSponsor() {
        return sponsor;
    }

    private void setSponsor(String sponsor) {
        if (sponsor != null) {
            this.sponsor = sponsor;
        } else {
            this.sponsor = "???";
        }
    }

    @Override
    public String toString() {
        return "Formule1{" +
                super.toString() + '\'' +
                "sponsor='" + sponsor + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Formule1 f1 = new Formule1("NordVPN");
        System.out.println(f1.toString());
        f1.setSponsor("RedBull");
        System.out.println("Sponsor : " + f1.getSponsor());
        System.out.println(f1.toString());

        f1.setSponsor(null);
        System.out.println("Sponsor : " + f1.getSponsor());
        System.out.println(f1.toString());
    }
}
