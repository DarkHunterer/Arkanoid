import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 29.10.2015.
 */
public class pasekWyniku extends JPanel{

    //Dimension wymiar = new Dimension(50,200);
    private int zycie = 5;
    private int wynik = 0;
    private int czas = 100;

    private Timer timer;

    private JLabel labelZycie;
    private JLabel labelWynik;
    private JLabel labelCzas;

    public pasekWyniku(Color col){
        this.setBackground(col);
    ustawGUI();
    }

    private void ustawGUI(){
        setLayout(new FlowLayout());

        labelZycie= new JLabel("Zycie: "+ zycie);
        labelWynik= new JLabel("Wynik: "+ wynik);
        labelCzas= new JLabel("Czas: "+ czas);

        add(labelWynik);
        add(labelZycie);
        add(labelCzas);
        this.setOpaque(true);

    }
}
