import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by Daniel on 02.11.2015.
 */
public class Pilka {
    private int x_pos;
    private int y_pos;
    private int promien;
    private int predkosc;
    private int dx=1;
    private int dy=1;

    Pilka(int x_start,int y_start,int szerokosc){
        System.out.println("Dodano pilke");
        x_pos = x_start;
        y_pos = y_start;
        promien=szerokosc;
        predkosc=1;
    }

    public int getPredkosc() {
        return predkosc;
    }

    public void setPredkosc(int predkosc) {
        this.predkosc = predkosc;
    }

    public void ustaw_pozycje(int x_start,int y_start,int prom){
        x_pos = x_start;
        y_pos = y_start;
        promien=prom;
    }
    public void porusz(int maxX,int maxY){
        x_pos += predkosc*dx;
        y_pos += predkosc*dy;
        if(x_pos<1)
            x_pos=1;
        else if(x_pos+promien>maxX)
            x_pos=maxX-promien;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public int getPromien() {
        return promien;
    }
    public Rectangle getBounds(){
        return new Rectangle(x_pos,y_pos,promien,promien);
    }
    public void odwroc_Y()
    {
        dy = -dy;
        System.out.println("Odwroc Y");
    }
    public void odwroc_X(){
        dx=-dx;
        System.out.println("Odwroc X");
    }
    public void skaluj(int szerokosc, int wysokosc,int szer_stara, int wys_stara,int szer_paletki){
        if(promien!=0) {
            double a = (double)x_pos/szer_stara;
            double b = (double)y_pos/wys_stara;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
            System.out.println("PosX="+x_pos+" PosY="+y_pos+" szerokosc:"+szerokosc+" szer_stara="+szer_stara+" wys stara="+ wys_stara+" a="+df.format(a)+" b="+df.format(b));
            x_pos=(int)(szerokosc*a);
            y_pos=(int)(wysokosc*b);
            System.out.println("Wynik dzialania to:"+(int)(szerokosc*a)+" a pos_X to:"+x_pos);
        }
        promien = szerokosc/40;
    }
}




