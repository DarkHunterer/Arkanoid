import java.awt.*;
import java.text.DecimalFormat;

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
        szer = szer_ekr/15;
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

    public void skaluj(int szerokosc, int wysokosc,int szer_stara, int wys_stara)
    {
        if(szer!=0) {
            double a = (double)pos_X/szer_stara;
            double b = (double)pos_Y/wys_stara;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
            // System.out.println("PosX="+x_pos+" szerokosc:"+szerokosc+" szer_stara="+szer_stara+" b="+df.format(a));
             System.out.println("PosY="+pos_Y+" szerokosc:"+szerokosc+" szer_stara="+szer_stara+" b="+df.format(b));
            pos_X=(int)(szerokosc*a);
            pos_Y =(int)(wysokosc*b);
            //   System.out.println("Wynik dzialania to:"+(int)(szerokosc*a)+" a pos_X to:"+x_pos);
        }
        System.out.println("y_pos klocka to"+pos_Y+"wysokosc ekranu to "+wysokosc);

        szer = szerokosc/15;
        wys = wysokosc/30;
    }
    public int getWytrzymalosc() {
        return wytrzymalosc;
    }
    public Rectangle getBounds(){
        return new Rectangle(pos_X,pos_Y,szer,wys);
    }
}
