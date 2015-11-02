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

    private int dx;
    private int dy;
    paletka(int x_start,int y_start,int szerokosc){
        System.out.println("Dodano paletke");
        x_pos = x_start;
        y_pos = y_start;
        szer_=szerokosc;
        wys_ = 20;
    }
    public void ustaw_pozycje(int x_start,int y_start,int szerokosc){
        x_pos = x_start;
        y_pos = y_start;
        szer_=szerokosc;
    }

    public void porusz(){
        x_pos += dx;
        if(x_pos<1)
            x_pos=1;
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
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
    if (key==KeyEvent.VK_LEFT) {
            System.out.println(" Strzalka w lewo wcisnieta");
            dx=-1;
    }
    else if(key==KeyEvent.VK_RIGHT) {
        System.out.println(" Strzalka w prawo wcisnieta");
        dx=1;
    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key==KeyEvent.VK_LEFT||key==KeyEvent.VK_RIGHT) {
            if (x_pos > 0) {
                System.out.println(" Strzalka w lewo lub prawo puszczona");
                dx=0;
            }
        }
    }
}
