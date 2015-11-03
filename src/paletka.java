import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Daniel on 02.11.2015.
 */
public class paletka implements KeyListener {

    private int x_pos;
    private int y_pos;
    private int szer_;
    private int wys_;
    private int predkosc;

    private int dx;
    private int dy;
    paletka(int x_start,int y_start,int szerokosc,int wysokosc){
        System.out.println("Dodano paletke");
        x_pos = x_start;
        y_pos = y_start;
        szer_=szerokosc;
        wys_ = wysokosc;
        predkosc =1;
    }
    public void ustaw_pozycje(int x_start,int y_start,int szerokosc,int wysokosc){
        x_pos = x_start;
        y_pos = y_start;
        szer_= szerokosc;
        wys_ = wysokosc;
    }

    public void porusz(int maxX){
        x_pos += 2*dx;
        if(x_pos<1)
            x_pos=1;
        if(x_pos+szer_>maxX)
            x_pos=maxX-szer_;
    }

    public int getSzer_() {
        return szer_;
    }

    public int getWys_() {
        return wys_;
    }

    public int getX()
    {
        return x_pos;
    }
    public int getY()
    {
        return y_pos;
    }

    public void skaluj(int szerokosc,int wysokosc){
        y_pos = wysokosc-wysokosc/10;
        szer_ = szerokosc/5;
        wys_ = wysokosc/25;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
    if (key==KeyEvent.VK_LEFT) {
            //System.out.println(" Strzalka w lewo wcisnieta");
            dx=-predkosc;
    }
    else if(key==KeyEvent.VK_RIGHT) {
      //  System.out.println(" Strzalka w prawo wcisnieta");
        dx=predkosc;
    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key==KeyEvent.VK_LEFT||key==KeyEvent.VK_RIGHT) {
            if (x_pos > 0) {
               // System.out.println(" Strzalka w lewo lub prawo puszczona");
                dx=0;
            }
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x_pos,y_pos,szer_,wys_);
    }
}
