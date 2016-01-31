import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.*;

/**
 *
 * Klasa odpowiadajaca za panel gry
 */

public class panelGry extends JPanel implements KeyListener,Runnable {

    private Data config_;
    private int[][] bricksPos;
    private paletka paletka_;
    private Pilka pilka_;
    private Boolean init = false;
    private Boolean pauza = false;
    private ArrayList<Klocek> klocki;
    private ArrayList<perk> perks;
    private int szer_stara;
    private int wys_stara;
    private Image imgTlo;
    private OknoGlowne oknoGlowneUchwyt;
    private int pozostale_klocki=1;
    Thread thread;
    Random generator = new Random();
   // public int punkty;
   // public int szanse;
    private pasekWyniku pasekwyniku_;
    /**
     * Konstruktor klasy panelGry
     * @param config Klasa obiektu w kt�rym jest konfiguracja
     */
    public panelGry(Data config,OknoGlowne OknoGlowneUchwyt_){
        this.setOpaque(true);
        this.setBackground(config.OknoGlowne_kolor_panelGry);
     //   punkty =0;
      //  szanse = 5;
        oknoGlowneUchwyt = OknoGlowneUchwyt_;
        pasekwyniku_ = oknoGlowneUchwyt.getPasekWyniku_();

        bricksPos = config.bricksPos;
        try {
            imgTlo = ImageIO.read(new File("grafika/tlo.jpg"));
           // imgPaddle = ImageIO.read(new File("grafika/paddle.png"));
           // imgBall = ImageIO.read(new File("ball.png"));
        }catch (Exception e){
            System.out.println(e.toString());
        }
        config_=config;
    }
    /**
     * Metoda tworz�ca obiekty paletki,pilki oraz tworzy klocki oraz uruchamia logik� gry.
     */
        public void start(){
            int width = getWidth();
            int heigth= getHeight();
            System.out.println("Szerokosc "+width +" wysokosc " +heigth);
            paletka_ = new paletka(width/2,heigth-heigth/10,width/5,heigth/25);
            pilka_ = new Pilka(width/2,heigth/2,heigth/45, config_);
            dodajKlocki(width,heigth);
            perks = new ArrayList<>();
           // pasekwyniku_.wylacz_pauze();
            wylaczPauze();
            //ukryta_pauza_wlacz();
            //  pauza=false;

            init = true;
            if (thread == null)
            {
                thread = new Thread (this, "Watek panelu gry");
            }
            thread.start();
           // ukryta_pauza_wlacz();
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
                        klocki.add(new Klocek(X, Y, brickWidth, brickHeigth,row[i], config_));
                    }
                    X += brickWidth;
                    // tutaj dodawac ilosc klockow
                }
                X=0;
                Y+=brickHeigth;
            }
    }

    /**
     * Metoda odpowiadaj�ca za rysowanie paletki
     * @param g
     */
    private void rysuj_paletke(Graphics g){
       // g.setColor(paletka_.getKolor());
      //  g.drawRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
      //  g.fillRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
        g.drawImage(paletka_.getImage(),paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_(),null);
    }

    /**
     * Metoda odpowiadaj�ca za rysowanie pi�ki
     * @param g
     */
    private void rysuj_pilke(Graphics g){
      /*  g.setColor(Color.green);
        g.drawOval(pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getSrednica(),pilka_.getSrednica());
        g.fillOval(pilka_.getX_pos(), pilka_.getY_pos(), pilka_.getSrednica(), pilka_.getSrednica());
        g.setColor(Color.white);
        g.drawOval(pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getSrednica(),pilka_.getSrednica());
    */
        g.drawImage(pilka_.getImage(),pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getSrednica(),pilka_.getSrednica(),null);
    }

    /**
     * Metoda odpowiadaj�ca za narysowanie na ekranie klock�w
     * @param g
     */
    private void rysuj_klocki(Graphics g){
        for(Klocek k :klocki) {
           // g.setColor(k.getKolor());
            if(k.getWytrzymalosc()!=0) {
              g.drawImage(k.getImage(),k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys(), null);
                //  g.drawRect(k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys());
               // g.fillRect(k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys());
              //  g.setColor(Color.black);
              //  g.drawRect(k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys());
            }
        }
    }

    /**
     * Metoda odpowiadajaca za narysowanie bonusow
     */
    public void rysuj_perki(Graphics g){
        if(!perks.isEmpty()) {
            for (perk p : perks) {
              /*  g.setColor(p.col);
                g.drawRect(p.getPos_x(),p.getPos_y(),p.getWidth(),p.getHeight());
                g.fillRect(p.getPos_x(),p.getPos_y(),p.getWidth(),p.getHeight());
            */
                g.drawImage(p.getImage(),p.getPos_x(),p.getPos_y(),p.getWidth(),p.getHeight(),null);
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
            rysuj_perki(g);
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
        g.drawImage(imgTlo,0,0,getWidth(),getHeight(),null);
    }
    /**
     *  Metoda odpowiadajaca za przechwycenie puszczenia klawisza
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        paletka_.keyReleased(e);
        //if (pilka_.getDy()==0){
          pilka_.keyReleased(e);
        //if (pilka_.getDy()==0){
      //  pilka_.keyReleased(e);}
    }

    /**
     * Metoda pauzuj�ca rozgrywk�
     *
     */
    public void wlaczPauze() {
       // thread = null;
        pasekwyniku_.wlacz_pauze();
        pauza = true;
    }
    public void wylaczPauze(){
       // thread = new Thread (this, "Watek panelu gry");
        if(pasekwyniku_.zwrocZycie()>0) {
            pasekwyniku_.wylacz_pauze();
            pauza = false;
        }
    }
    public void ukryta_pauza_wlacz(){
        pasekwyniku_.ukryta_pauza_wlacz();
    }
    public void ukryta_pauza_wylacz(){
        pasekwyniku_.ukryta_pauza_wylacz();
    }
    /**
     *  Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
        //if (pilka_.getDy()==0){
        //  pilka_.keyReleased(e);}
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
     *
     */

    @Override
    public void run() {
        int FPS = 30;
        while(true) {
            szer_stara = getWidth();
            wys_stara = getHeight();
            //   System.out.println(e.getActionCommand());
            if (!pauza) {
                if (init) {
                    long now = System.nanoTime();
                    paletka_.porusz(getWidth());
                    pilka_.porusz(getWidth());
                    if(!perks.isEmpty()){
                        for(perk p : perks){
                            p.porusz();
                        }
                    }
                    sprawdzKolizje();
                    if(pasekwyniku_.getCzas()<=0){
                        System.out.println("koniec gry czas");
                        koniecGry();
                    }
                    pozostale_klocki=0;
                 //   pozostale_klocki=0;
                    long update = System.nanoTime() - now;
                    double milis = (double) update / 1000000.0;

                    if (milis < (double) 1000 / FPS) {
                        try {
                            thread.currentThread().sleep((1000 / FPS - (long) milis));
                        } catch (Exception ex) {
                            //  System.out.println(e.toString());
                        }
                    }
                }
            }
            repaint();
        }
       // System.out.println("Szer panelu gry to: "+getWidth() +" a wys panelu gry to: "+ getHeight());
       // System.out.println("Paletka: X: "+ paletka_.getX()+" Y: "+paletka_.getY()+" szer i wys: "+paletka_.getSzer_() +" "+ paletka_.getWys_());
      //  System.out.println("Pilka: X: "+ pilka_.getX_pos()+" Y: "+pilka_.getY_pos()+" promien: "+pilka_.getSrednica());
    }

    /**
     * Metoda sprawdzaj�ca wyst�pienie kolizji, obsluguje bonusy i pilnuje konca gry
     */
    public void sprawdzKolizje(){
        Rectangle rpaletka = paletka_.getBounds();
        Rectangle rpilka = pilka_.getBounds();
//kolizje napisane od nowa poczatek
        //wziecie wspolrzendych pilki

        Point pPG,pPD,pLD,pLG;
        pLG = new Point((int) rpilka.getX(), (int) rpilka.getY());
        pLD = new Point((int) rpilka.getX(), (int) rpilka.getMaxY());
        pPG = new Point((int) rpilka.getMaxX(), (int) rpilka.getY());
        pPD = new Point((int) rpilka.getMaxX(), (int) rpilka.getMaxY());
//tablica dla [pilki
        Point[] pointTab = new Point[4];
        pointTab[0] = pLG;
        pointTab[1] = pLD;
        pointTab[2] = pPG;
        pointTab[3] = pPD;
        //tablica dla klopckow
        Klocek[] klockiTab = new Klocek[3];

// przejsice po klockach, zapamietaj w tablice klocki ze zderzen
        int ilosc_kolizji=0;
        for (Klocek kl :klocki){
            Rectangle rklocek = kl.getBounds();

                if(kl.getWytrzymalosc()!=0){
                    pozostale_klocki=1;
                    if (rklocek.intersects(rpilka))
                {
                    klockiTab[ilosc_kolizji]=kl;
                    ilosc_kolizji++;
                }
            }
        }
        //kolizja z 1 klockiem
        if (ilosc_kolizji==1) {
            System.out.println("Zderzenie z 1 klockiem");
            int zderzenia_rogi = 0;
            Rectangle rklocek = klockiTab[0].getBounds();
            //sytuacja 1 klocek do ogarniecia
            //rozpatruje rogami na pilce zalozenie: pierwszenstwo ma odbicie po Y na razie nie uwzgledniamy rogow, moze kiedys
            //porzebna mi tablica punktow na pilce
            //przejdz po tablicy i sr ile punktow wezslo 1 czy 2
            //kolizja i bonus po sprawdzeniyu wsyztskeigo najpierw odwrocic pilke
            for (Point p : pointTab) {
                if (rklocek.contains(p)) {
                    zderzenia_rogi++;
                }
            }
            if (zderzenia_rogi == 1) {
                if (rklocek.contains(pPD)) {
                    pilka_.lewo_gora();
                }
                if (rklocek.contains(pLD)) {
                    pilka_.prawo_gora();
                }
                if (rklocek.contains(pPG)) {
                    pilka_.lewo_dol();
                }
                if (rklocek.contains(pLG)) {
                    pilka_.prawo_dol();
                }
            }//koniec 1 rogowej kolizji
            //kolizja 2 rogi
            if (zderzenia_rogi == 2) {
                if ((rklocek.contains(pLG) && rklocek.contains(pPG)) || (rklocek.contains(pLD) && rklocek.contains(pPD))) {
                    pilka_.odwroc_Y();
                }
                if ((rklocek.contains(pLG) && rklocek.contains(pLD)) || (rklocek.contains(pPG) && rklocek.contains(pPD))) {
                    pilka_.odwroc_X();
                }
            }//koniec 2 rogow
            //kolizja na klocki i pasek wyniku
            if (klockiTab[0].getWytrzymalosc() != 0) {
                klockiTab[0].kolizja();
                pasekwyniku_.dodajPunkty();
            }
            //bonus edytowac po mzianie perkow
            if (klockiTab[0].getWytrzymalosc() == 0) {
                add_perk(klockiTab[0], config_);
            }
        }//koniec przypadku kolizji z 1 klockiem
//kolizje z 2 klockami:

        if(ilosc_kolizji==2)
        {
            System.out.println("Zderzenie z 2 klockami");
            int zmLG=0;
            int zmLD=0;
            int zmPD=0;
            int zmPG=0;
            for (int i=0; i<2; i++) {
                Klocek kl=klockiTab[i];
                    Rectangle rklocek = kl.getBounds();
                    if (rklocek.contains(pLG)) {
                        zmLG = 1;
                    }
                    if (rklocek.contains(pLD)) {
                        zmLD = 1;
                    }
                    if (rklocek.contains(pPG)) {
                        zmPG = 1;
                    }
                    if (rklocek.contains(pPD)) {
                        zmPD = 1;
                    }
                        kl.kolizja();
                        pasekwyniku_.dodajPunkty();
                        if (kl.getWytrzymalosc() == 0) {
                          add_perk(kl, config_);
                        }


            }

            if((zmPG==1 && zmLG==1)||(zmPD==1 && zmLD==1))
            {
                pilka_.odwroc_Y();
            }
            if((zmLG==1 && zmLD==1)||(zmPD==1 && zmPG==1))
            {
                pilka_.odwroc_X();
            }

        }//koniec kolizji 2 klockow
        if (ilosc_kolizji==3)
        {
            System.out.println("Zderzenie z 3 klockami");
            //sytuacja 3 klocki zderzenie
            //kazdemu zabierz zycie
            //odiji obie wspolrzedne pilki zawsze, bo L ksztaltne
            for(Klocek kl: klockiTab)
            {
            //Rectangle rklocek = kl.getBounds();

                        pasekwyniku_.dodajPunkty();
                        kl.kolizja();
                //bonus
                        if (kl.getWytrzymalosc() == 0) {
                           add_perk(kl, config_);
                        }
            }
            pilka_.odwroc_X();
            pilka_.odwroc_Y();
        }

        if(!perks.isEmpty()) {
            int index=-1;
                for(perk p : perks){
                    Rectangle rperk = p.getBounds();
                    if (rperk.intersects(rpaletka)){
                        p.akcja();
                        index = perks.indexOf(p);
                    }
                    else if (p.getPos_y() == paletka_.getY()) {
                     index = perks.indexOf(p);
                     }
            }
            if(index!=-1){
                perks.remove(index);
            }
        }

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
              //
             //Pilka uderzyla w podloge
            //
            pasekwyniku_.zmniejszZycie();
            pilka_ = new Pilka(getWidth()/2,getHeight()/2,getHeight()/45, config_);
            paletka_.paletka_pozycja_start(getWidth(), getHeight());
            ukryta_pauza_wlacz();
            if(pasekwyniku_.zwrocZycie()==0){
                System.out.println("koniec gry zycie");
                koniecGry();
            }

        }
        else if(pilka_.getY_pos()<=0){
            pilka_.setY_pos(1);
            pilka_.odwroc_Y();
        }
      //  if(pasekwyniku_.getCzas()<=0){
      //      System.out.println("koniec gry czas");
     //       koniecGry();
    //    }
        if(pozostale_klocki==0)
        {
            System.out.println("koniec gry klocki");
            koniecGry();
        }
    }


    /**
     *
     */
    private void koniecGry(){
        /// Poniżej dodać wygraną z powodu zbitych klockow
        // Dodac taki bonus za czas ==>  pasekwyniku_.dodajPunkty(pasekwyniku_.getCzas()*5);

        Boolean czyRekord =false;
        wlaczPauze();
        pasekwyniku_.dodajPunkty(pasekwyniku_.getCzas()*20);
        pasekwyniku_.dodajPunkty(pasekwyniku_.zwrocZycie()*100);
        int wynik = pasekwyniku_.getWynik();
        Map.Entry<String,Long> tempEntry = new AbstractMap.SimpleEntry<String, Long>("Nick", 0l);
           for(Map.Entry<String,Long> tempMap : oknoGlowneUchwyt.highScore.entrySet()){
            if((long)wynik > tempMap.getValue()){
                czyRekord = true;
                tempEntry = tempMap;
                System.out.println("Dziala");
                break;
            }
        }
        if(czyRekord){
            String nick = JOptionPane.showInputDialog(null,"Twoj wynik to "+ pasekwyniku_.getWynik(),"Koniec gry",JOptionPane.PLAIN_MESSAGE);
            oknoGlowneUchwyt.highScore.remove(tempEntry.getKey());
            oknoGlowneUchwyt.highScore.put(nick,(long)wynik);
            OknoGlowne.BestScoreFrame bestscore;
            bestscore = oknoGlowneUchwyt.getScoreFrame();
            bestscore.zapiszDoPliku();
        }
        else{
            JOptionPane.showMessageDialog(null,"Twoj wynik to "+ pasekwyniku_.getWynik(),"Koniec gry",JOptionPane.PLAIN_MESSAGE);
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

        int kat = 80 * (X_collision - paletka_.getSzer_() / 2) / (paletka_.getSzer_() / 2);
        if(kat <= -80)
            kat = -80;
        else if (kat >=80)
            kat = 80;
        pilka_.setKat(kat);
    }

    /**
     * Metoda odpowiadaj�ca za skalowanie element�w panelu gry wzgl�dem rozmiaru ekranu
     */
    public void skaluj()
    {
        if(init) {

            int X=0,Y=getHeight()/10;
            int maxRow=0,maxCol;
            int brickWidth=0,brickHeigth=0;
            maxRow = bricksPos.length;
            maxCol = bricksPos[0].length;
            brickWidth = getWidth()/maxCol;
            brickHeigth = (int)(getHeight()/maxRow/(2.5));
            paletka_.skaluj(getWidth(), getHeight(), szer_stara, wys_stara);
            pilka_.skaluj(getWidth(), getHeight(), szer_stara, wys_stara);
            for(perk perk_ : perks){
                perk_.skaluj(brickHeigth, brickWidth, szer_stara, wys_stara, getHeight(), getWidth());
            }
            int j=0;
            Klocek temp = new Klocek(0,0,0,0,0, config_);
            for (int[] row : bricksPos) {
                for (int i=0; i<row.length; i++){
                    if(row[i]!=0) {
                        temp = klocki.get(j);
                        temp.skaluj(X,Y,brickWidth,brickHeigth);
                        j++;
                    }
                    X += brickWidth;
                }
                X=0;
                Y+=brickHeigth;
            }
            repaint();

        }
    }

    /**
     * metoda do wywolania perkow
     */
    private void add_perk(Klocek kl, Data config){
        int j = generator.nextInt(9);
        System.out.println("Wynik losowania to:" + j);
        if (j == 1 || j==5) {
            perks.add(new perk(kl, paletka_, pasekwyniku_,  config));
    }
}

}
