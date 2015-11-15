import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 29.10.2015.
 */
public class pasekWyniku extends JPanel implements ActionListener{

    //Dimension wymiar = new Dimension(50,200);
    private int zycie;
    private int wynik;
    private int czas ;
    private int licznik;
    private Timer timer;
    private Boolean init=false;
    private Color kolor_;

    private JLabel labelZycie;
    private JLabel labelWynik;
    private JLabel labelCzas;

    public pasekWyniku(Color col,Data config){
        kolor_ = col;
        this.setBackground(kolor_);
        setLayout(new FlowLayout());

        zycie = config.PasekWyniku_const_zycie;
        czas = config.PasekWyniku_const_czas;
        licznik=0;
        wynik=0;

        labelZycie= new JLabel("Zycie: "+ zycie);
        labelWynik= new JLabel("Wynik: "+ wynik);
        labelCzas= new JLabel("Czas: "+ czas);
        this.setOpaque(true);
    }

    public void start(){
        ustawGUI();
    }
    private void ustawGUI(){
        add(labelWynik);
        add(labelZycie);
        add(labelCzas);
        init=true;
    }

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
