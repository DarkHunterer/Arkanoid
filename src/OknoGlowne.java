import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OknoGlowne extends JFrame implements ActionListener, KeyListener,ComponentListener{
    pasekWyniku pasekWyniku_ = new pasekWyniku(Color.red);
    panelGry panelgry_ = new panelGry(Color.red);
    private final int DELAY = 10;
    private Timer timerGlowny;
    private Boolean pauza = false;
    private Boolean graTrwa = false;

    public OknoGlowne(){
      //  super();
        dodajMenu();
        dodajElementy();
        dodajGUI();
    }
    private  void dodajElementy(){
        Dimension rozmiar_okna = new Dimension(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(rozmiar_okna);
        this.getContentPane().setBackground(Color.BLUE);

        timerGlowny = new Timer(DELAY,this);
        addKeyListener(this);
        timerGlowny.setActionCommand("TIMER_MAIN_TICK_OFF");
        timerGlowny.start();
        this.addComponentListener(this);

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
    }
    private void zacznijGre(){
        pasekWyniku_.start();
        panelgry_.setVisible(true);
        graTrwa = true;
        panelgry_.start();
        timerGlowny.setActionCommand("TIMER_MAIN_TICK");
    }
    private void dodajGUI(){

        this.pack();
    }
    public static void main(String [] args){
        OknoGlowne okno = new OknoGlowne();
        okno.setVisible(true);
        okno.panelgry_.setVisible(false);
    }

    private void dodajMenu(){

        MenuBar mbar;
        Menu menu;
        Menu menuPomoc = new Menu("Pomoc");

        MenuItem mStart = new MenuItem("Start gry");
        MenuItem mKoniec = new MenuItem("Koniec gry");
        MenuItem bestScore = new MenuItem("Najlepsze wyniki");
        MenuItem mUstawienia = new MenuItem("Ustawienia");
        MenuItem mPomoc = new MenuItem("Zasady gry");
        MenuItem mAutorzy = new MenuItem("O autorach");

        mbar = new MenuBar();
        menu = new Menu("Menu");
        setMenuBar(mbar);
        mbar.add(menu);

        mbar.setHelpMenu(menuPomoc);
        mbar.add(menuPomoc);
        menu.add(mStart);
        menu.add(bestScore);
        menu.add(mUstawienia);
        menu.add(mKoniec);
        menuPomoc.add(mPomoc);
        menuPomoc.add(mAutorzy);
        mbar.setName("Pasek Menu");

        mKoniec.addActionListener(this);
        mAutorzy.addActionListener(this);
        mPomoc.addActionListener(this);
        mStart.addActionListener(this);

        mKoniec.setActionCommand("EXIT");
        mAutorzy.setActionCommand("AUTORZY");
        mPomoc.setActionCommand("POMOC");
        mStart.setActionCommand("START");
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
        if (e.getKeyCode()==KeyEvent.VK_P) {
            System.out.println("Pauza wcisnieta");
            if(!pauza) {
                pauza = true;
                getMenuBar().getMenu(0).setEnabled(true);
            }
            else {
                pauza = false;
                getMenuBar().getMenu(0).setEnabled(false);
            }
            panelgry_.setPauza(pauza);
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // System.out.println("Szer okna to: "+getWidth() +" a wys okna: "+ getHeight());
       // System.out.println("Szer panelu gry to: "+panelgry_.getWidth() +" a wys panelu gry to: "+ panelgry_.getHeight());

        if(graTrwa) {
           panelgry_.actionPerformed(e);
           pasekWyniku_.actionPerformed(e);
       }
        if (e.getActionCommand().equals("EXIT")){
            this.dispose();
        }
        else if(e.getActionCommand().equals("POMOC"))
        {
            JOptionPane.showMessageDialog(getParent(),"KONRAD DZIWKO NIE SPIJ KURWA");
        }
        else if(e.getActionCommand().equals("AUTORZY"))
        {
            JOptionPane.showMessageDialog(getParent(), "Autorzy gry:\n-Daniel Rêkawek\n-Tu powinien byæ Konrad Jêdrzejczak ALE KURWA NAWET NIE SKOMPILOWAL PROJEKTU!");
        }
        else if(e.getActionCommand().equals("START"))
        {
            zacznijGre();
        }
           repaint();
     }

    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println("Komponent resized");
        panelgry_.skaluj();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        System.out.println("Komponent moved");
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

}