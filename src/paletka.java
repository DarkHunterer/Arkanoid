import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

/**
 * Created by Daniel on 02.11.2015.
 */
public class paletka implements KeyListener {

    private int x_pos;
    private int y_pos;
    private int szer_;
    private int wys_;
    private int predkosc;
    private Color kolor;

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

    public Color getKolor() {
        return kolor;
    }

    public void setKolor(Color kolor) {
        this.kolor = kolor;
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

    public void skaluj(int szerokosc,int wysokosc,int szer_stara, int wys_stara){
        if(szer_!=0) {
            double a = (double)x_pos/szer_stara;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
           // System.out.println("PosX="+x_pos+" szerokosc:"+szerokosc+" szer_stara="+szer_stara+" b="+df.format(a));
            x_pos=(int)(szerokosc*a);
         //   System.out.println("Wynik dzialania to:"+(int)(szerokosc*a)+" a pos_X to:"+x_pos);
        }
        System.out.println("y_pos paletki to"+y_pos+"wysokosc ekranu to "+wysokosc);
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
