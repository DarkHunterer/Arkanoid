import java.awt.*;

/**
 * Created by Daniel on 03.11.2015.
 */
public class Klocek {
    private int pos_X;
    private int pos_Y;
    private int szer;
    private int wys;
    private int wytrzymalosc =5;
    private Color kolor=Color.RED;
    Klocek(int x, int y, int szer_ekr, int wys_ekr){
        pos_X = x;
        pos_Y = y;
        szer = szer_ekr/20;
        wys = wys_ekr/30;
    }

    public Color getKolor() {
        return kolor;
    }

    public int getPos_X() {
        return pos_X;
    }

    public int getPos_Y() {
        return pos_Y;
    }

    public int getSzer() {
        return szer;
    }

    public int getWys() {
        return wys;
    }

    public int getWytrzymalosc() {
        return wytrzymalosc;
    }
}
