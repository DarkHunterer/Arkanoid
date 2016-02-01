import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * Klasa odpowiadająca za klocek. Przechowuje parametry każdego klocka, obsługuje zmianę parametrów po kolizji. Dostarcza dane do rysowania.
 */
public class Klocek {
    /**
     * Zmienna typu int przechowująca pozycję x-ową klocka
     */
    private int pos_X;
    /**
     * Zmienna typu int przechowująca pozycję y-ową klocka
     */
    private int pos_Y;
    /**
     * Zmienna typu int przechowująca szerokość klocka
     */
    private int szer;
    /**
     * Zmienna typu int przechowująca wysokość klocka
     */
    private int wys;
    /**
     * Zmienna typu int przechowująca pozostałe życie klocka
     */
    private int wytrzymalosc;
    /**
     * Pole przechowujące obrazek reprezentujący klocek o danej wytrzymałości
     */
    private Image imgKlocek;
    /**
     * Pole przechowujące ścieżkę do obrazka reprezentującego klocek o wytrzymałości 5
     */
    private String kafel_zycie_5;
    /**
     * Pole przechowujące ścieżkę do obrazka reprezentującego klocek o wytrzymałości 4
     */
    private String kafel_zycie_4;
    /**
     * Pole przechowujące ścieżkę do obrazka reprezentującego klocek o wytrzymałości 3
     */
    private String kafel_zycie_3;
    /**
     * Pole przechowujące ścieżkę do obrazka reprezentującego klocek o wytrzymałości 2
     */
    private String kafel_zycie_2;
    /**
     * Pole przechowujące ścieżkę do obrazka reprezentującego klocek o wytrzymałości 1
     */
    private String kafel_zycie_1;

    /**
     * Konstruktor obiektu klocek
     *
     * @param x         pozycja x-owa klocka
     * @param y         pozycja y-owa klocka
     * @param szerokosc Szerokosc klocka
     * @param wysokosc  Wysokośc klocka
     * @param zycie     Wytrzymalosc klocka
     * @param config    plik konfiguracyjny
     */
    Klocek(int x, int y, int szerokosc, int wysokosc, int zycie, Data config) {
        pos_X = x;
        pos_Y = y;
        szer = szerokosc;
        wys = wysokosc;
        wytrzymalosc = zycie;
        kafel_zycie_1 = config.Klocek_zycie_1;
        kafel_zycie_2 = config.Klocek_zycie_2;
        kafel_zycie_3 = config.Klocek_zycie_3;
        kafel_zycie_4 = config.Klocek_zycie_4;
        kafel_zycie_5 = config.Klocek_zycie_5;
        switch (zycie) {
            case 0:
                try {
                    this.finalize();
                } catch (Throwable e) {
                    System.out.println(e.toString());
                }
                break;
            case 1: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_1));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 2: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_2));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 3: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_3));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 4: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_4));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 5: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_5));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            default:
                break;
        }

    }

    /**
     * Metoda zwracająca pozycję x-ową klocka
     *
     * @return X-owa pozycja klocka
     */
    public int getPos_X() {
        return pos_X;
    }

    /**
     * Metoda zwracająca pozycję y-ową klocka
     *
     * @return Y-owa pozycja klocka
     */
    public int getPos_Y() {
        return pos_Y;
    }

    /**
     * Metoda zwracająca szerokość klocka
     *
     * @return Szerokość klocka
     */
    public int getSzer() {
        return szer;
    }

    /**
     * Metoda zwracająca wysokośc klocka
     *
     * @return Wysokośc klocka
     */
    public int getWys() {
        return wys;
    }

    /**
     * Metoda zmieniająca parametry klocka po skalowaniu
     *
     * @param pozycja_x   Nowa pozycja x-owa klocka
     * @param pozycja_y   Nowa pozycja Y-owa klocka
     * @param szer_klocka Nowa szerokość klocka
     * @param wys_klocka  Nowa wysokość klocka
     */
    public void skaluj(int pozycja_x, int pozycja_y, int szer_klocka, int wys_klocka) {
        pos_X = pozycja_x;
        pos_Y = pozycja_y;
        szer = szer_klocka;
        wys = wys_klocka;
    }

    /**
     * Metoda pobierająca obrazek niezbędny do narysowania klocka na ekranie
     *
     * @return Obiekt Image reprezentujący klocek na ekranie
     */
    public Image getImage() {
        return imgKlocek;
    }

    /**
     * Metoda obslugująca zmianę parametrów klocka po kolizji z pilka
     */
    public void kolizja() {
        System.out.println("Nastapila kolizja z klockiem. Wytrzymalosc=" + wytrzymalosc);
        if (wytrzymalosc > 0) {
            wytrzymalosc--;
        }

        switch (wytrzymalosc) {
            case 1: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_1));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 2: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_2));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 3: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_3));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 4: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_4));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 5: {
                try {
                    imgKlocek = ImageIO.read(new File(kafel_zycie_5));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            default:
                break;
        }
    }

    /**
     * Metoda do odczytania wytrzymałości klocka
     *
     * @return Wytrzymnałość klocka
     */
    public int getWytrzymalosc() {
        return wytrzymalosc;
    }

    /**
     * Metoda zwracająca obiekt typu Rectangle wykorzystywany do obsługi kolizji klocka z piłką
     *
     * @return Obiekt typu Rectangle stworzony na podstawie klocka
     */
    public Rectangle getBounds() {
        return new Rectangle(pos_X, pos_Y, szer, wys);
    }
}
