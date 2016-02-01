import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Klasa odpowiadająca za paletkę.
 * Przechowuje parametry paletki. Odpowiada za skalowanie paletki i jej poruszanie.
 */
public class paletka implements KeyListener {

    /**
     * Zmienna przechowująca x-ową pozycję paletki
     */
    private int x_pos;
    /**
     * Zmienna przechowująca y-ową pozycję paletki
     */
    private int y_pos;
    /**
     * Zmienna przechowująca szerokośc paletki
     */
    private int szer_;
    /**
     * Zmienna przechowująca wysokość paletki
     */
    private int wys_;
    /**
     * Zmienna przechowująca prędkośc paletki
     */
    private int predkosc;
    /**
     * Zmienna przechowująca obrazek reprezentujący paletkę na ekranie
     */
    private Image imgPaletka;
    /**
     * Zmienna przechowująca odległość o jaką przesuwa się paletka w jednej iteracji
     */
    private int dx;

    /**
     * Konstruktor paletki
     *
     * @param x_start   pozycja x-owa startowa paletki
     * @param y_start   pozycja y-owa startowa paletki
     * @param szerokosc szerokośc paletki
     * @param wysokosc  wysokość paletki
     * @param config    Plik konfiguracyjny
     */
    paletka(int x_start, int y_start, int szerokosc, int wysokosc, Data config) {
        System.out.println("Dodano paletke");
        x_pos = x_start - (szerokosc / 2);
        y_pos = y_start;
        szer_ = szerokosc;
        wys_ = wysokosc;
        predkosc = config.paletka_predkosc;
        try {
            imgPaletka = ImageIO.read(new File(config.paletka_string_imgPale));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Metoda pobierająca obrazek reprezentujący paletkę
     *
     * @return Obiekt Image reprezentujący paletkę
     */
    public Image getImage() {
        return imgPaletka;
    }

    /**
     * Metoda ustawiająca paletkę na wybranej pozycji
     * @param height wysokosc okna gry
     * @param width szerokosc okna gry
     */
    public void paletka_pozycja_start(int width, int height) {

        x_pos = (width / 2) - (szer_ / 2);
        y_pos = height - height / 10;
    }

    /**
     * Metoda ustawiająca szerokość paletki
     *
     * @param szer_ Szerokośc paletki
     */
    public void setSzer_(int szer_) {
        this.szer_ = szer_;
    }

    /**
     * Metoda odpowiadająca za poruszanie się paletki po ekranie w wyznaczonym obszarze
     *
     * @param maxX Ograniczenie poruszania paletki do krawędzi ekranu
     */
    public void porusz(int maxX) {
        x_pos += dx / 30;
        if (x_pos < -15)
            x_pos = -15;
        if (x_pos + szer_ > maxX + 15)
            x_pos = maxX - szer_ + 15;
    }

    /**
     * Metoda zwracająca szerokość paletki
     *
     * @return Szerokośc paletki
     */
    public int getSzer_() {
        return szer_;
    }

    /**
     * Metoda zwracajająca wysokośc paletki
     *
     * @return Wysokośc paletki
     */
    public int getWys_() {
        return wys_;
    }

    /**
     * Metoda zwracajająca x-ową pozycje paletki
     *
     * @return x-owa pozycja paletki
     */
    public int getX() {
        return x_pos;
    }

    /**
     * Metoda zwracająća y-owa pozycję paletki
     *
     * @return y-owa pozycja paletki
     */
    public int getY() {
        return y_pos;
    }

    /**
     * Metoda odpowiadająca za skalowanie paletki względem rozmiaru okna
     *
     * @param szerokosc  Nowa szerokosc okna
     * @param wysokosc   Nowa wysokosc okna
     * @param szer_stara Stara szerokosc okna
     */
    public void skaluj(int szerokosc, int wysokosc, int szer_stara) {
        if (szer_ != 0) {
            double a = (double) x_pos / szer_stara;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
            x_pos = (int) (szerokosc * a);
        }
        System.out.println("y_pos paletki to" + y_pos + "wysokosc ekranu to " + wysokosc);
        y_pos = wysokosc - wysokosc / 10;
        szer_ = szerokosc / 5;
        wys_ = wysokosc / 25;
    }

    /**
     * Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metoda odpowiadajaca za przechwycenie wciskania klawiszy sterujących paletką
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -predkosc;
        } else if (key == KeyEvent.VK_RIGHT) {
            dx = predkosc;
        }
    }

    /**
     * Metoda odpowiadajaca za przechwycenie puszczenia klawiszy sterujących paletką
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            if (x_pos > 0) {
                dx = 0;
            }
        }
    }

    /**
     * Metoda zwracająca obiekt Rectangle wykorzystywany do wykrywania kolizji z paletką
     *
     * @return Obiekt typu Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x_pos, y_pos, szer_, wys_);
    }
}
