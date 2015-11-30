import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * Klasa odpowiadajaca za panel gry
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
    private Image img;

    /**
     * Konstruktor klasy panelGry
     * @param config Klasa obiektu w kt�rym jest konfiguracja
     */
    public panelGry(Data config){
        this.setOpaque(true);
        this.setBackground(config.OknoGlowne_kolor_panelGry);
        bricksPos = config.bricksPos;
        try {
            img = ImageIO.read(new File("tlo.jpg"));
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public panelGry(){};

    /**
     * Metoda tworz�ca obiekty paletki,pilki oraz tworzy klocki oraz uruchamia logik� gry.
     */
        public void start(){
        int width = getWidth();
        int heigth= getHeight();
        System.out.println("Szerokosc "+width +" wysokosc " +heigth);
        paletka_ = new paletka(width/2,heigth-heigth/10,width/5,heigth/25);
        pilka_ = new Pilka(width/2,heigth/2,heigth/30);
      //  pilka_.setPredkosc(1);
        dodajKlocki(width,heigth);
        pauza=false;
        init = true;
    }

    /**
     * Wype�nia list� klock�w z wczytanej mapy
     * @param width Szeroko�� okna wzgl�dem kt�rego skalowany jest klocek
     * @param heigth Wysoko�c okna wzgl�dem kt�rej skalowany jest klocek
     */
    private void dodajKlocki(int width,int heigth){
        int X=0,Y=heigth/10;
        int maxRow=0,maxCol;
        int brickWidth=0,brickHeigth=0;
        maxRow = bricksPos.length;
        maxCol = bricksPos[0].length;
        brickWidth = width/maxCol;
        brickHeigth = (int)(heigth/maxRow/(2.5));
        System.out.println("Tablica klockow. LENGTH: "+bricksPos.length);
       // X=brickWidth;
        klocki = new ArrayList<>();
            for (int[] row : bricksPos) {
                for (int i=0; i<row.length; i++){
                    if(row[i]!=0) {
                        klocki.add(new Klocek(X, Y, brickWidth, brickHeigth,row[i]));
                    }
                    X += brickWidth;
                }
                X=0;
                Y+=brickHeigth;
            }

        for(Klocek kl :klocki)
        {
            System.out.println(kl.getBounds());
        }
    }

    /**
     * Metoda odpowiadaj�ca za rysowanie paletki
     * @param g
     */
    private void rysuj_paletke(Graphics g){
        g.setColor(paletka_.getKolor());
        g.drawRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
        g.fillRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());

    }

    /**
     * Metoda odpowiadaj�ca za rysowanie pi�ki
     * @param g
     */
    private void rysuj_pilke(Graphics g){
        g.setColor(Color.green);
        g.drawOval(pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getSrednica(),pilka_.getSrednica());
        g.fillOval(pilka_.getX_pos(), pilka_.getY_pos(), pilka_.getSrednica(), pilka_.getSrednica());
        g.setColor(Color.white);
        g.drawOval(pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getSrednica(),pilka_.getSrednica());
    }

    /**
     * Metoda odpowiadaj�ca za narysowanie na ekranie klock�w
     * @param g
     */
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

    /**
     * Metoda odpowiadaj�ca za rysowanie
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        paintComponent(g);
        if(init) {
            rysuj_paletke(g);
            rysuj_pilke(g);
            rysuj_klocki(g);
        }
    }

    /**
     * Metoda odpowiadaj�ca za narysowanie t�a
     * @param g
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img,0,0,getWidth(),getHeight(),null);
    }
    /**
     *  Metoda odpowiadajaca za przechwycenie puszczenia klawisza
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        paletka_.keyReleased(e);
    }

    /**
     * Metoda pauzuj�ca rozgrywk�
     * @param pauza
     */
    public void setPauza(Boolean pauza) {
        this.pauza = pauza;
    }
    /**
     *  Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
    /**
     *  Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        paletka_.keyPressed(e);
    }

    /**
     *
     *  Metoda odpowiadajaca za obsluge zdarzen w obiekcie
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        int FPS = 30;
        if (e.getActionCommand().equals("TIMER_MAIN_TICK")) {
            szer_stara = getWidth();
            wys_stara = getHeight();
         //   System.out.println(e.getActionCommand());
            if (!pauza) {
                if (init) {
                    long now = System.nanoTime();
                    paletka_.porusz(getWidth());
                    pilka_.porusz(getWidth(), getHeight());
                    sprawdzKolizje();
                    long update = System.nanoTime() - now;
                    double milis = (double)update/1000000.0;

                    if (milis<(double)1000/25) {
                        try {
                            Thread.sleep(1000/25 - (long)milis);
                        } catch (Exception ex) {
                            System.out.println(e.toString());
                        }
                    }
                }
                repaint();
            }
        }
       // System.out.println("Szer panelu gry to: "+getWidth() +" a wys panelu gry to: "+ getHeight());
       // System.out.println("Paletka: X: "+ paletka_.getX()+" Y: "+paletka_.getY()+" szer i wys: "+paletka_.getSzer_() +" "+ paletka_.getWys_());
      //  System.out.println("Pilka: X: "+ pilka_.getX_pos()+" Y: "+pilka_.getY_pos()+" promien: "+pilka_.getSrednica());
    }

    /**
     * Metoda sprawdzaj�ca wyst�pienie kolizji
     */
    public void sprawdzKolizje(){
        Rectangle rpaletka = paletka_.getBounds();
        Rectangle rpilka = pilka_.getBounds();

        Point pG,pD,pL,pP;
        Klocek temp = new Klocek(0,0,0,0,0);
        for(Klocek kl : klocki){
            Rectangle rklocek = kl.getBounds();

            if(rklocek.intersects(rpilka)) {
                if (kl.getWytrzymalosc() != 0) {
                 /*   pG = new Point((int) rpilka.getX()+pilka_.getSrednica()/2, 0);
                    pD = new Point((int) rpilka.getX()+pilka_.getSrednica()/2 , (int) rpilka.getY());
                    pL = new Point(0, (int) rpilka.getY()+pilka_.getSrednica()/2);
                    pP = new Point((int) rpilka.getX(), (int) rpilka.getY()+pilka_.getSrednica()/2);

                    Point[] pointTab = new Point[4];
                    pointTab[0] = pG;
                    pointTab[1] = pD;
                    pointTab[2] = pL;
                    pointTab[3] = pP;
                   */
                    kl.kolizja();
                    pilka_.odwroc_Y();
                    if (kl.getWytrzymalosc() == 0) {
                        temp = kl;
                    }

                /*//pilka_.setY_pos((kl.getPos_Y()+ kl.getSzer()));
                System.out.println("Zderzenie z klockiem");
                pilka_.odwroc_Y();
                */
                    //  for (Point p : pointTab) {

                       /* if(p.getX()>=rklocek.getX()&&p.getX()<=rklocek.getMaxX()) {
                            System.out.println("DUPA1");
                            if(p.getY()>=rklocek.getY()&&p.getY()<=rklocek.getMaxY()){
                                System.out.println("DUPA2");
                                if (p.equals(pL) || p.equals(pP)) {
                                    System.out.println("PL PUNKT LEWY");
                                    pilka_.odwroc_X();
                                }

                                  if (p.equals(pG) || p.equals(pD)) {
                                      System.out.println("PG PUNKT GORNY");
                                      pilka_.odwroc_Y();
                                 }
                            if (p.equals(pD)) {
                                System.out.println("PD PUNKT DOLNY");
                                pilka_.odwroc_Y();
                            }

                            if (p.equals(pP)) {
                                System.out.println("PP PUNKT PRAWY");
                                pilka_.odwroc_X();
                            }
                            if (kl.getWytrzymalosc() == 0) {
                                //temp = kl;
                                klocki.remove(kl);
                            } else {
                                kl.kolizja();
                            }
                            //   }
                        }
                        }*/
                    //   }
                }
            }
        }
        klocki.remove(temp);
        if(rpaletka.intersects(rpilka)){
            obliczKat((int)rpilka.getX());
            pilka_.obliczPredkosc(pilka_.getKat());
            System.out.println(pilka_.getKat());
            pilka_.odwroc_Y();
        }
        else if((pilka_.getX_pos()>=getWidth()-pilka_.getSrednica())||(pilka_.getX_pos()<=1))
        {
            pilka_.odwroc_X();
        }
        else if((pilka_.getY_pos()+pilka_.getSrednica()>=getHeight())) {
            pilka_.odwroc_Y();
            pilka_.setY_pos(getHeight()-pilka_.getSrednica());
            JOptionPane.showMessageDialog(getParent(),"GAME OVER!");
        }
        else if(pilka_.getY_pos()<=0){
            pilka_.setY_pos(1);
            pilka_.odwroc_Y();
        }

    }

    /**
     * Metoda obliczajaca kat odbicia pilki od paletki
     */
    private void obliczKat(int X_ball) {
        int middlePaddle =(paletka_.getX()+paletka_.getSzer_()/2);
        int beginPaddle = paletka_.getX();
        int X_collision = X_ball+pilka_.getSrednica()/2 - beginPaddle;
        System.out.println("Srodek paletki to "+middlePaddle+" X_collision to "+X_collision);
        System.out.print("Szerokosc paletki to "+paletka_.getSzer_()+"\n");

        int kat = 90 * (X_collision - paletka_.getSzer_() / 2) / (paletka_.getSzer_() / 2);
        if(kat <= -90)
            kat = -90;
        else if (kat >=90)
            kat = 90;
        pilka_.setKat(kat);
    }

    /**
     * Metoda odpowiadaj�ca za skalowanie element�w panelu gry wzgl�dem rozmiaru ekranu
     */
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
