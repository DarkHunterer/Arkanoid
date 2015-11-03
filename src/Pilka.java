import java.awt.*;

/**
 * Created by Daniel on 02.11.2015.
 */
public class Pilka {
    private int x_pos;
    private int y_pos;
    private int promien;

    private int dx=1;
    private int dy=1;
    Pilka(int x_start,int y_start,int szerokosc){
        System.out.println("Dodano pilke");
        x_pos = x_start;
        y_pos = y_start;
        promien=szerokosc;
    }
    public void ustaw_pozycje(int x_start,int y_start,int prom){
        x_pos = x_start;
        y_pos = y_start;
        promien=prom;
        promien= prom;
    }
    public void porusz(int maxX){
        x_pos += 2*dx;
        y_pos += 2*dy;
        if(x_pos<1)
            x_pos=1;
        if(x_pos+promien>maxX)
            x_pos=maxX-promien;
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
    }
    public void odwroc_X(){
        dx=-dx;
    }
    public void skaluj(int szerokosc, int wysokosc){
        promien = szerokosc/30;
    }
}
