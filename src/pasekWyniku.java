import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 29.10.2015.
 * Klasa paska wyniku
 */
public class pasekWyniku extends JPanel implements ActionListener{

    //Dimension wymiar = new Dimension(50,200);
    private int zycie;
    private int wynik;
    private int czas ;
    private int licznik;
    private Boolean init=false;
    private Color kolor_;

    private JLabel labelZycie;
    private JLabel labelWynik;
    private JLabel labelCzas;

    /**
     *
     * @param config Obiekt odpowiadajacy za konfiguracje
     */
    public pasekWyniku(Data config){
        licznik=0;
        wynik=0;
        kolor_ = config.OknoGlowne_kolor_pasekWyniku;
        this.setBackground(kolor_);
        setLayout(new FlowLayout());

        zycie = config.PasekWyniku_const_zycie;
        czas = config.PasekWyniku_const_czas;

        labelZycie= new JLabel("Zycie: "+ zycie);
        labelWynik= new JLabel("Wynik: "+ wynik);
        labelCzas= new JLabel("Czas: "+ czas);
        this.setOpaque(true);
    }

    /**
     * Metoda odpowiadajaca za inicjacje paska
     */
    public void start(){
        ustawGUI();
    }

    /**
     * Metoda odpowiadajaca za dodanie labeli wyniku, ¿ycia i czasu do panelu paska wyniku
     */
    private void ustawGUI(){
        add(labelWynik);
        add(labelZycie);
        add(labelCzas);
        init=true;
    }

    /**
     * Metoda obs³uguj¹ca zdarzenia. Zmniejsza czas pozosta³y do koñca
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (init) {
            licznik += 10;
            if ((licznik % 1000) == 0) {
                czas--;
                labelCzas.setText("Czas: " + czas);
            }
        }
    }
}
