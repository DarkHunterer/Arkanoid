import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa perk
 */
public class perk {
    private int pos_x;
    private int pos_y;
    private int height;
    private int width;
    private int kod;
    private int dy;
     private paletka paletka_;
    private pasekWyniku pasekWyniku_;
    private Image imgPerk;
    private Random generator = new Random();

    public perk(Klocek kl, paletka pale, pasekWyniku pasek){
        pos_x=kl.getPos_X();
        pos_y=kl.getPos_Y();
        height=kl.getWys();
        width=kl.getSzer();
        dy=3;
        paletka_= pale;
        pasekWyniku_= pasek;
        kod =  generator.nextInt(12);
        switch (kod){
            case 0:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_zwezenie.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 1:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_poszerzenie.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 2:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_punkty_20.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 3:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_punkty_40.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 4:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_punkty_80.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 5:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_punkty_160.png"));
                }catch (Exception e){ System.out.println(e.toString());}

            }
            break;
            case 6:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_punkty_-20.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 7:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_punkty_-40.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 8:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_punkty_-80.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 9:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_punkty_-160.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 10:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_czas.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 11:{
                try {
                    imgPerk = ImageIO.read(new File("grafika/perk_zycie.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;

            default:
                break;
        }
    }

    public void akcja(){
        switch (kod){
            case 0:{
                paletka_.setSzer_(paletka_.getSzer_()*3/4);
            }
            break;
            case 1:{
                paletka_.setSzer_(paletka_.getSzer_()*4/3);
            }
            break;
            case 2:{
                pasekWyniku_.dodajPunkty(20);
            }
            break;
            case 3:{
                pasekWyniku_.dodajPunkty(40);
            }
            break;
            case 4:{
                pasekWyniku_.dodajPunkty(80);
            }
            break;
            case 5:{
                pasekWyniku_.dodajPunkty(160);

            }
            break;
            case 6:{
                pasekWyniku_.dodajPunkty(-20);
            }
            break;
            case 7:{
                pasekWyniku_.dodajPunkty(-40);
            }
            break;
            case 8:{
                pasekWyniku_.dodajPunkty(-80);
            }
            break;
            case 9:{
                pasekWyniku_.dodajPunkty(-160);
            }
            break;
            case 10:{
                pasekWyniku_.dodajCzas();
            }
            break;
            case 11:{
                pasekWyniku_.dodajZycie();
            }
            break;

            default:
                break;
        }

    }

    public int getPos_x() {
        return pos_x;
    }


    public int getPos_y() {
        return pos_y;
    }


    public int getHeight() {
        return height;
    }


    public int getWidth() {
        return width;
    }


    public void porusz(){

            pos_y+=dy;
    }
    public Rectangle getBounds(){
        return new Rectangle(pos_x,pos_y,width,height);
    }
    public void skaluj(int wys_,int szer_, int szer_stara, int wys_stara, int wysokosc, int szerokosc){
        height=wys_;
        width=szer_;
        double a = (double)pos_x/szer_stara;
        double b = (double)pos_y/wys_stara;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);
        pos_x=(int)(szerokosc*a);
        pos_y=(int)(wysokosc*b);
    }
    public Image getImage(){
        return imgPerk;
    }
}
