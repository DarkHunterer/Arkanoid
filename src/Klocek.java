import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

/**
 *
 * Klasa odpowiadaj�ca za klocek (przeszkoda do zbicia pi�k�)
 */
public class Klocek {
    private int pos_X;
    private int pos_Y;
    private int szer;
    private int wys;
    private int wytrzymalosc;
    //private Color kolor=Color.RED;
    Image imgKlocek;
    Klocek(int x, int y, int szerokosc, int wysokosc,int zycie){
        pos_X = x;
        pos_Y = y;
        szer = szerokosc;
        wys = wysokosc;
        wytrzymalosc=zycie;
        switch (zycie){
            case 0: try{
                this.finalize();
            }catch (Throwable e){
                System.out.println(e.toString());
            }
                break;
            case 1: //kolor=Color.RED;
            {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel5.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
                break;
            case 2:            {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel4.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            } //kolor=Color.black;
                break;
            case 3:            {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel3.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }//kolor=Color.blue;
                break;
            case 4:            {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel2.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }//kolor=Color.orange;
                break;
            case 5:            {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel1.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }//kolor=Color.green;
                break;
            default: break;//kolor=Color.magenta;
        }

    }

//    /**
 //    * Klasa zwracajaca kolor
 //    * @return
  //   */
  //  public Color getKolor() {
  //      return kolor;
 //   }

    /**
     * Ustawia pozycje X
     * @param pos_X
     */
    public void setPos_X(int pos_X) {
        this.pos_X = pos_X;
    }

    /**
     * Ustawia pozycje Y
     * @param pos_Y
     */
    public void setPos_Y(int pos_Y) {
        this.pos_Y = pos_Y;
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
     * Zwraca szeroko�� klocka
     * @return
     */
    public int getSzer() {
        return szer;
    }

    /**
     * Zwraca wysoko�� klocka
     * @return
     */
    public int getWys() {
        return wys;
    }

    /**
     * Metoda kt�ra skaluje klocek wzgl�dem rozmiar�w okna
     */
    /*public void skaluj(int szerokosc_ekranu, int wysokosc_ekranu,int szer_stara, int wys_stara,int szer_klocka,int wys_klocka)
    {
        if(szer!=0) {
            double a = (double)pos_X/szer_stara;
            double b = (double)pos_Y/wys_stara;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
             System.out.println("PosX="+pos_X+" szerokosc:"+szerokosc_ekranu+" szer_stara="+szer_stara+" b="+df.format(a));
             System.out.println("PosY="+pos_Y+" wysokosc:"+wysokosc_ekranu+" wys_stara="+wys_stara+" b="+df.format(b));
            pos_X=(int)(szerokosc_ekranu*a);
            pos_Y =(int)(wysokosc_ekranu*b);
            //   System.out.println("Wynik dzialania to:"+(int)(szerokosc*a)+" a pos_X to:"+x_pos);
        }
        System.out.println("y_pos klocka to"+pos_Y+"wysokosc ekranu to "+wysokosc_ekranu);

        szer = szer_klocka;
        wys = wys_klocka;
    }*/
    public void skaluj(int pozycja_x, int pozycja_y,int szer_klocka,int wys_klocka)
    {
        pos_X = pozycja_x;
        pos_Y = pozycja_y;
        szer = szer_klocka;
        wys = wys_klocka;
    }

    /**
     * Obsluga kolizji z pilka
     */
    public void kolizja(){
        System.out.println("Nastapila kolizja z klockiem. Wytrzymalosc="+wytrzymalosc);
        if(wytrzymalosc>0) {
            wytrzymalosc--;
        }
       /*if(wytrzymalosc==0){
            try {
                finalize();
            }catch (Throwable e){
                System.out.println(e.toString());
            }
        }*/
        switch (wytrzymalosc){
            case 1: //kolor=Color.RED;
            {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel5.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
                break;
            case 2:
            {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel4.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }//kolor=Color.black;
                break;
            case 3:          {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel3.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }//kolor=Color.blue;
                break;
            case 4:          {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel2.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
                //kolor=Color.orange;
                break;
            case 5:          {
                try {
                    imgKlocek = ImageIO.read(new File("Kafel1.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
                //kolor=Color.green;
                break;
            default:break;//kolor=Color.magenta;
        }
    }

    /**
     * Zwraca wytrzyma�o�� klocka
     * @return
     */
    public int getWytrzymalosc() {
        return wytrzymalosc;
    }

    /**
     * Zwraca obiekt typu Rectangle kt�ry jest u�ywany do wykrywania kolizji
     * @return
     */
    public Rectangle getBounds(){
        return new Rectangle(pos_X,pos_Y,szer,wys);
    }
}
