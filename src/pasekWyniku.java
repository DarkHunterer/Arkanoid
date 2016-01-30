import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Klasa paska wyniku
 */
public class pasekWyniku extends JPanel implements ActionListener{

    private int zycie;
    private int wynik;
    private int czas ;
    private int licznik;
    private Boolean init=false;
    private Color kolor_;

    private JLabel labelZycie;
    private JLabel labelWynik;
    private JLabel labelCzas;
    private JLabel labelPauza;

    /**
     *
     * @param config Obiekt odpowiadajacy za konfiguracje
     */
    public pasekWyniku(Data config){
        licznik=0;
        wynik=0;
        zycie = config.PasekWyniku_const_zycie;
        czas = config.PasekWyniku_const_czas;
        kolor_ = config.OknoGlowne_kolor_pasekWyniku;
        this.setBackground(kolor_);
        setLayout(new FlowLayout());

        zycie = config.PasekWyniku_const_zycie;
        czas = config.PasekWyniku_const_czas;

        labelZycie= new JLabel("Zycie: "+ zycie);
        labelWynik= new JLabel("Wynik: "+ wynik);
        labelCzas= new JLabel("Czas: "+ czas);
        labelZycie= new JLabel();
        labelWynik= new JLabel();
        labelCzas= new JLabel();
        labelPauza = new JLabel("PAUZA");
        labelPauza.setVisible(false);

        this.setOpaque(true);
    }
    public void dodajPunkty(){
        wynik+=10;
    }
    public void dodajPunkty(int pkt){
        wynik+=pkt;
    }
    public void zmniejszZycie(){
        zycie--;
    }
    public void dodajZycie(){
        zycie++;
    }
    public int zwrocZycie(){
        return zycie;
    }
    public void dodajCzas(){
        czas=czas+5;
    }

    /**
     * Metoda odpowiadajaca za inicjacje paska
     */
    public void start(Data config){
        ustawGUI();
        zerujWartosci(config);

    }

    public int getCzas() {
            return czas;
    }

    public int getWynik() {
        return wynik;
    }

    /**
     * Metoda odpowiadajaca za dodanie labeli wyniku, �ycia i czasu do panelu paska wyniku
     */
    private void ustawGUI(){
        add(labelWynik);
        add(labelZycie);
        add(labelCzas);
        add(labelPauza);

               ////
            ////
           ////
        ////
        /*
        do zrobienia
           ImageIcon pauzaIcon = new ImageIcon("pauza.png");
        Image img = pauzaIcon.getImage();
        img = img.getScaledInstance(getWidth(),getHeight(), Image.SCALE_SMOOTH);
        pauzaIcon = new ImageIcon(img);
        labelPauza.setIcon(pauzaIcon);
         */
        init=true;
    }
    private void zerujWartosci(Data config){
        licznik=0;
        wynik=0;
        zycie = config.PasekWyniku_const_zycie;
        czas = config.PasekWyniku_const_czas;
    }
    private void zaktualizujWynik(int wynik_){
        wynik = wynik_;
    }
    private void zaktualizujZycie(int zycie_){
        zycie = zycie_;
    }
    public void zaktualizujWartosci(int wynik_, int zycie_){
        zaktualizujWynik(wynik_);
        zaktualizujZycie(zycie_);
    }
    /**
     * Metoda obs�uguj�ca zdarzenia. Zmniejsza czas pozosta�y do ko�ca
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (init) {
            licznik += 10;
            labelCzas.setText("Czas: " + czas);
            labelWynik.setText("Wynik: "+wynik);
            labelZycie.setText("Zycie: "+ zycie);
            if ((licznik % 1000) == 0 && czas > 0) {
                czas--;
            }
        }
    }
    public void wlacz_pauze(){
        init = false;
        labelPauza.setVisible(true);
    }
    public void wylacz_pauze(){
        labelPauza.setVisible(false);
        init=true;
    }
}
