import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 29.10.2015.
 */
public class pasekWyniku extends JPanel implements ActionListener{

    //Dimension wymiar = new Dimension(50,200);
    private int zycie = 5;
    private int wynik = 0;
    private int czas = 100;
    private int licznik=0;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        licznik+=10;
        if((licznik%1000)==0){
            czas--;
            labelCzas.setText("Czas: "+czas);
        }
    }
}
