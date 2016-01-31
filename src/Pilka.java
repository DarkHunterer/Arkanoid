import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.awt.event.KeyListener;

/**
 * Klasa odpowiadająca za piłkę. Przechowuje wszystkie parametry piłkii. Pilnuje stałej wypadkowej prędkości piłki. Odpowiada za przesunięcia piłki i zmiany kierunku prędkości. Skaluje piłkę. Obsługuje rozpoczęcie lotu piłki przez użytkownika po jej ustawieniu.
 */
public class Pilka implements KeyListener {
    private int x_pos;
    private int y_pos;
    private int srednica;
    private int kat;
    private int dx;
    private int dy;
    private int velVect;
    private String string_imgBall;
    private Image imgBall;
    private int start;

    /**
     * Konsturktor piłki
     *
     * @param x_start   Pozycja startowa X'owa
     * @param y_start   Pozycja startowa Y'owa
     * @param szerokosc Srednica piłki
     * @param config    Plik konfiguracyjny
     */
    Pilka(int x_start, int y_start, int szerokosc, Data config) {
        System.out.println("Dodano pilke ");
        x_pos = x_start;
        y_pos = y_start;
        srednica = szerokosc;
        dy = config.Pilka_dy;
        velVect = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        dx = config.Pilka_dx;
        start = config.pilka_start;
        string_imgBall = config.pilka_string_imgBall;
        try {
            imgBall = ImageIO.read(new File(string_imgBall));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Metoda odpowiadająca za przechwycenie wcisniętego klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Metoda odpowiadająca za przechwycenie wciśnięcia klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metoda odpowiadajaca za przechwycenie zwolnienia klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            System.out.println(" Spacja puszczona pilka");
            start = 1;
        }
    }

    /**
     * Metoda wyznaczająca składowe dx i dy prędkości piłki po odbiciu od paletki
     *
     * @param kat kąt pod jakim piłka się odbiła
     */
    public void obliczPredkosc(int kat) {
        double tangens = Math.tan(Math.toRadians(kat));
        int skl_Y = (int) Math.round(Math.sqrt(Math.pow(velVect, 2) / (Math.pow(tangens, 2) + 1)));
        int skl_X = (int) Math.round(Math.sqrt(Math.pow(velVect, 2) - Math.pow(skl_Y, 2)));
        if (tangens > 0)
            dx = skl_X;
        if (tangens < 0)
            dx = -skl_X;
        dy = skl_Y;
        System.out.println("Tangens wynosi " + tangens + ".Skl_X wynosi " + skl_X + ".Skl_Y wynosi " + skl_Y);
    }

    /**
     * Metoda do pobrania kąta pod jakim odbiła sie piłka
     *
     * @return kąt odbicia piłki
     */
    public int getKat() {
        return kat;
    }

    /**
     * Metoda zwracająca obrazek reprezentujący piłkę na ekranie
     *
     * @return Obiekt Image reprezentujący piłkę
     */
    public Image getImage() {
        return imgBall;
    }

    /**
     * Metoda do ustawienia kąta odbicia piłki
     *
     * @param kat kąt pod jakim odbiła się piłka
     */
    public void setKat(int kat) {
        this.kat = kat;
    }

    /**
     * Metoda odpowiadajca za poruszenie pilki
     *
     * @param maxX Maksymalny X jaki piłka może przyjąć
     */
    public void porusz(int maxX) {
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
     * Ustawia pozycję Y-ową piłki
     *
     * @param y_pos Pozycia y-owa piłki
     */
    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    /**
     * Zwraca pozycje X-ową
     *
     * @return Pozycja x-owa piłki
     */
    public int getX_pos() {
        return x_pos;
    }

    /**
     * Zwraca pozycje Y-ową piłki
     *
     * @return Pozycja y-owa piłki
     */
    public int getY_pos() {
        return y_pos;
    }

    /**
     * Zwraca średnicę piłki
     *
     * @return Średnica piłki
     */
    public int getSrednica() {
        return srednica;
    }

    /**
     * Zwraca obiekt typu Rectangle który jest używany do wykrywania kolizji
     *
     * @return Obiekt typu Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x_pos, y_pos, srednica, srednica);
    }

    /**
     * Metoda zmieniająca zwrot pionowego kierunku piłki
     */
    public void odwroc_Y() {
        dy = -dy;
        System.out.println("Odwroc Y");
    }

    /**
     * Metoda zmieniająca zwrot poziomego kierunku piłki
     */
    public void odwroc_X() {
        dx = -dx;
        System.out.println("Odwroc X");
    }

    /**
     * Metoda kierująca piłkę w lewo i górę
     */
    public void lewo_gora() {
        if (dx > 0) {
            dx = -dx;
        }
        if (dy > 0) {
            dy = -dy;
        }
    }

    /**
     * Metoda kierująca piłkę w prawo i górę
     */
    public void prawo_gora() {
        if (dx < 0) {
            dx = -dx;
        }
        if (dy > 0) {
            dy = -dy;
        }
    }

    /**
     * Metoda kierujuąca piłkę w lewo i dół
     */
    public void lewo_dol() {
        if (dx > 0) {
            dx = -dx;
        }
        if (dy < 0) {
            dy = -dy;
        }
    }

    /**
     * Metoda kierująca piłkę w prawo i dół
     */
    public void prawo_dol() {
        if (dx < 0) {
            dx = -dx;
        }
        if (dy < 0) {
            dy = -dy;
        }
    }

    /**
     * Metoda skalująca piłke względem okna
     *
     * @param szerokosc  Nowa szerokosc okna
     * @param wysokosc   Nowa wysokosc okna
     * @param szer_stara Stara szerokosc okna
     * @param wys_stara  Stara wysokosc okna
     */
    public void skaluj(int szerokosc, int wysokosc, int szer_stara, int wys_stara) {
        if (srednica != 0) {
            double a = (double) x_pos / szer_stara;
            double b = (double) y_pos / wys_stara;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
            System.out.println("PosX=" + x_pos + " PosY=" + y_pos + " szerokosc:" + szerokosc + " szer_stara=" + szer_stara + " wys stara=" + wys_stara + " a=" + df.format(a) + " b=" + df.format(b));
            x_pos = (int) (szerokosc * a);
            y_pos = (int) (wysokosc * b);
            System.out.println("Wynik dzialania to:" + (int) (szerokosc * a) + " a pos_X to:" + x_pos);
            System.out.println("pilka:" + (int) (szer_stara) + " wysok stara:" + wys_stara + "nowa szer" + x_pos + "nowA_wys" + y_pos);
        }
        srednica = szerokosc / 45;
    }
}




