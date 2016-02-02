import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Klasa perk odpowiadająca za obsługę bonusów wypadających z klocków.
 */
public class perk {
    /**
     * Zmienna typu int przechowująca x-ową pozycję bonusu
     */
    private int pos_x;
    /**
     * Zmienna typu int przechowująca zmienną y-ową bonusu
     */
    private int pos_y;
    /**
     * Zmienna typu int przechowująca wysokość bonusu niezbędną do jego narysowania
     */
    private int height;
    /**
     * Zmienna typu int przechowująca szerokość bonusu niezbędną do jego narysowania
     */
    private int width;
    /**
     * Zmienna typu int przechowująca rodzaj tupy bonusu
     * 0 - Bonus zwężający paletkę do 3/4 szerokości
     * 1 - Bonus poszerzający paletkę do 4/3 szerokości
     * 2 - Bonus dodający 20 punktów
     * 3 - Bonus dodający 40 punktów
     * 4 - Bonus dodający 80 punktów
     * 5 - Bonus dodający 160 punktów
     * 6 - Bonus odejmujący 20 punktów
     * 7 - Bonus odejmujący 40 punktów
     * 8 - Bonus odejmujący 80 punktów
     * 9 - Bonus odejmujący 160 punktów
     * 10 - Bonus dodający 5 sekund czasu
     * 11 - Bonus dodający dodatkowe życie
     */
    private int kod;
    /**
     * Zmienna typu int przechowująca wartośc o jaką przesuwa się bonus na ekranie z każdą iteracją
     */
    private int dy;
    /**
     * Pole przechowujące paletkę z którą bonus będzie się zderzać
     */
    private paletka paletka_;
    /**
     * Pole przechowująće pasek wyniku do którego bonus przesyłą informacje o dodatkowych punktach, zyciach, czasie
     */
    private pasekWyniku pasekWyniku_;
    /**
     * Pole przechfowujące obrazek reprezentujący bonus danego typu na planszy
     */
    private Image imgPerk;
    /**
     * Pole przechowujące generator liczb pseudolosowych, niezbędny do ustalenia typu bonusu jaki pojawi się na ekranie
     */
    private Random generator = new Random();
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 0
     */
    private String bonus_0;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 1
     */
    private String bonus_1;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 2
     */
    private String bonus_2;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 3
     */
    private String bonus_3;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 4
     */
    private String bonus_4;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 5
     */
    private String bonus_5;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 6
     */
    private String bonus_6;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 7
     */
    private String bonus_7;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 8
     */
    private String bonus_8;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 9
     */
    private String bonus_9;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 10
     */
    private String bonus_10;
    /**
     * String przechowujący ścieżkę obrazka bonusu o kodzie 11
     */
    private String bonus_11;

    /**
     * Metoda wykorzystywana do tworzenia obiektu klocka
     *
     * @param kl    klocek z któego wypada bonus
     * @param pale  paletka z którą bonus ma się zderzyć aby się wykonać
     * @param pasek pasek wyniku do któego zapisywane są wyniki działania bonusu
     * @param config Data konfiguracja
     */
    public perk(Klocek kl, paletka pale, pasekWyniku pasek, Data config) {
        pos_x = kl.getPos_X();
        pos_y = kl.getPos_Y();
        height = kl.getWys();
        width = kl.getSzer();
        dy = 3;
        paletka_ = pale;
        pasekWyniku_ = pasek;
        kod = generator.nextInt(12);
        bonus_0 = config.Perk_string_bonus_0;
        bonus_1 = config.Perk_string_bonus_1;
        bonus_2 = config.Perk_string_bonus_2;
        bonus_3 = config.Perk_string_bonus_3;
        bonus_4 = config.Perk_string_bonus_4;
        bonus_5 = config.Perk_string_bonus_5;
        bonus_6 = config.Perk_string_bonus_6;
        bonus_7 = config.Perk_string_bonus_7;
        bonus_8 = config.Perk_string_bonus_8;
        bonus_9 = config.Perk_string_bonus_9;
        bonus_10 = config.Perk_string_bonus_10;
        bonus_11 = config.Perk_string_bonus_11;
        switch (kod) {
            case 0: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_0));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 1: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_1));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 2: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_2));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 3: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_3));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 4: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_4));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 5: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_5));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            }
            break;
            case 6: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_6));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 7: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_7));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 8: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_8));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 9: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_9));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 10: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_10));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            break;
            case 11: {
                try {
                    imgPerk = ImageIO.read(new File(bonus_11));
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
     * Metoda odpowiadająca za wykonanie się bonusu. Dodanie punktów, życia, czasu, odjęcie czasu
     */
    public void akcja() {
        switch (kod) {
            case 0: {
                paletka_.setSzer_(paletka_.getSzer_() * 3 / 4);
            }
            break;
            case 1: {
                paletka_.setSzer_(paletka_.getSzer_() * 4 / 3);
            }
            break;
            case 2: {
                pasekWyniku_.dodajPunkty(20);
            }
            break;
            case 3: {
                pasekWyniku_.dodajPunkty(40);
            }
            break;
            case 4: {
                pasekWyniku_.dodajPunkty(80);
            }
            break;
            case 5: {
                pasekWyniku_.dodajPunkty(160);

            }
            break;
            case 6: {
                pasekWyniku_.dodajPunkty(-20);
            }
            break;
            case 7: {
                pasekWyniku_.dodajPunkty(-40);
            }
            break;
            case 8: {
                pasekWyniku_.dodajPunkty(-80);
            }
            break;
            case 9: {
                pasekWyniku_.dodajPunkty(-160);
            }
            break;
            case 10: {
                pasekWyniku_.dodajCzas();
            }
            break;
            case 11: {
                pasekWyniku_.dodajZycie();
            }
            break;

            default:
                break;
        }

    }

    /**
     * Metoda do pobrania pozycji x-owej bonusu
     *
     * @return Pozycja x-owa bonusu
     */
    public int getPos_x() {
        return pos_x;
    }

    /**
     * Metoda do pobrania pozycji y-owej bonusu
     *
     * @return Pozycja y-owa bonusu
     */
    public int getPos_y() {
        return pos_y;
    }

    /**
     * Metoda zwracająca wysokość bonusu niezbędną do narysowania
     *
     * @return wysokośc bonusu
     */
    public int getHeight() {
        return height;
    }

    /**
     * Metoda do pobrania szerokości bonusu
     *
     * @return Szerokość bonusu
     */
    public int getWidth() {
        return width;
    }

    /**
     * Metoda służąca do przesunięcia pozycji bonusu na ekranie
     */
    public void porusz() {

        pos_y += dy;
    }

    /**
     * Metoda zwracająca obiekt Rectangle stworzony na podstawie rozmiarów i pozycji bonusu
     *
     * @return obiekt Rectangle stworzony z danych z bonusu
     */
    public Rectangle getBounds() {
        return new Rectangle(pos_x, pos_y, width, height);
    }

    /**
     * Metoda służąca do skalowania bonusu
     *
     * @param wys_       Nowa wysokość bonusu, stworzona na podstawie nowego wymiaru klocków
     * @param szer_      Nowa szerokośc bonusu stworzona na podstawie nowego wymairu klocków
     * @param szer_stara Stara szerokość okna gry
     * @param wys_stara  Stara wysokośc okna gry
     * @param wysokosc   Nowa wysokość okna gry
     * @param szerokosc  Nowa szerokość okna gry
     */
    public void skaluj(int wys_, int szer_, int szer_stara, int wys_stara, int wysokosc, int szerokosc) {
        height = wys_;
        width = szer_;
        double a = (double) pos_x / szer_stara;
        double b = (double) pos_y / wys_stara;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);
        pos_x = (int) (szerokosc * a);
        pos_y = (int) (wysokosc * b);
    }

    /**
     * Metoda pobierająca obrazek reprezentujący bonus na ekranie gry
     *
     * @return Obrazek reprezentujący bonus
     */
    public Image getImage() {
        return imgPerk;
    }
}
