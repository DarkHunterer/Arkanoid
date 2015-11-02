import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Daniel on 02.11.2015.
 */
public class panelGry extends JPanel implements ActionListener, KeyListener {

    paletka paletka_;
   Boolean init = false;
    public panelGry(Color color){
        this.setOpaque(true);
        this.setBackground(color);
        paletka_ = new paletka(this.getWidth()/2,this.getHeight() -50,getWidth()/5);
        dodajKlocek();
    }

    void dodajKlocek(){

    }

    @Override
    public void paint(Graphics g) {
        g.drawRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
        g.fillRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        paletka_.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        paletka_.keyPressed(e);
    }
    public void actionPerformed(ActionEvent e) {
        if(init==false)
        {
            paletka_.ustaw_pozycje(this.getWidth()/2,this.getHeight() -50,getWidth()/5);
            init=true;
        }
        paletka_.porusz(getWidth());
        System.out.println("Szer panelu gry to: "+getWidth() +" a wys panelu gry to: "+ getHeight());
        System.out.println("Paletka: X: "+ paletka_.getX()+" Y: "+paletka_.getY()+" szer i wys: "+paletka_.getSzer_() +" "+ paletka_.getWys_());
        repaint();
    }
}
