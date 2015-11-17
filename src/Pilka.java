import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by Daniel on 02.11.2015.
 * Klasa odpowiadaj¹ca za pi³ke
 */
public class Pilka {
    private int x_pos;
    private int y_pos;
    private int srednica;
    private int predkosc;
    private int dx=1;
    private int dy=1;

    /**
     * Konsturktor pi³ki
     * @param x_start Pozycja startowa X'owa
     * @param y_start Pozycja startowa Y'owa
     * @param szerokosc Srednica pi³ki
     */
    Pilka(int x_start,int y_start,int szerokosc){
        System.out.println("Dodano pilke");
        x_pos = x_start;
        y_pos = y_start;
        srednica =szerokosc;
        predkosc=1;
    }

    /**
     * Zwraca predkosc pi³ki
     * @return
     */
    public int getPredkosc() {
        return predkosc;
    }

    /**
     * Ustawia predkosc pilki
     * @param predkosc
     */
    public void setPredkosc(int predkosc) {
        this.predkosc = predkosc;
    }

    /**
     * Ustawia pozycje pilki
     * @param x_start X
     * @param y_start Y
     * @param sredn Srednica
     */
    public void ustaw_pozycje(int x_start,int y_start,int sredn){
        x_pos = x_start;
        y_pos = y_start;
        srednica =sredn;
    }

    /**
     * Metoda odpowiadajca za poruszenie pilki
     * @param maxX Maksymalny X jaki pi³ka mo¿e przyj¹æ
     * @param maxY Maksymalny Y jaki pi³ka mo¿e przyj¹æ
     */
    public void porusz(int maxX,int maxY){
        x_pos += predkosc*dx;
        y_pos += predkosc*dy;
        if(x_pos<1)
            x_pos=1;
        else if(x_pos+ srednica >maxX)
            x_pos=maxX- srednica;
    }

    /**
     * Ustawia pozycje Y
     * @param y_pos
     */
    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    /**
     * Ustawia pozycje X
     * @param x_pos
     */
    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    /**
     * Zwraca pozycje X
     * @return
     */
    public int getX_pos() {
        return x_pos;
    }

    /**
     * Zwraca pozycje Y
     * @return
     */
    public int getY_pos() {
        return y_pos;
    }

    /**
     * Zwraca œrednice
     * @return
     */
    public int getSrednica() {
        return srednica;
    }

    /**
     * Zwraca obiekt typu Rectangle który jest u¿ywany do wykrywania kolizji
     * @return
     */
    public Rectangle getBounds(){
        return new Rectangle(x_pos,y_pos, srednica, srednica);
    }

    /**
     * Odwraca Y
     */
    public void odwroc_Y()
    {
        dy = -dy;
        System.out.println("Odwroc Y");
    }

    /**
     * Odwraca X
     */
    public void odwroc_X(){
        dx=-dx;
        System.out.println("Odwroc X");
    }

    /**
     * Skaluje pi³ke wzglêdem okna
     * @param szerokosc Nowa szerokosc okna
     * @param wysokosc  Nowa wysokosc okna
     * @param szer_stara Stara szerokosc okna
     * @param wys_stara Stara wysokosc okna
     * @param szer_paletki Szerokosc paletki
     */
    public void skaluj(int szerokosc, int wysokosc,int szer_stara, int wys_stara,int szer_paletki){
        if(srednica !=0) {
            double a = (double)x_pos/szer_stara;
            double b = (double)y_pos/wys_stara;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
            System.out.println("PosX="+x_pos+" PosY="+y_pos+" szerokosc:"+szerokosc+" szer_stara="+szer_stara+" wys stara="+ wys_stara+" a="+df.format(a)+" b="+df.format(b));
            x_pos=(int)(szerokosc*a);
            y_pos=(int)(wysokosc*b);
            System.out.println("Wynik dzialania to:"+(int)(szerokosc*a)+" a pos_X to:"+x_pos);
        }
        srednica = szerokosc/30;
    }
}




