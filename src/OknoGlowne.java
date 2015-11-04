import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OknoGlowne extends JFrame implements ActionListener, KeyListener,ComponentListener  {
    pasekWyniku pasekWyniku_ = new pasekWyniku(Color.cyan);
    panelGry panelgry_ = new panelGry(Color.white);
    private final int DELAY = 10;
    private Timer timer;

    public OknoGlowne(){
        super();
        dodajGUI();
        dodajElementy();
    }
    private  void dodajElementy(){
        timer = new Timer(DELAY,this);
        addKeyListener(this);
        timer.start();
        panelgry_.addComponentListener(this);
    }

    private void dodajGUI(){
        Dimension rozmiar_okna = new Dimension(500,500);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        c.weightx=1;
        c.weighty=0.01;
        c.gridx=0;
        c.gridy=0;
        this.add(pasekWyniku_,c);

        c.gridx=0;
        c.gridy=1;
        c.weighty=0.95;
        add(panelgry_, c);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(rozmiar_okna);
        this.getContentPane().setBackground(Color.BLUE);
        this.pack();

    }
    public static void main(String [] args){
        OknoGlowne okno = new OknoGlowne();
        okno.setVisible(true);
        okno.panelgry_.start();
    }


        @Override
        public void keyReleased(KeyEvent e) {
            panelgry_.keyReleased(e);
        }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
        public void keyPressed(KeyEvent e) {
        panelgry_.keyPressed(e);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
       // System.out.println("Szer okna to: "+getWidth() +" a wys okna: "+ getHeight());
       // System.out.println("Szer panelu gry to: "+panelgry_.getWidth() +" a wys panelu gry to: "+ panelgry_.getHeight());
         panelgry_.actionPerformed(e);
         pasekWyniku_.actionPerformed(e);
         repaint();
     }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {
        panelgry_.skaluj();
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}