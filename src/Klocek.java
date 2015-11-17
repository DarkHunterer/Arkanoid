import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by Daniel on 03.11.2015.
 * Klasa odpowiadaj¹ca za klocek (przeszkoda do zbicia pi³k¹)
 */
public class Klocek {
    private int pos_X;
    private int pos_Y;
    private int szer;
    private int wys;
    private int wytrzymalosc;
    private Color kolor=Color.RED;
    Klocek(int x, int y, int szer_ekr, int wys_ekr,int zycie){
        pos_X = x;
        pos_Y = y;
        szer = szer_ekr/15;
        wys = wys_ekr/30;
        wytrzymalosc=zycie;
        switch (zycie){
            case 0: try{
                this.finalize();
            }catch (Throwable e){
                System.out.println(e.toString());
            }
                break;
            case 1: kolor=Color.RED;
                break;
            case 2: kolor=Color.black;
                break;
            case 3:kolor=Color.blue;
                break;
            case 4:kolor=Color.orange;
                break;
            case 5:kolor=Color.green;
                break;
            default:kolor=Color.magenta;
        }
    }

    /**
     * Klasa zwracajaca kolor
     * @return
     */
    public Color getKolor() {
        return kolor;
    }

    /**
     * Zwraca pozycje X
     * @return
     */
    public int getPos_X() {
        return pos_X;
    }

    /**
     * Zwraca pozycje Y
     * @return
     */
    public int getPos_Y() {
        return pos_Y;
    }

    /**
     * Zwraca szerokoœæ klocka
     * @return
     */
    public int getSzer() {
        return szer;
    }

    /**
     * Zwraca wysokoœæ klocka
     * @return
     */
    public int getWys() {
        return wys;
    }

    /**
     * Metoda która skaluje klocek wzglêdem rozmiarów okna
     * @param szerokosc Nowa szerokosc okna
     * @param wysokosc  Nowa wysokosc okna
     * @param szer_stara Stara szerokosc okna
     * @param wys_stara Stara wysokosc okna
     */
    public void skaluj(int szerokosc, int wysokosc,int szer_stara, int wys_stara)
    {
        if(szer!=0) {
            double a = (double)pos_X/szer_stara;
            double b = (double)pos_Y/wys_stara;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
             System.out.println("PosX="+pos_X+" szerokosc:"+szerokosc+" szer_stara="+szer_stara+" b="+df.format(a));
             System.out.println("PosY="+pos_Y+" wysokosc:"+wysokosc+" wys_stara="+wys_stara+" b="+df.format(b));
            pos_X=(int)(szerokosc*a);
            pos_Y =(int)(wysokosc*b);
            //   System.out.println("Wynik dzialania to:"+(int)(szerokosc*a)+" a pos_X to:"+x_pos);
        }
        System.out.println("y_pos klocka to"+pos_Y+"wysokosc ekranu to "+wysokosc);

        szer = szerokosc/15;
        wys = wysokosc/30;
    }

    /**
     * Obsluga kolizji z pilka
     */
    public void kolizja(){
        System.out.println("Nastapila kolizja z klockiem. Wytrzymalosc="+wytrzymalosc);
        if(wytrzymalosc>0) {
            wytrzymalosc--;
        }
        if(wytrzymalosc==0){
            try {
                finalize();
            }catch (Throwable e){
                System.out.println(e.toString());
            }
        }
        switch (wytrzymalosc){
            case 1: kolor=Color.RED;
                break;
            case 2: kolor=Color.black;
                break;
            case 3:kolor=Color.blue;
                break;
            case 4:kolor=Color.orange;
                break;
            case 5:kolor=Color.green;
                break;
            default:kolor=Color.magenta;
        }
    }

    /**
     * Zwraca wytrzyma³oœæ klocka
     * @return
     */
    public int getWytrzymalosc() {
        return wytrzymalosc;
    }

    /**
     * Zwraca obiekt typu Rectangle który jest u¿ywany do wykrywania kolizji
     * @return
     */
    public Rectangle getBounds(){
        return new Rectangle(pos_X,pos_Y,szer,wys);
    }
}
