import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 *
 * Klasa odpowiadaj�ca za pi�ke
 */
public class Pilka implements KeyListener{
    private int x_pos;
    private int y_pos;
    private int srednica;
    private double predkosc;
    private int kat;
    private int dx;
    private int dy;
    private int velVect;
    private Image imgBall;
private int start;
    /**
     * Konsturktor pi�ki
     * @param x_start Pozycja startowa X'owa
     * @param y_start Pozycja startowa Y'owa
     * @param szerokosc Srednica pi�ki
     */
    Pilka(int x_start,int y_start,int szerokosc){
        System.out.println("Dodano pilke");
        x_pos = x_start;
        y_pos = y_start;
        srednica =szerokosc;
        predkosc=0.5;
       // dx = 120;
        dy = 120;
        velVect = (int)Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
        dx=0; //aby pilka po pojawieniu leciala pionowo w dol
        start=0;
        try {
            imgBall = ImageIO.read(new File("grafika/ball.png"));
        }catch (Exception e){ System.out.println(e.toString());}
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    /**
     *  Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key==KeyEvent.VK_SPACE) {

            System.out.println(" Spacja puszczona pilka");
             start=1;
            //dx=-predkosc;
        }
    }




    /**
     * Metoda wyznaczaj�ca sk�adowe X i Y pr�dko�ci wzgl�dem k�ta padania
     * @param kat
     */
    public void obliczPredkosc(int kat)
    {

        double tangens=Math.tan(Math.toRadians(kat));
       // double sumVel = Math.sqrt(Math.pow(dx,2)+ Math.pow(dy,2));
        int skl_Y = (int)Math.round(Math.sqrt(Math.pow(velVect,2)/(Math.pow(tangens,2)+1)));
        int skl_X = (int)Math.round(Math.sqrt(Math.pow(velVect,2)-Math.pow(skl_Y,2)));


        if(tangens>0)
            dx= skl_X;
        if(tangens<0)
            dx= -skl_X;
        dy= skl_Y;
        System.out.println("Tanens wynosi " + tangens+".Skl_X wynosi "+ skl_X+".Skl_Y wynosi "+skl_Y);
       // velVect = (int)Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));



    }
    /**
     * Getter zmiennej kat
     * @return
     */
    public int getKat() {
        return kat;
    }
/**
 * pobiera obrazel
 */
    public Image getImage(){
        return imgBall;
    }
    /**
     * Setter pola kat
     * @param kat
     */
    public void setKat(int kat) {
        this.kat = kat;
    }

    /**
     * Zwraca predkosc pi�ki
     * @return
     */
    public double getPredkosc() {
        return predkosc;
    }
    /**
     * Zwraca dx piłki
     * @return
     */
    public double getDx() {
        return dx;
    }
    /**
     * Zwraca dy pilki
     * @return
     */
    public double getDy() {
        return dy;
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
     * @param maxX Maksymalny X jaki pi�ka mo�e przyj��
     * @param maxY Maksymalny Y jaki pi�ka mo�e przyj��
     */
    public void porusz(int maxX,int maxY) {
        if (start == 1) {
            x_pos += dx / 28;
            y_pos += dy / 28;
            if (x_pos < 1)
                x_pos = 1;
            else if (x_pos + srednica > maxX)
                x_pos = maxX - srednica;
        }
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
     * Zwraca �rednice
     * @return
     */
    public int getSrednica() {
        return srednica;
    }

    /**
     * Zwraca obiekt typu Rectangle kt�ry jest u�ywany do wykrywania kolizji
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
     * Kieruje piłkę w lewo i górę
     */
    public void lewo_gora(){
    if (dx>0){
        dx=-dx;
    }
        if(dy>0){
            dy=-dy;
        }
}
    /**
     * Kieruje piłkę w prawo i górę
     */
    public void prawo_gora(){
        if (dx<0){
            dx=-dx;
        }
        if(dy>0){
            dy=-dy;
        }
    }
    /**
     * Kieruje piłkę w lewo i dół
     */
    public void lewo_dol(){
        if (dx>0){
            dx=-dx;
        }
        if(dy<0){
            dy=-dy;
        }
    }    /**
     * Kieruje piłkę w prawo i dół
     */
    public void prawo_dol(){
        if (dx<0){
            dx=-dx;
        }
        if(dy<0){
            dy=-dy;
        }
    }
    /**
     * Skaluje pi�ke wzgl�dem okna
     * @param szerokosc Nowa szerokosc okna
     * @param wysokosc  Nowa wysokosc okna
     * @param szer_stara Stara szerokosc okna
     * @param wys_stara Stara wysokosc okna
     */
    public void skaluj(int szerokosc, int wysokosc,int szer_stara, int wys_stara){
        if(srednica !=0) {
            double a = (double)x_pos/szer_stara;
            double b = (double)y_pos/wys_stara;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
            System.out.println("PosX="+x_pos+" PosY="+y_pos+" szerokosc:"+szerokosc+" szer_stara="+szer_stara+" wys stara="+ wys_stara+" a="+df.format(a)+" b="+df.format(b));
            x_pos=(int)(szerokosc*a);
            y_pos=(int)(wysokosc*b);
            System.out.println("Wynik dzialania to:"+(int)(szerokosc*a)+" a pos_X to:"+x_pos);
            System.out.println("pilka:"+(int)(szer_stara)+" wysok stara:"+wys_stara+"nowa szer"+x_pos+"nowA_wys"+y_pos);
        }
        srednica = szerokosc/45;
    }
}




