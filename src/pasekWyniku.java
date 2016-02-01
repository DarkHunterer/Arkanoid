import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa paska wyniku. Zajmuje się odmierzaniem pozostałęgo czasu.
 * Przechowuje wynik i pozostałe życia.
 * Dodaje/odejmuje punkty.
 * Dodaje/odejmuje życia.
 */
public class pasekWyniku extends JPanel implements ActionListener {
    /**
     * zmienna przechowująca pozostałe życia
     */
    private int zycie;
    /**
     * Zmienna przechowująca wynik gracza
     */
    private int wynik;
    /**
     * Zmienna przechowująca pzostały czas
     */
    private int czas;
    /**
     * Zmienna wykorzystywana do obliczenia czasu
     */
    private int licznik;
    /**
     * Zmienna do inicjalizacji pauzy
     */
    private Boolean init;
    /**
     * Zmienna do inicjalizacji ukrytej pauzy, wykorzystywanej do startowania piłki przez uzytkownika
     */
    private Boolean init2;
    /**
     * Zmienna przechowująca kolor paska wyniku
     */
    private Color kolor_;
    /**
     * Obiekt JLabel do wyświetlania pozostałych żyć
     */
    private JLabel labelZycie;
    /**
     * Obiekt JLabel do wyświetlania wyniku
     */
    private JLabel labelWynik;
    /**
     * Obiekt JLabel do wyświetlenia pozostałego czasu
     */
    private JLabel labelCzas;
    /**
     * Obiekt JLabel do wyświetlenia stanu pauzy
     */
    private JLabel labelPauza;
    /**
     * String przechowujący napis do wyświetlenia czasu
     */
    private String string_czas;
    /**
     * String przechowujący napis do wyświetlenia wyniku
     */
    private String string_wynik;
    /**
     * String przechowujący napis do wyświetlenia życia
     */
    private String string_zycie;
    /**
     * String przechowujący napis do wyświetlenia stanu pauzy
     */
    private String string_pauza;

    /**
     * Pole przechowujace obiekt typu Data bedacy konfiguracja gry
     */
    private Data conf;
    /**
     * Konstruktor paska wyniku
     * Wczytuje dane z konfiguracji
     *
     * @param config Obiekt odpowiadajacy za konfiguracje
     */
    public pasekWyniku(Data config) {
        string_czas = config.PasekWyniku_string_czas;
        string_wynik = config.PasekWyniku_string_wynik;
        string_zycie = config.PasekWyniku_string_zycie;
        string_pauza = config.PasekWyniku_string_pauza;
        licznik = config.PasekWyniku_licznik;
        wynik = config.PasekWyniku_wynik;
        init = config.PasekWyniku_init;
        init2 = config.PasekWyniku_init2;
        zycie = config.PasekWyniku_const_zycie;
        czas = config.PasekWyniku_const_czas;
        kolor_ = config.OknoGlowne_kolor_pasekWyniku;
        this.setBackground(kolor_);
        setLayout(new FlowLayout());
        zycie = config.PasekWyniku_const_zycie;
        czas = config.PasekWyniku_const_czas;
        labelZycie = new JLabel(string_zycie + zycie);
        labelWynik = new JLabel(string_wynik + wynik);
        labelCzas = new JLabel(string_czas + czas);
        labelZycie = new JLabel();
        labelWynik = new JLabel();
        labelCzas = new JLabel();
        labelPauza = new JLabel(string_pauza);
        labelPauza.setVisible(false);
        conf = config;
        this.setOpaque(true);
    }

    /**
     * Metoda dodająca punkty przy uderzeniu w klocek
     */
    public void dodajPunkty() {
        wynik += 10;
    }

    /**
     * Metoda dodająca ilość punktów z parametru.
     * Wykorzystywana przy bonusach, przeliczeniu czasu i zyc na punkty
     *
     * @param pkt Ilość punktów
     */
    public void dodajPunkty(int pkt) {
        wynik += pkt;
    }

    /**
     * Metoda zmniejszająca ilość pozostałych żyć o 1
     */
    public void zmniejszZycie() {
        zycie--;
    }

    /**
     * Metoda dodająca 1 życie
     */
    public void dodajZycie() {
        zycie++;
    }

    /**
     * Metoda zwracająca ilośc pozostałych żyć
     *
     * @return Ilośc pozostałych żyć
     */
    public int zwrocZycie() {
        return zycie;
    }

    /**
     * Metoda dodająca dodatkowy czas 5 sekund
     * Wykorzystywana w bonusach
     */
    public void dodajCzas() {
        czas = czas + 5;
    }

    /**
     * Metoda odpowiadajaca za inicjacje paska wyniku
     *
     * @param config Plik konfiguracyjny
     */
    public void start(Data config) {
        ustawGUI();
        zerujWartosci(config);
    }

    /**
     * Metoda zwracająca pozostały czas
     *
     * @return Pozostały czas
     */
    public int getCzas() {
        return czas;
    }

    /**
     * Metoda zwracająca wynik
     *
     * @return Wynik gracza
     */
    public int getWynik() {
        return wynik;
    }

    /**
     * Metoda odpowiadajaca za dodanie labeli wyniku, życia i czasu do panelu paska wyniku
     */
    private void ustawGUI() {
        add(labelWynik);
        add(labelZycie);
        add(labelCzas);
        add(labelPauza);
        init = true;
    }

    /**
     * Metoda przywracająca początkowy stan paska wyniku
     *
     * @param config Plik konfiguracyjny
     */
    private void zerujWartosci(Data config) {
        licznik = config.PasekWyniku_licznik;
        wynik = config.PasekWyniku_wynik;
        zycie = config.PasekWyniku_const_zycie;
        czas = config.PasekWyniku_const_czas;
    }

    /**
     * Metoda obsługująca zdarzenia.
     * Zmniejsza czas pozostały do końca gry
     *
     * @param e Objekt ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (init) {
            licznik += 10;
            labelCzas.setText(string_czas + czas);
            labelWynik.setText(string_wynik + wynik);
            labelZycie.setText(string_zycie + zycie);
            if (init2) {
                if ((licznik % 1000) == 0 && czas > 0) {
                    czas--;
                }
            }
        }
    }

    /**
     * Metoda włączająca pauzę na żądanie gracza
     */
    public void wlacz_pauze() {
        init = false;
        labelPauza.setVisible(true);
    }

    /**
     * Metoda wyłączająca pauzę na żądanie gracza
     */
    public void wylacz_pauze() {
        labelPauza.setVisible(false);
        init = true;
    }

    /**
     * Metoda włączania ukrytej pauzy
     * Wykorzystywana do zatrzymania piłki po ustawieniu jej na pozycji startowej
     */
    public void ukryta_pauza_wlacz() {
        init2 = false;
    }

    /**
     * Metoda wyłączająca ukryta pauzę
     * Wykorzystywana do wystartowania piłki z pozycji startowej
     */
    public void ukryta_pauza_wylacz() {
        init2 = true;
    }
public void uaktualnij_mape(){
    czas = conf.PasekWyniku_const_czas;
    zycie = conf.PasekWyniku_const_zycie;
}
}
