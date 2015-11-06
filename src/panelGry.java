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

    private int[][] pos;
    private paletka paletka_;
    private Pilka pilka_;
    private Boolean init = false;
    private Boolean pauza = true;
    private ArrayList<Klocek> klocki;
    private int szer_stara;
    private int wys_stara;

    public panelGry(Color color){
        this.setOpaque(true);
        this.setBackground(color);
    }
    public void start(){
        int width_ = getWidth();
        int heigth= getHeight();
        System.out.println("Szerokosc "+width_ +" wysokosc " +heigth);
        paletka_ = new paletka(width_/2,heigth-heigth/10,width_/5,heigth/25);
        pilka_ = new Pilka(width_/2,heigth/2,heigth/30);
        pos =new int[][]{{20,20},{20,40},{20,60},{20,80},{20,100},{20,120},{20,140},{20,160},{60,20},{100,20},{140,20},{180,20},{220,20},{260,20}};
        klocki = new ArrayList<>();
        pilka_.setPredkosc(2);
        for (int i = 0; i < pos.length; i++) {
            for (int[] p : pos) {
                klocki.add(new Klocek(p[0], p[1], width_, heigth));
            }
           // System.out.println(width_);
        }
        skaluj();
        pauza=false;
        init = true;
    }
    private void rysuj_paletke(Graphics g){
        g.drawRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
        g.fillRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
    }
    private void rysuj_pilke(Graphics g){
        g.setColor(Color.green);
        g.drawOval(pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getPromien(),pilka_.getPromien());
        g.fillOval(pilka_.getX_pos(), pilka_.getY_pos(), pilka_.getPromien(), pilka_.getPromien());
    }
    private void rysuj_klocki(Graphics g){
        g.setColor(Color.red);
        for(Klocek k :klocki) {
            g.drawRect(k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys());
            g.fillRect(k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys());
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
       if(pauza!=true) {
           if (init == false) {
               //paletka_.ustaw_pozycje(this.getWidth() / 2, this.getHeight() - this.getHeight() / 10, getWidth() / 5, getHeight() / 25);
               //pilka_.ustaw_pozycje(this.getWidth() / 2, this.getHeight() / 2, this.getHeight() / 30);
               //init = true;
           }
           szer_stara = getWidth();
           wys_stara = getHeight();
           paletka_.porusz(getWidth());
           pilka_.porusz(getWidth(),getHeight());
           sprawdzKolizje();
           repaint();
       }
       // System.out.println("Szer panelu gry to: "+getWidth() +" a wys panelu gry to: "+ getHeight());
       // System.out.println("Paletka: X: "+ paletka_.getX()+" Y: "+paletka_.getY()+" szer i wys: "+paletka_.getSzer_() +" "+ paletka_.getWys_());
      //  System.out.println("Pilka: X: "+ pilka_.getX_pos()+" Y: "+pilka_.getY_pos()+" promien: "+pilka_.getPromien());
    }
    public void sprawdzKolizje(){
        Rectangle rpaletka = paletka_.getBounds();
        Rectangle rpilka = pilka_.getBounds();

        for(Klocek kl : klocki){
            Rectangle rklocek = kl.getBounds();

            if(rklocek.intersects(rpilka))
            {
                System.out.println("Zderzenie z klockiem");
                //pilka_.setY_pos((kl.getPos_Y()+ kl.getSzer()));
                pilka_.odwroc_Y();
            }
        }

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
            pilka_.odwroc_Y();
        }

        }
    public void skaluj()
    {
        if(init==true) {
            paletka_.skaluj(getWidth(), getHeight(), szer_stara, wys_stara);
            pilka_.skaluj(getWidth(), getHeight(), szer_stara, wys_stara,paletka_.getSzer_());
            for (Klocek k : klocki) {
                k.skaluj(getWidth(), getHeight(), szer_stara, wys_stara);
            }
            repaint();
        }
    }
}
