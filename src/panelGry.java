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
    Pilka pilka_;
   Boolean init = false;
    public panelGry(Color color){
        this.setOpaque(true);
        this.setBackground(color);
        paletka_ = new paletka(this.getWidth()/2,this.getHeight()/10,getWidth()/5,getHeight()/25);
        pilka_ = new Pilka(this.getWidth()/2,this.getHeight()/2,this.getHeight()/30);
    }


    @Override
    public void paint(Graphics g) {
        g.drawRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
        g.fillRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());

        g.setColor(Color.red);
        g.drawOval(pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getPromien(),pilka_.getPromien());
        g.fillOval(pilka_.getX_pos(), pilka_.getY_pos(), pilka_.getPromien(), pilka_.getPromien());
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
            paletka_.ustaw_pozycje(this.getWidth()/2,this.getHeight() - this.getHeight()/10,getWidth()/5,getHeight()/25);
            pilka_.ustaw_pozycje(this.getWidth()/2,this.getHeight()/2,this.getHeight()/30);
            init=true;
        }
        paletka_.porusz(getWidth());
        pilka_.porusz(getWidth());
        sprawdzKolizje();



        System.out.println("Szer panelu gry to: "+getWidth() +" a wys panelu gry to: "+ getHeight());
        System.out.println("Paletka: X: "+ paletka_.getX()+" Y: "+paletka_.getY()+" szer i wys: "+paletka_.getSzer_() +" "+ paletka_.getWys_());
        System.out.println("Pilka: X: "+ pilka_.getX_pos()+" Y: "+pilka_.getY_pos()+" promien: "+pilka_.getPromien());
        repaint();
    }
    public void sprawdzKolizje(){
        Rectangle r1 = paletka_.getBounds();
        Rectangle r2 = pilka_.getBounds();
        if(r1.intersects(r2)){
            pilka_.odwroc_Y();
        }
        else if((pilka_.getX_pos()>=getWidth()-pilka_.getPromien())||(pilka_.getX_pos()<=1))
        {
            pilka_.odwroc_X();
        }
        else if((pilka_.getY_pos()>=getHeight()-pilka_.getPromien())||(pilka_.getY_pos()<=0)){
            pilka_.odwroc_Y();
        }
    }
    public void skaluj()
    {
        paletka_.skaluj(getWidth(),getHeight());
        pilka_.skaluj(getWidth(),getHeight());
    }
}
