import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Daniel on 02.11.2015.
 */

public class panelGry extends JPanel implements ActionListener, KeyListener {

    private int[][] bricksPos;
    private paletka paletka_;
    private Pilka pilka_;
    private Boolean init = false;
    private Boolean pauza = true;
    private ArrayList<Klocek> klocki;
    private int szer_stara;
    private int wys_stara;
    private Timer timer;


    public panelGry(Color color){
        this.setOpaque(true);
        this.setBackground(color);
    }

        public void start(){
        int width = getWidth();
        int heigth= getHeight();
        System.out.println("Szerokosc "+width +" wysokosc " +heigth);
        paletka_ = new paletka(width/2,heigth-heigth/10,width/5,heigth/25);
        pilka_ = new Pilka(width/2,heigth/2,heigth/40);
        pilka_.setPredkosc(2);
        dodajKlocki(width,heigth);
        pauza=false;
        init = true;
    }
    private void dodajKlocki(int width,int heigth){
        bricksPos =new int[][]{
                {0,0,0,4,5,4,0,0,0,0,0,6},
                {0,0,0,4,5,4,0,0,0,0,0,5},
                {0,0,0,5,5,5,0,0,0,0,0,4},
                {0,0,0,4,4,4,0,0,0,0,0,3},
                {0,0,0,4,4,4,0,0,0,0,0,2},
                {0,0,0,4,4,4,0,0,0,0,0,1},
                {3,3,0,4,4,4,0,3,3,0,0,2},
                {3,3,0,4,4,4,0,3,3,0,0,3},
                {6,6,6,4,4,4,6,6,6,0,0,4},
                {6,6,6,4,4,4,6,6,6,0,0,5},
        };
        int X=width/20,Y=heigth/10;
        System.out.println("Tablica klockow. LENGTH: "+bricksPos.length);
        klocki = new ArrayList<>();
            for (int[] row : bricksPos) {
                for (int i=0; i<row.length; i++){
                    if(row[i]!=0) {
                        klocki.add(new Klocek(X, Y, width, heigth,row[i]));
                    }
                    else if(i==0&&klocki.isEmpty()) {
                        klocki.add(new Klocek(X, Y, width, heigth,0));
                    }
                    X += klocki.get(0).getSzer();
                }
                X=width/20;
                Y+=klocki.get(0).getWys();
            }

        for(Klocek kl :klocki)
        {
            System.out.println(kl.getBounds());
        }
    }
    private void rysuj_paletke(Graphics g){
        g.setColor(Color.red);
        g.drawRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
        g.fillRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());

    }
    private void rysuj_pilke(Graphics g){
        g.setColor(Color.green);
        g.drawOval(pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getPromien(),pilka_.getPromien());
        g.fillOval(pilka_.getX_pos(), pilka_.getY_pos(), pilka_.getPromien(), pilka_.getPromien());
        g.setColor(Color.white);
        g.drawOval(pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getPromien(),pilka_.getPromien());
    }
    private void rysuj_klocki(Graphics g){
        for(Klocek k :klocki) {
            g.setColor(k.getKolor());
            if(k.getWytrzymalosc()!=0) {
                g.drawRect(k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys());
                g.fillRect(k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys());
                g.setColor(Color.black);
                g.drawRect(k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys());
            }
            }
    }
    @Override
    public void paint(Graphics g) {
        rysuj_paletke(g);
        rysuj_pilke(g);
        rysuj_klocki(g);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        paletka_.keyReleased(e);
    }

    public void setPauza(Boolean pauza) {
        this.pauza = pauza;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        paletka_.keyPressed(e);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("TIMER_MAIN_TICK")) {
         //   System.out.println(e.getActionCommand());
            if (!pauza) {
                if (init) {
                    szer_stara = getWidth();
                    wys_stara = getHeight();
                    paletka_.porusz(getWidth());
                    pilka_.porusz(getWidth(), getHeight());
                    sprawdzKolizje();
                    repaint();
                }
            }
        }
       // System.out.println("Szer panelu gry to: "+getWidth() +" a wys panelu gry to: "+ getHeight());
       // System.out.println("Paletka: X: "+ paletka_.getX()+" Y: "+paletka_.getY()+" szer i wys: "+paletka_.getSzer_() +" "+ paletka_.getWys_());
      //  System.out.println("Pilka: X: "+ pilka_.getX_pos()+" Y: "+pilka_.getY_pos()+" promien: "+pilka_.getPromien());
    }
    public void sprawdzKolizje(){
        Rectangle rpaletka = paletka_.getBounds();
        Rectangle rpilka = pilka_.getBounds();

        Klocek temp = new Klocek(0,0,0,0,0);
        for(Klocek kl : klocki){
            Rectangle rklocek = kl.getBounds();

            if(rklocek.intersects(rpilka))
            {
                System.out.println("Zderzenie z klockiem");
                //pilka_.setY_pos((kl.getPos_Y()+ kl.getSzer()));
                pilka_.odwroc_Y();
                kl.kolizja();
                if (kl.getWytrzymalosc()==0){
                    temp = kl;
                }
            }
        }
        klocki.remove(temp);
        if(rpaletka.intersects(rpilka)){
            pilka_.odwroc_Y();
        }
        else if((pilka_.getX_pos()>=getWidth()-pilka_.getPromien())||(pilka_.getX_pos()<=1))
        {
            pilka_.odwroc_X();
        }
        else if((pilka_.getY_pos()+pilka_.getPromien()>=getHeight())) {
            pilka_.odwroc_Y();
            pilka_.setY_pos(getHeight()-pilka_.getPromien());
            JOptionPane.showMessageDialog(getParent(),"GAME OVER!");
        }
        else if(pilka_.getY_pos()<=0){
            pilka_.setY_pos(1);
            pilka_.odwroc_Y();
        }

        }
    public void skaluj()
    {
        if(init) {
            paletka_.skaluj(getWidth(), getHeight(), szer_stara, wys_stara);
            pilka_.skaluj(getWidth(), getHeight(), szer_stara, wys_stara,paletka_.getSzer_());
            for (Klocek k : klocki) {
                k.skaluj(getWidth(), getHeight(), szer_stara, wys_stara);
            }
            repaint();
        }
    }
}
