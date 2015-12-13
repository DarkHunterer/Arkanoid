import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Klasa odpowiadajaca za panel gry
 */

public class panelGry extends JPanel implements KeyListener,Runnable {

    private int[][] bricksPos;
    private paletka paletka_;
    private Pilka pilka_;
    private Boolean init = false;
    private Boolean pauza = false;
    private ArrayList<Klocek> klocki;
    private ArrayList<perk> perks;
    private int szer_stara;
    private int wys_stara;
    private Image imgTlo, imgPaddle,imgBall,imgPerk;
    private OknoGlowne oknoGlowneUchwyt;
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
            imgTlo = ImageIO.read(new File("tlo.jpg"));
            imgPaddle = ImageIO.read(new File("paddle.png"));
            imgBall = ImageIO.read(new File("ball.png"));
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    /**
     * Metoda tworz�ca obiekty paletki,pilki oraz tworzy klocki oraz uruchamia logik� gry.
     */
        public void start(){
            int width = getWidth();
            int heigth= getHeight();
            System.out.println("Szerokosc "+width +" wysokosc " +heigth);
            paletka_ = new paletka(width/2,heigth-heigth/10,width/5,heigth/25);
            pilka_ = new Pilka(width/2,heigth/2,heigth/45);
            dodajKlocki(width,heigth);
            perks = new ArrayList<>();
            pasekwyniku_.wylacz_pauze();
            wylaczPauze();
            //  pauza=false;

            init = true;
            if (thread == null)
            {
                thread = new Thread (this, "Watek panelu gry");
            }
            thread.start();
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
    }

    /**
     * Metoda odpowiadaj�ca za rysowanie paletki
     * @param g
     */
    private void rysuj_paletke(Graphics g){
       // g.setColor(paletka_.getKolor());
      //  g.drawRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
      //  g.fillRect(paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_());
        g.drawImage(imgPaddle,paletka_.getX(),paletka_.getY(),paletka_.getSzer_(),paletka_.getWys_(),null);
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
        g.drawImage(imgBall,pilka_.getX_pos(),pilka_.getY_pos(),pilka_.getSrednica(),pilka_.getSrednica(),null);
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
     * Metoda odpowiadajaca za narysowanie bonusow
     */
    public void rysuj_perki(Graphics g){
        if(!perks.isEmpty()) {
            for (perk p : perks) {
              /*  g.setColor(p.col);
                g.drawRect(p.getPos_x(),p.getPos_y(),p.getWidth(),p.getHeight());
                g.fillRect(p.getPos_x(),p.getPos_y(),p.getWidth(),p.getHeight());
            */
                g.drawImage(p.imgPerk,p.getPos_x(),p.getPos_y(),p.getWidth(),p.getHeight(),null);
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
                    pilka_.porusz(getWidth(), getHeight());
                    if(!perks.isEmpty()){
                        for(perk p : perks){
                            p.porusz();
                        }
                    }
                    sprawdzKolizje();

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
     * Metoda sprawdzaj�ca wyst�pienie kolizji
     */
    public void sprawdzKolizje(){
        Rectangle rpaletka = paletka_.getBounds();
        Rectangle rpilka = pilka_.getBounds();

        //poczate moich zmian
        // 2 razy elce po calej tablicy klockow, na przyszlosc to ograniczyc zapamietujac otzrebe klocki z kolizja
        Point pPG,pPD,pLD,pLG;
      //  Klocek temp = new Klocek(0,0,0,0,0);
        pLG = new Point((int) rpilka.getX(), (int) rpilka.getY());
        pLD = new Point((int) rpilka.getX(), (int) rpilka.getMaxY());
        pPG = new Point((int) rpilka.getMaxX(), (int) rpilka.getY());
        pPD = new Point((int) rpilka.getMaxX(), (int) rpilka.getMaxY());

        Point[] pointTab = new Point[4];
        pointTab[0] = pLG;
        pointTab[1] = pLD;
        pointTab[2] = pPG;
        pointTab[3] = pPD;
        //kiedys perki zabrac z kolizji i wyonywac je po sprawdzneiu wsyztskeigo a nie w kazdym z 3 przypadkow
        int ilosc_kolizji=0;
        for (Klocek kl :klocki){
            Rectangle rklocek = kl.getBounds();
            if (rklocek.intersects(rpilka)){
                if(kl.getWytrzymalosc()!=0) {
                    ilosc_kolizji++;
                }
            }
        }


        if (ilosc_kolizji==1){
            int zderzenia_rogi=0;
        for (Klocek kl : klocki) {
            if (kl.getWytrzymalosc() != 0){//zabezpieczenie do zliczenia tylko zyjacych
                Rectangle rklocek = kl.getBounds();

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
        }
        }
            if(zderzenia_rogi==1)
            {

                zderzenia_rogi=0;
                pilka_.odwroc_Y();//zalzoenie ze jak 1 rog pierwszensto ma odbicie gora dol
                //zmiana zalozenia odwrocmy oba
                pilka_.odwroc_X();
                /*//sytuacja gdy jeden rog odbicie gora dol pierwszenstwo
                    for (Klocek kl : klocki) {
                        Rectangle rklocek = kl.getBounds();
                        if (rklocek.contains(pLG) || rklocek.contains(pPG))
                        {
                            pilka_.odwroc_Y();
                        }
                    }
                */
            }//koniec 1 rogowej kolizji
            if(zderzenia_rogi==2)
            {

                zderzenia_rogi=0;//kiedys pozbyc sie tu petli po klockach
                for (Klocek kl : klocki)
                {
                    if (kl.getWytrzymalosc()!=0){
                    Rectangle rklocek = kl.getBounds();
                    if ((rklocek.contains(pLG) && rklocek.contains(pPG)) || (rklocek.contains(pLD) && rklocek.contains(pPD)))
                    {
                        pilka_.odwroc_Y();
                       // w tej wersji nie mozna tu kl.kolizja();
                    }
                    if ((rklocek.contains(pLG) && rklocek.contains(pLD)) || (rklocek.contains(pPG) && rklocek.contains(pPD)))
                    {
                        pilka_.odwroc_X();
                       // kl.kolizja();
                    }
                }
                }
                //sytuacja ze uderzylo na pewno w bok lub na pewno w podstawy

            }//koniec 2 rogow
            //dodac bonusy i kolizje na klocku
            for (Klocek kl : klocki) {
                Rectangle rklocek = kl.getBounds();
                if (rklocek.intersects(rpilka)){
                    if(kl.getWytrzymalosc()!=0)
                    {
                        kl.kolizja();
                        pasekwyniku_.dodajPunkty();
                    }
                    if (kl.getWytrzymalosc()==0)
                    {
                        //wklej tu bonus
                       // temp = kl;
                        int i = generator.nextInt(10);
                        System.out.println("Wynik losowania to:"+i);
                        if(i==1){
                            perks.add(new perk(kl.getPos_X(), kl.getPos_Y(), kl.getSzer(), kl.getWys(), "p",paletka_));
                        }
                        else if(i==2){
                            perks.add(new perk(kl.getPos_X(), kl.getPos_Y(), kl.getSzer(), kl.getWys(), "z",paletka_));
                        }
                    }
                }
            }


    }//koniec przypadku kolizji z 1 klockiem
        if(ilosc_kolizji==2)
        {
            int zmLG=0;
            int zmLD=0;
            int zmPD=0;
            int zmPG=0;
            for (Klocek kl : klocki) {
                if (kl.getWytrzymalosc() != 0) {
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
                    if (rklocek.intersects(rpilka)) {
                        kl.kolizja();
                        pasekwyniku_.dodajPunkty();
                    }
                }

                if (kl.getWytrzymalosc() == 0) {
                    //wklej tu bonus
                   // temp = kl;
                    int i = generator.nextInt(10);
                    System.out.println("Wynik losowania to:" + i);
                    if (i == 1) {
                        perks.add(new perk(kl.getPos_X(), kl.getPos_Y(), kl.getSzer(), kl.getWys(), "p", paletka_));
                    } else if (i == 2) {
                        perks.add(new perk(kl.getPos_X(), kl.getPos_Y(), kl.getSzer(), kl.getWys(), "z", paletka_));
                    }
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
            //sytuacja 2 klocki zderzenie
            //latwo, sprawdzic ktore 2 rogi pilki uderzyly i to jednoznacznie okresla odbicie
        }
        if (ilosc_kolizji==3)
        {
            //sytuacja 3 klocki zderzenie
            //kazdemu zabierz zycie
            //odiji obie wspolrzedne pilki zawsze, bo L ksztaltne
            for(Klocek kl: klocki)
            {
            Rectangle rklocek = kl.getBounds();
                if(rklocek.intersects(rpilka))
                {
                    if(kl.getWytrzymalosc()!=0) {
                        pasekwyniku_.dodajPunkty();
                        kl.kolizja();

                       // pilka_.odwroc_X();
                       // pilka_.odwroc_Y();
                        if (kl.getWytrzymalosc() == 0) {
                          //  temp = kl;
                            int i = generator.nextInt(10);
                            System.out.println("Wynik losowania to:"+i);
                            if(i==1){
                                perks.add(new perk(kl.getPos_X(), kl.getPos_Y(), kl.getSzer(), kl.getWys(), "p",paletka_));
                            }
                            else if(i==2){
                                perks.add(new perk(kl.getPos_X(), kl.getPos_Y(), kl.getSzer(), kl.getWys(), "z",paletka_));
                            }
                        }
                    }
                }
            }
            pilka_.odwroc_X();
            pilka_.odwroc_Y();
            ilosc_kolizji=0;//dla pewnosci napisalem...
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
            pilka_ = new Pilka(getWidth()/2,getHeight()/2,getHeight()/45);

            if(pasekwyniku_.zwrocZycie()==0){
                koniecGry();
            }
        }

        else if(pilka_.getY_pos()<=0){
            pilka_.setY_pos(1);
            pilka_.odwroc_Y();
        }

    }


    /**
     *
     */
    private void koniecGry(){
///Dodac sprawdzenie czy rekord. Jeśli tak, to zapytac o nick i zapisac. Zapisac wtedy bestscores do pliku

        wlaczPauze();
        String nick = JOptionPane.showInputDialog(null,"Twoj wynik to "+ pasekwyniku_.getWynik(),"Koniec gry",JOptionPane.PLAIN_MESSAGE);

        /// Poniżej dodać wygraną z powodu zbitych klockow
        // Dodac taki bonus za czas ==>  pasekwyniku_.dodajPunkty(pasekwyniku_.getCzas()*5);

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
            wlaczPauze();
            int X=0,Y=getHeight()/10;
            int maxRow=0,maxCol;
            int brickWidth=0,brickHeigth=0;
            maxRow = bricksPos.length;
            maxCol = bricksPos[0].length;
            brickWidth = getWidth()/maxCol;
            brickHeigth = (int)(getHeight()/maxRow/(2.5));

            paletka_.skaluj(getWidth(), getHeight(), szer_stara, wys_stara);
            pilka_.skaluj(getWidth(), getHeight(), szer_stara, wys_stara,paletka_.getSzer_());

            int j=0;
            Klocek temp = new Klocek(0,0,0,0,0);
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
            wylaczPauze();
        }
    }


}
