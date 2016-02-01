import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.*;

/**
 * Klasa odpowiadajaca za panel gry.
 * Uochomienie logiki gry, rysowanie wszystkiech elementów.
 * Obsługa kolizji elementów.
 * Obsługa wcnisniętych klawiszy.
 */

public class panelGry extends JPanel implements KeyListener, Runnable {

    /**
     *Pole przechowujące obiekt Data z konfiguracją gry
     */
    private Data config_;
    /**
     *Dwuwymiarowa tablica przechowująca pozycje klocków
     */
    private int[][] bricksPos;

    /**
     * Zmienna odpowiadajaca ilosci map ktore mamy do zagrania
     */
    private int ilosc_map;
    /**
     * Zmienna przechowujaca numer aktualnie rozgrywanej mapy
     */
    private int aktualna_mapa;
    /**
     * Pole przehcowujące obiekt paletki
     */
    private paletka paletka_;
    /**
     * Pole przechowująće obiekt piłki
     */
    private Pilka pilka_;
    /**
     *Zmienna wykorzystywana do uruchomienia w odpowiednim momencie metod run, paint i skaluj
     */
    private Boolean init;
    /**
     *Zmienna wykorzystywana do włączenia i wyłączenia pauzy
     */
    private Boolean pauza;
    /**
     *Lista przechowująca wszystkie klocki
     */
    private ArrayList<Klocek> klocki;
    /**
     *Lista przechowujca wszystkie perki
     */
    private ArrayList<perk> perks;
    /**
     *Zmienna przehcowująca starą szerokośc okna przed skalowaniem
     */
    private int szer_stara;
    /**
     *Zmienna przechowująca starą wysokośc okna, przed skalowaniem
     */

    private int wys_stara;
    /**
     *Obiekt Image przechowujący obrazek tła gry
     */
    private Image imgTlo;
    /**
     *Uchwyt na obiekt, który przechowuje dane na temat przebiegu rozgrywki i metody zapewniające dostęp do nich.
     */
    private OknoGlowne oknoGlowneUchwyt;
    /**
     *Zmienna wykorzystywana do wykrycia końca gry przez zbicie wszystkich klocków
     */
    private boolean pozostale_klocki;
    /**
     *Główny wątek gry
     */
    Thread thread;
    /**
     *Generator liczb pseudolosowych, wykorzystywany do pojawiania bonusów po zbiciu klocka
     */
    Random generator = new Random();
    /**
     *Pole przechowujące obiekt paska wyniku.
     */
    private pasekWyniku pasekwyniku_;
    /**
     * Pole przechowujące adres do obrazka tła
     */
private String string_tlo;
    /**
     * Konstruktor klasy panelGry
     *
     * @param config Obiekt z konfiguracją gry
     * @param OknoGlowneUchwyt_ Obiekt Okna Głownego w którym znajduje sie Panel Gry
     */
    public panelGry(Data config, OknoGlowne OknoGlowneUchwyt_) {
        this.setOpaque(true);
        this.setBackground(config.OknoGlowne_kolor_panelGry);
        oknoGlowneUchwyt = OknoGlowneUchwyt_;
        ilosc_map = config.ilosc_map;
        System.out.println("Ilosc map to: "+ilosc_map);
        aktualna_mapa=1;
        pasekwyniku_ = oknoGlowneUchwyt.getPasekWyniku_();
        bricksPos = config.bricksPos;
        string_tlo=config.panel_gry_string_imgPanel;
        init=config.panel_gry_init;
        pauza=config.panel_gry_pauza;
        pozostale_klocki=config.panel_gry_pozostale_klocki;
        try {
            imgTlo = ImageIO.read(new File(string_tlo));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        config_ = config;
    }

    /**
     * Metoda tworząca początki gry.
     * Tworzy obiekty paletki, piłki i klocki.
     * Tworzy listę bonusów.
     * Rozpoczyna logikę gry i jej wątek.
     */
    public void start() {
        int width = getWidth();
        int heigth = getHeight();
        System.out.println("Szerokosc " + width + " wysokosc " + heigth);
        paletka_ = new paletka(width / 2, heigth - heigth / 10, width / 5, heigth / 25, config_);
        pilka_ = new Pilka(width / 2, heigth / 2, heigth / 45, config_);
        dodajKlocki(width, heigth);
        perks = new ArrayList<>();
        init = true;
        if (thread == null) {
            thread = new Thread(this, "Watek panelu gry");
        }
        thread.start();
    }

    /**
     * Metoda wypełniająca listę klocków
     *
     * @param width  Szerokość okna względem którego skalowany jest klocek
     * @param heigth Wysokość okna względem któej skalowany jest klocek
     */
    private void dodajKlocki(int width, int heigth) {
        int X = 0, Y = heigth / 10;
        int maxRow = bricksPos.length;
        int maxCol = bricksPos[0].length;
        int brickWidth = width / maxCol;
       int brickHeigth = (int) (heigth / maxRow / (2.5));
        System.out.println("Tablica klockow. LENGTH: " + bricksPos.length);
        klocki = new ArrayList<>();
        for (int[] row : bricksPos) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != 0) {
                    klocki.add(new Klocek(X, Y, brickWidth, brickHeigth, row[i], config_));
                }
                X += brickWidth;
            }
            X = 0;
            Y += brickHeigth;
        }
    }

    /**
     * Metoda odpowiadająca za rysowanie paletki
     *
     * @param g Obiekt Graphics
     */
    private void rysuj_paletke(Graphics g) {
        g.drawImage(paletka_.getImage(), paletka_.getX(), paletka_.getY(), paletka_.getSzer_(), paletka_.getWys_(), null);
    }

    /**
     * Metoda odpowiadającaca za rysowanie piłki
     *
     * @param g Obiekt Graphics
     */
    private void rysuj_pilke(Graphics g) {
        g.drawImage(pilka_.getImage(), pilka_.getX_pos(), pilka_.getY_pos(), pilka_.getSrednica(), pilka_.getSrednica(), null);
    }

    /**
     * Metoda odpowiadająca za narysowanie na ekranie klocków
     *
     * @param g Obiekt Graphics
     */
    private void rysuj_klocki(Graphics g) {
        for (Klocek k : klocki) {
            if (k.getWytrzymalosc() != 0) {
                g.drawImage(k.getImage(), k.getPos_X(), k.getPos_Y(), k.getSzer(), k.getWys(), null);
            }
        }
    }

    /**
     * Metoda odpowiadajaca za narysowanie bonusow
     *
     * @param g Obiekt Graphics
     */
    public void rysuj_perki(Graphics g) {
        if (!perks.isEmpty()) {
            for (perk p : perks) {
                g.drawImage(p.getImage(), p.getPos_x(), p.getPos_y(), p.getWidth(), p.getHeight(), null);
            }
        }
    }

    /**
     * Metoda odpowiadająca za rysowanie elementów na ekranie
     *
     * @param g Obiekt Graphics
     */
    @Override
    public void paint(Graphics g) {
        paintComponent(g);
        if (init) {
            rysuj_paletke(g);
            rysuj_pilke(g);
            rysuj_klocki(g);
            rysuj_perki(g);
        }
    }


    /**
     * Metoda odpowiadająca za narysowanie tła
     *
     * @param g Obiekt Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgTlo, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Metoda odpowiadajaca za przechwycenie puszczenia klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        paletka_.keyReleased(e);
        pilka_.keyReleased(e);
    }

    /**
     * Metoda pauzująca rozgrywkę na życzenie użytkownika
     */
    public void wlaczPauze() {
        pasekwyniku_.wlacz_pauze();
        pauza = true;
    }

    /**
     * Metoda wyłączająca pauzę na życzenie użytkownika
     */
    public void wylaczPauze() {
        if (pasekwyniku_.zwrocZycie() > 0) {
            pasekwyniku_.wylacz_pauze();
            pauza = false;
        }
    }

    /**
     * Metoda wykorzystywana do zatrzymania piłki po pojawieniu jej na ekrnanie
     */
    public void ukryta_pauza_wlacz() {
        pasekwyniku_.ukryta_pauza_wlacz();
    }

    /**
     * Metoda wykorzystywana do wystartowania piłki po pojawieniu jej na ekranie
     */
    public void ukryta_pauza_wylacz() {
        pasekwyniku_.ukryta_pauza_wylacz();
    }

    /**
     * Metoda odpowiadajaca za przechwycenie wciśnięcia klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metoda odpowiadajaca za przechwycenie wciśnięcia klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        paletka_.keyPressed(e);
    }

    /**
     * Metoda odpowiadająca za obsługę zdarzeń
     * Poruszanie paletką
     * Poruszanie piłką
     * Poruszanie perków
     * Sprawdzanie kolizji
     * Sprawdzanie warunku końca gry po czasie
     *
     */

    @Override
    public void run() {
        int FPS = 30;
        while (true) {
            szer_stara = getWidth();
            wys_stara = getHeight();
            if (!pauza) {
                if (init) {
                    long now = System.nanoTime();
                    paletka_.porusz(getWidth());
                    pilka_.porusz(getWidth());
                    if (!perks.isEmpty()) {
                        for (perk p : perks) {
                            p.porusz();
                        }
                    }
                    sprawdzKolizje();
                    if (pasekwyniku_.getCzas() <= 0) {
                        System.out.println("koniec gry czas");
                            koniecGry();
                        }
                    pozostale_klocki = false;
                    long update = System.nanoTime() - now;
                    double milis = (double) update / 1000000.0;

                    if (milis < (double) 1000 / FPS) {
                        try {
                            thread.currentThread().sleep((1000 / FPS - (long) milis));
                        } catch (Exception ex) {
                        }
                    }
                }
            }
            repaint();
        }
    }

    /**
     * Metoda sprawdzająca wystapienie kolizji i zajmująca się ich skutkami.
     * Obsługuje wszelkie zderzenia w grze.
     * Powoduje wywoływanie metod zajmujących się bonusami
     * Pilnuje końca gry z braku żyć.
     * Pilnuje końća gry po zbiciu wsyzstkich klocków.
     */
    public void sprawdzKolizje() {
        Rectangle rpaletka = paletka_.getBounds();
        Rectangle rpilka = pilka_.getBounds();
        Point pPG, pPD, pLD, pLG;
        pLG = new Point((int) rpilka.getX(), (int) rpilka.getY());
        pLD = new Point((int) rpilka.getX(), (int) rpilka.getMaxY());
        pPG = new Point((int) rpilka.getMaxX(), (int) rpilka.getY());
        pPD = new Point((int) rpilka.getMaxX(), (int) rpilka.getMaxY());
        Point[] pointTab = new Point[4];
        pointTab[0] = pLG;
        pointTab[1] = pLD;
        pointTab[2] = pPG;
        pointTab[3] = pPD;
        Klocek[] klockiTab = new Klocek[3];
        //tablica klockow, wykrycie kolizji
        int ilosc_kolizji = 0;
        for (Klocek kl : klocki) {
            Rectangle rklocek = kl.getBounds();

            if (kl.getWytrzymalosc() != 0) {
                pozostale_klocki = true;
                if (rklocek.intersects(rpilka)) {
                    klockiTab[ilosc_kolizji] = kl;
                    ilosc_kolizji++;
                }
            }
        }
        //zderzenie z 1 klockiem
        if (ilosc_kolizji == 1) {
            System.out.println("Zderzenie z 1 klockiem");
            int zderzenia_rogi = 0;
            Rectangle rklocek = klockiTab[0].getBounds();
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
            }
            if (zderzenia_rogi == 2) {
                if ((rklocek.contains(pLG) && rklocek.contains(pPG)) || (rklocek.contains(pLD) && rklocek.contains(pPD))) {
                    pilka_.odwroc_Y();
                }
                if ((rklocek.contains(pLG) && rklocek.contains(pLD)) || (rklocek.contains(pPG) && rklocek.contains(pPD))) {
                    pilka_.odwroc_X();
                }
            }
            if (klockiTab[0].getWytrzymalosc() != 0) {
                klockiTab[0].kolizja();
                pasekwyniku_.dodajPunkty();
            }
            if (klockiTab[0].getWytrzymalosc() == 0) {
                add_perk(klockiTab[0], config_);
            }
        }
        //kolizje z 2 klockami:
        if (ilosc_kolizji == 2) {
            System.out.println("Zderzenie z 2 klockami");
            int zmLG = 0;
            int zmLD = 0;
            int zmPD = 0;
            int zmPG = 0;
            for (int i = 0; i < 2; i++) {
                Klocek kl = klockiTab[i];
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

            if ((zmPG == 1 && zmLG == 1) || (zmPD == 1 && zmLD == 1)) {
                pilka_.odwroc_Y();
            }
            if ((zmLG == 1 && zmLD == 1) || (zmPD == 1 && zmPG == 1)) {
                pilka_.odwroc_X();
            }

        }
        //kolizje z 3 klockami
        if (ilosc_kolizji == 3) {
            System.out.println("Zderzenie z 3 klockami");
            for (Klocek kl : klockiTab) {
                pasekwyniku_.dodajPunkty();
                kl.kolizja();
                if (kl.getWytrzymalosc() == 0) {
                    add_perk(kl, config_);
                }
            }
            pilka_.odwroc_X();
            pilka_.odwroc_Y();
        }

        if (!perks.isEmpty()) {
            int index = -1;
            for (perk p : perks) {
                Rectangle rperk = p.getBounds();
                if (rperk.intersects(rpaletka)) {
                    p.akcja();
                    index = perks.indexOf(p);
                } else if (p.getPos_y() == paletka_.getY()) {
                    index = perks.indexOf(p);
                }
            }
            if (index != -1) {
                perks.remove(index);
            }
        }

        if (rpaletka.intersects(rpilka)) {
            obliczKat((int) rpilka.getX());
            pilka_.obliczPredkosc(pilka_.getKat());
            System.out.println(pilka_.getKat());
            pilka_.odwroc_Y();
        } else if ((pilka_.getX_pos() >= getWidth() - pilka_.getSrednica()) || (pilka_.getX_pos() <= 1)) {
            pilka_.odwroc_X();
        } else if ((pilka_.getY_pos() + pilka_.getSrednica() >= getHeight())) {
            pilka_.odwroc_Y();
            pilka_.setY_pos(getHeight() - pilka_.getSrednica());
            //
            //Pilka uderzyla w podloge
            //
            pasekwyniku_.zmniejszZycie();
            pilka_ = new Pilka(getWidth() / 2, getHeight() / 2, getHeight() / 45, config_);
            paletka_.paletka_pozycja_start(getWidth(), getHeight());
            ukryta_pauza_wlacz();
            if (pasekwyniku_.zwrocZycie() == 0) {

                    System.out.println("koniec gry zycie");
                    nastepnaMapa();
                }

        } else if (pilka_.getY_pos() <= 0) {
            pilka_.setY_pos(1);
            pilka_.odwroc_Y();
        }
        if (pozostale_klocki == false) {
                System.out.println("koniec gry klocki");
            nastepnaMapa();

        }
    }

    /**
     * Metoda zmieniajaca plansze
     * W przypadku ukonczenia mapy, należy przejść do nastepnej nie konczac gry
     */
    private void nastepnaMapa() {
        aktualna_mapa++;
        if (aktualna_mapa < ilosc_map) {
            ukryta_pauza_wlacz();
            System.out.println("AKTUALNA MAPA: " + aktualna_mapa);
            config_.aktualizuj_mape(aktualna_mapa);
            pasekwyniku_.uaktualnij_mape();
            System.out.println("AKTUALNA MAPA: " + aktualna_mapa);
            int width = getWidth();
            int heigth = getHeight();
            bricksPos = config_.bricksPos;
            System.out.println("Szerokosc " + width + " wysokosc " + heigth);
            paletka_ = new paletka(width / 2, heigth - heigth / 10, width / 5, heigth / 25, config_);
            pilka_ = new Pilka(width / 2, heigth / 2, heigth / 45, config_);
            klocki = null;
            dodajKlocki(width, heigth);
            perks = new ArrayList<>();
            init = true;
        }
        else
            koniecGry();
    }
    /**
     * Metoda końca gry
     * Dodaje bonusowe punkty za czas i zycie do ostatecznego wyniku
     * Wyświetla wynik użytkownikowi
     * Obsługuje sprawdzanie i zapisywanie rekordowych wyników
     */
    private void koniecGry() {
            Boolean czyRekord = false;
            wlaczPauze();
            pasekwyniku_.dodajPunkty(pasekwyniku_.getCzas() * 20);
            pasekwyniku_.dodajPunkty(pasekwyniku_.zwrocZycie() * 100);
            int wynik = pasekwyniku_.getWynik();
            Map.Entry<String, Long> tempEntry = new AbstractMap.SimpleEntry<String, Long>("Nick", 0l);
            for (Map.Entry<String, Long> tempMap : oknoGlowneUchwyt.highScore.entrySet()) {
                if ((long) wynik > tempMap.getValue()) {
                    czyRekord = true;
                    tempEntry = tempMap;
                    System.out.println("Dziala");
                    break;
                }
            }
            if (czyRekord) {
                String nick = JOptionPane.showInputDialog(null, "Twoj wynik to " + pasekwyniku_.getWynik(), "Koniec gry", JOptionPane.PLAIN_MESSAGE);
                oknoGlowneUchwyt.highScore.remove(tempEntry.getKey());
                oknoGlowneUchwyt.highScore.put(nick, (long) wynik);
                OknoGlowne.BestScoreFrame bestscore;
                bestscore = oknoGlowneUchwyt.getScoreFrame();
                bestscore.zapiszDoPliku();
            } else {
                JOptionPane.showMessageDialog(null, "Twoj wynik to " + pasekwyniku_.getWynik(), "Koniec gry", JOptionPane.PLAIN_MESSAGE);
            }
    }
    /**
     * Metoda obliczajaca kat odbicia pilki od paletki
     * @param X_ball pozycja x-owa piłki
     */
    private void obliczKat(int X_ball) {
        int middlePaddle = (paletka_.getX() + paletka_.getSzer_() / 2);
        int beginPaddle = paletka_.getX();
        int X_collision = X_ball + pilka_.getSrednica() / 2 - beginPaddle;
       // System.out.println("Srodek paletki to " + middlePaddle + " X_collision to " + X_collision);
       // System.out.print("Szerokosc paletki to " + paletka_.getSzer_() + "\n");

        int kat = 80 * (X_collision - paletka_.getSzer_() / 2) / (paletka_.getSzer_() / 2);
        if (kat <= -80)
            kat = -80;
        else if (kat >= 80)
            kat = 80;
        pilka_.setKat(kat);
    }

    /**
     * Metoda skalująca elementy z panelu gry względem rozmiaru ekranu
     * Skaluje piłkę
     * Skaluje paletkę
     * Skaluje bonusy
     * Skaluje klocki
     */
    public void skaluj() {
        if (init) {

            int X = 0, Y = getHeight() / 10;
            int maxRow = bricksPos.length;
            int maxCol = bricksPos[0].length;
            int brickWidth = getWidth() / maxCol;
            int brickHeigth = (int) (getHeight() / maxRow / (2.5));
            paletka_.skaluj(getWidth(), getHeight(), szer_stara);
            pilka_.skaluj(getWidth(), getHeight(), szer_stara, wys_stara);
            for (perk perk_ : perks) {
                perk_.skaluj(brickHeigth, brickWidth, szer_stara, wys_stara, getHeight(), getWidth());
            }
            int j = 0;
            Klocek temp = new Klocek(0, 0, 0, 0, 0, config_);
            for (int[] row : bricksPos) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i] != 0) {
                        temp = klocki.get(j);
                        temp.skaluj(X, Y, brickWidth, brickHeigth);
                        j++;
                    }
                    X += brickWidth;
                }
                X = 0;
                Y += brickHeigth;
            }
            repaint();

        }
    }

    /**
     * Metoda decydująca czy pojawi się bonus
     * Jeśli tak, odpowiada za jego stworzenie
     */
    private void add_perk(Klocek kl, Data config) {
        int j = generator.nextInt(9);
        System.out.println("Wynik losowania to:" + j);
        if (j == 1 || j == 5) {
            perks.add(new perk(kl, paletka_, pasekwyniku_, config));
        }
    }

}
