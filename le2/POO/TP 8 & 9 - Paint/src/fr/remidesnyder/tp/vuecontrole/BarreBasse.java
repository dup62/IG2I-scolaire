package fr.remidesnyder.tp.vuecontrole;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Created by Desnyder Rémi
 * Date: 07/12/2023
 */

public class BarreBasse extends JPanel {

    private JLabel message;
    private JLabel x;
    private JLabel y;

    public BarreBasse() {
       message = new JLabel("Message");
       x = new JLabel("x -");
       y = new JLabel("y -");
       message.setForeground(Color.RED);

       this.add(message);
       this.add(x);
       this.add(y);

       this.setBackground(Color.LIGHT_GRAY);
    }

    public void deplacementSouris (MouseEvent evt) {
        if( evt != null) {
            this.setX(" x - " + Integer.toString( evt.getX() ) );
            this.setY(" y - " + Integer.toString( evt.getY() ) );
        }
    }

    public void setMessage(String message) {
        if( message != null) this.message.setText(message);
    }

    public void setX(String x) {
        if( x != null) this.x.setText(x);
    }

    public void setY(String y) {
        if( y != null) this.y.setText(y);
    }
}
