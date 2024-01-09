package fr.remidesnyder.tp.vuecontrole;

import fr.remidesnyder.tp.modele.*;
import fr.remidesnyder.tp.modele.formes.*;
import fr.remidesnyder.tp.modele.formes.Point;
import fr.remidesnyder.tp.modele.formes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

/**
 * Created by Desnyder Rémi
 * Date: 19/12/2023
 */

public class ZoneGraphique extends JPanel implements MouseMotionListener, MouseListener {

    private BarreBasse barreBasse;
    private BarreHaute barreHaute;

    /**
     * représente le point lorsque l’utilisateur appuie sur la souris (mis à jour à chaque nouvel appui).
     */
    private Point pInit;

    /**
     * représente le point lorsque l’utilisateur relâche la souris (mis à jour à chaque relâchement de la souris).
     */
    private Point pFin;
    /**
     * indique si nous sommes en train de dessiner (glissé de la souris avec le bouton appuyé = true)
     * ou si le dessin est terminé (bouton relâché = false).
     */
    private boolean drawing;

    private LinkedList<Forme> listeDessins;

    private boolean gommeEnCours;
    private int gommeWidth;

    public ZoneGraphique(BarreBasse barreBasse, BarreHaute barreHaute) {
        super();

        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        if( barreBasse != null)
            this.barreBasse = barreBasse;
        else
            this.barreBasse = new BarreBasse();

        if( barreHaute != null)
            this.barreHaute = barreHaute;

        this.drawing = false;
        this.listeDessins = new LinkedList<>();

        this.setBackground(Color.WHITE);

        this.gommeEnCours = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.barreBasse.deplacementSouris(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Evenement souris : glisser/déposer
        this.barreBasse.deplacementSouris(e);
        this.pFin = new Point( e.getPoint().x, e.getPoint().y );
        this.dessin();
        if(!this.drawing) this.drawing = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("L'utilisateur a cliqué sur la zone graphique !");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Evenement souris : presser
        //System.out.println("Point : " + e.getPoint().toString());
        this.pInit = new Point( e.getPoint().x, e.getPoint().y);
        //System.out.println("Relâcher pour voir la forme.");
        barreBasse.setMessage("Relâcher pour voir la forme.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Evenement souris : relâcher
        this.drawing = false;
        this.pFin = new Point( e.getPoint().x, e.getPoint().y );
        //System.out.println("Point : " + e.getPoint().toString()); // OK
        //this.dessin();
        ///System.out.println("Cliquez pour initier une forme.");
        barreBasse.setMessage("Cliquez pour initier une forme.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Le curseur de la souris est entré dans la zone graphique.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Le curseur de la souris est sorti de la zone graphique.");
    }
    private void dessin() {

        if(this.gommeEnCours) {
            this.gommer();
            return;
        }

        EnumForme forme = this.barreHaute.getFormeSelectionnee();
        EnumCouleur couleur = this.barreHaute.getCouleurSelectionnee();
        Forme formeDessin = null;

        switch(forme) {
            case DROITE :
                formeDessin = new Droite(couleur.getCouleur(), this.pInit, this.pFin);
                break;

            case RECTANGLE :
                if (this.barreHaute.isPlein())
                    formeDessin = new Rectangle(couleur.getCouleur(), this.pInit, this.pFin, true);
                else
                    formeDessin = new Rectangle(couleur.getCouleur(), this.pInit, this.pFin);
                break;

            case OVALE :
                if (this.barreHaute.isPlein())
                    formeDessin = new Ovale(couleur.getCouleur(), this.pInit, this.pFin, true);
                else
                    formeDessin = new Ovale(couleur.getCouleur(), this.pInit, this.pFin);
                break;

            case CERCLE:
                if (this.barreHaute.isPlein())
                    formeDessin = new Cercle(couleur.getCouleur(), this.pInit, this.pFin, true);
                else
                    formeDessin = new Cercle(couleur.getCouleur(), this.pInit, this.pFin);
                break;

            case TRIANGLE_RECTANGLE :
                if (this.barreHaute.isPlein())
                    formeDessin = new TriangleRectangle(couleur.getCouleur(), this.pInit, this.pFin, true);
                else
                    formeDessin = new TriangleRectangle(couleur.getCouleur(), this.pInit, this.pFin);
                break;

            case TRIANGLE_ISOCELE :
                if (this.barreHaute.isPlein())
                    formeDessin = new TriangleIsocele(couleur.getCouleur(), this.pInit, this.pFin, true);
                else
                    formeDessin = new TriangleIsocele(couleur.getCouleur(), this.pInit, this.pFin);
                break;

            case TRIANGLE_EQUILATERAL :
                if (this.barreHaute.isPlein())
                    formeDessin = new TriangleEquilateral(couleur.getCouleur(), this.pInit, this.pFin, true);
                else
                    formeDessin = new TriangleEquilateral(couleur.getCouleur(), this.pInit, this.pFin);
                break;

            case CARRE :
                if (this.barreHaute.isPlein())
                    formeDessin = new Carre(couleur.getCouleur(), this.pInit, this.pFin, true);
                else
                    formeDessin = new Carre(couleur.getCouleur(), this.pInit, this.pFin);
                break;

            case CAMION :
                formeDessin = new Camion(couleur.getCouleur(), this.pInit, this.pFin);
                break;

            default:
                System.out.println("Cas non prévu !");
        }

        if( formeDessin != null) {
            if(this.drawing) {
                this.listeDessins.removeLast();
            }

            this.listeDessins.add(formeDessin);
        }

        this.repaint();
    }

    private void gommer() {
        Gomme gomme = new Gomme(Color.WHITE, this.pFin, getGommeWidth());
        gomme.seDessiner(this.getGraphics());
        this.listeDessins.add(gomme);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Forme f : this.listeDessins ) {
            f.seDessiner( g );
        }
    }

    public void effacer() {
        this.listeDessins.clear();
        this.repaint();
    }

    public void retourEnArriere() {
        if(!this.listeDessins.isEmpty()) {
            this.listeDessins.removeLast();
            this.repaint();
        }
    }

    public void activerGomme() {
        this.gommeEnCours = true;
    }

    public void desactiverGomme() {
        this.gommeEnCours = false;
    }

    public int getGommeWidth() {
        return gommeWidth;
    }

    public void setGommeWidth(int gommeWidth) {
        if (gommeWidth > 0) {
            this.gommeWidth = gommeWidth;
        }
    }
}