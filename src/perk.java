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
   // Color col = Color.yellow;
    private int dy;
    paletka paletka_;
    pasekWyniku pasekWyniku_;
    Image imgPerk;
    Random generator = new Random();

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
                    imgPerk = ImageIO.read(new File("perk_zwezenie.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 1:{
                try {
                    imgPerk = ImageIO.read(new File("perk_poszerzenie.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 2:{
                try {
                    imgPerk = ImageIO.read(new File("perk_punkty_20.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 3:{
                try {
                    imgPerk = ImageIO.read(new File("perk_punkty_40.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 4:{
                try {
                    imgPerk = ImageIO.read(new File("perk_punkty_80.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 5:{
                try {
                    imgPerk = ImageIO.read(new File("perk_punkty_160.png"));
                }catch (Exception e){ System.out.println(e.toString());}

            }
            break;
            case 6:{
                try {
                    imgPerk = ImageIO.read(new File("perk_punkty_-20.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 7:{
                try {
                    imgPerk = ImageIO.read(new File("perk_punkty_-40.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 8:{
                try {
                    imgPerk = ImageIO.read(new File("perk_punkty_-80.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 9:{
                try {
                    imgPerk = ImageIO.read(new File("perk_punkty_-160.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 10:{
                try {
                    imgPerk = ImageIO.read(new File("perk_czas.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;
            case 11:{
                try {
                    imgPerk = ImageIO.read(new File("perk_zycie.png"));
                }catch (Exception e){ System.out.println(e.toString());}
            }
            break;

            default:
                break;
        }
        //dodac obrazki do kazdego case
    }

    public void akcja(){
        switch (kod){
            case 0:{
         //       col = Color.magenta;
                paletka_.setSzer_(paletka_.getSzer_()*3/4);
            }
            break;
            case 1:{
        //        col = Color.black;
                paletka_.setSzer_(paletka_.getSzer_()*4/3);
            }
            break;
            case 2:{
                //        col = Color.black;
                pasekWyniku_.dodajPunkty(20);
            }
            break;
            case 3:{
                //        col = Color.black;
                pasekWyniku_.dodajPunkty(40);
            }
            break;
            case 4:{
                //        col = Color.black;
                pasekWyniku_.dodajPunkty(80);
            }
            break;
            case 5:{
                //        col = Color.black;
                pasekWyniku_.dodajPunkty(160);

            }
            break;
            case 6:{
                //        col = Color.black;
                pasekWyniku_.dodajPunkty(-20);
            }
            break;
            case 7:{
                //        col = Color.black;
                pasekWyniku_.dodajPunkty(-40);
            }
            break;
            case 8:{
                //        col = Color.black;
                pasekWyniku_.dodajPunkty(-80);
            }
            break;
            case 9:{
                //        col = Color.black;
                pasekWyniku_.dodajPunkty(-160);
            }
            break;
            case 10:{
                //        col = Color.black;
                pasekWyniku_.dodajCzas();
            }
            break;
            case 11:{
                //        col = Color.black;
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

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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
        //System.out.println("PosX="+x_pos+" PosY="+y_pos+" szerokosc:"+szerokosc+" szer_stara="+szer_stara+" wys stara="+ wys_stara+" a="+df.format(a)+" b="+df.format(b));
        pos_x=(int)(szerokosc*a);
        pos_y=(int)(wysokosc*b);
    }
}
