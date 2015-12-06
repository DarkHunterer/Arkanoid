import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

/**
 * Created by Daniel on 02.11.2015.
 * Klasa odpowiadaj¹ca za paletkê
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

    /**
     * @param x_start Pozycja pocz¹tkowa X
     * @param y_start Pozycja pocz¹tkowa Y
     * @param szerokosc Szerokoœæ paletki
     * @param wysokosc Wysokoœæ paletki
     */
    paletka(int x_start,int y_start,int szerokosc,int wysokosc){
        System.out.println("Dodano paletke");
        x_pos = x_start;
        y_pos = y_start;
        szer_=szerokosc;
        wys_ = wysokosc;
        predkosc =200;
        kolor=Color.blue;
    }

    /**
     * Zwraca kolor paletki
     * @return
     */
    public Color getKolor() {
        return kolor;
    }

    /**
     * Ustawia kolor paletki
     * @param kolor
     */
    public void setKolor(Color kolor) {
        this.kolor = kolor;
    }

    /**
     * Ustawia szerokosc paletki
     * @param szer_
     */
    public void setSzer_(int szer_) {
        this.szer_ = szer_;
    }

    /**
     * Ustawia pozycje paletki
     * @param x_start
     * @param y_start
     * @param szerokosc
     * @param wysokosc
     */
    public void ustaw_pozycje(int x_start,int y_start,int szerokosc,int wysokosc){
        x_pos = x_start;
        y_pos = y_start;
        szer_= szerokosc;
        wys_ = wysokosc;
    }

    /**
     * Metoda odpowiadaj¹ca za poruszanie siê paletki
     * @param maxX
     */
    public void porusz(int maxX){
        x_pos += dx/30;
        if(x_pos<1)
            x_pos=1;
        if(x_pos+szer_>maxX)
            x_pos=maxX-szer_;
    }

    /**
     * Metoda zwracaj¹ca szerokoœæ paletki
     * @return
     */
    public int getSzer_() {
        return szer_;
    }

    /**
     * Metoda zwracaj¹ca wysokoœæ paletki
     * @return
     */
    public int getWys_() {
        return wys_;
    }

    /**
     * Metoda zwracaj¹ca pozycjê X
     * @return
     */
    public int getX()
    {
        return x_pos;
    }

    /**
     * Metoda zwracaj¹ca pozycjê Y
     * @return
     */
    public int getY()
    {
        return y_pos;
    }

    /**
     * Metoda odpowiadaj¹ca za skalowanie paletki wzglêdem rozmiaru okna
     * @param szerokosc Nowa szerokosc okna
     * @param wysokosc  Nowa wysokosc okna
     * @param szer_stara Stara szerokosc okna
     * @param wys_stara Stara wysokosc okna
     */
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
    /**
     *  Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
    /**
     *  Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     * @param e Obiekt typu KeyEvent
     */
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
    /**
     *  Metoda odpowiadajaca za przechwycenie puszczenia klawisza
     * @param e Obiekt typu KeyEvent
     */
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
    /**
     * Zwraca obiekt typu Rectangle który jest u¿ywany do wykrywania kolizji
     * @return
     */
    public Rectangle getBounds(){
        return new Rectangle(x_pos,y_pos,szer_,wys_);
    }
}
