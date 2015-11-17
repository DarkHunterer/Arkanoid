import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Główna klasa, w której umieszczane komponenty panelGry i pasekWyniku
 */

public class OknoGlowne extends JFrame implements ActionListener, KeyListener,ComponentListener{
    //Wszystko to idzie do konfigu
    private  Data config;
    private Color kolor_pasekWyniku;
    private Color kolor_panelGry;
    private Color kolor_background;
    private int width;
    private int heigth;

    private String string_command_timer_off;
    private String string_command_timer_on;
    private String string_command_exit;
    private String string_command_authors;
    private String string_command_help;
    private String string_command_start;
    private String string_command_settings;

    private String string_menuPomoc_title;
    private String string_start;
    private String string_end;
    private String string_bestScore;
    private String string_config;
    private String string_rules;
    private String string_authors;
    private String string_menu;
    private String string_menu_title;
    private String string_help_message;

    //
    private pasekWyniku pasekWyniku_;
    private panelGry panelgry_;
    private int DELAY;
    private Timer timerGlowny;
    private Boolean pauza = false;
    private Boolean graTrwa = false;
    private Boolean init = false;

    /**
     * Konstruktor okna głównego
     */
    public OknoGlowne(){
      //  super();
        wczytaj_config();
        dodajElementy();
        dodajMenu();
        dodajGUI();
    }

    /**
     * Main okna głównego. Tworzy obiekt okna i pojawia go na ekranie
     *
     */
    public static void main(String [] args){
        OknoGlowne okno = new OknoGlowne();
        okno.setVisible(true);
        System.out.println(Color.darkGray.getRGB()+" "+Color.PINK.getRGB()+" "+Color.BLUE.getRGB());
       // okno.panelgry_.setVisible(false);
    }

    /**
     * Metoda wczytująca konfiguracje
     */
    private void wczytaj_config(){
        config = new Data();
        kolor_background = config.OknoGlowne_kolor_background;
        kolor_pasekWyniku = config.OknoGlowne_kolor_pasekWyniku;
        kolor_panelGry = config.OknoGlowne_kolor_panelGry;
        width = config.OknoGlowne_width;
        heigth = config.OknoGlowne_heigth;
        DELAY = config.OknoGlowne_Delay;
        string_command_timer_off = config.OknoGlowne_command_timer_off;
        string_command_timer_on = config.OknoGlowne_command_timer_on;
        string_command_authors = config.OknoGlowne_command_authors;
        string_command_exit =config.OknoGlowne_command_exit;
        string_command_help =config.OknoGlowne_command_help;
        string_command_start =config.OknoGlowne_command_start;
        string_command_settings = config.OknoGlowne_command_settings;
        string_menuPomoc_title=config.OknoGlowne_string_menuPomoc_title;
        string_start=config.OknoGlowne_string_start;
        string_end=config.OknoGlowne_string_end;
        string_bestScore=config.OknoGlowne_string_bestScore;
        string_config=config.OknoGlowne_string_config;
        string_rules=config.OknoGlowne_string_rules;
        string_authors=config.OknoGlowne_string_authors;
        string_menu=config.OknoGlowne_string_menu;
        string_menu_title=config.OknoGlowne_string_menu_title;
        string_help_message = config.OknoGlowne_string_help_message;
    }

    /**
     * Metoda tworząca obiekty panelu gry i pasku wyniku oraz dodająca je do głównego okna na GridBackLayout'cie
     */
    private  void dodajElementy(){
        panelgry_ = new panelGry(config);
        pasekWyniku_ = new pasekWyniku(kolor_pasekWyniku,config);

        Dimension rozmiar_okna = new Dimension(width,heigth);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(rozmiar_okna);
        this.getContentPane().setBackground(kolor_background);

        timerGlowny = new Timer(DELAY,this);
        addKeyListener(this);
        timerGlowny.setActionCommand(string_command_timer_off);
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

    /**
     * Metoda uruchamiająca logikę gry
     */
    private void zacznijGre(){
        //if(init) {
            pasekWyniku_.start();
            panelgry_.setVisible(true);
            graTrwa = true;
            panelgry_.start();
            timerGlowny.setActionCommand(string_command_timer_on);
      // }
       // else
       //     JOptionPane.showMessageDialog(getParent(),"Nie wczytano ustawien");
    }

    /**
     * Metoda rysujaca okno na ekranie
     */
    private void dodajGUI(){

        this.pack();
    }

    /**
     * Metoda dodajaca pasek menu do głównego okna
     */

    private void dodajMenu(){

        MenuBar mbar;
        Menu menu;
        Menu menuPomoc = new Menu(string_menuPomoc_title);

        MenuItem mStart = new MenuItem(string_start);
        MenuItem mKoniec = new MenuItem(string_end);
        MenuItem bestScore = new MenuItem(string_bestScore);
        MenuItem mUstawienia = new MenuItem(string_config);
        MenuItem mPomoc = new MenuItem(string_rules);
        MenuItem mAutorzy = new MenuItem(string_authors);

        mbar = new MenuBar();
        menu = new Menu(string_menu);
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
        mbar.setName(string_menu_title);

        mKoniec.setActionCommand(string_command_exit);
        mAutorzy.setActionCommand(string_command_authors);
        mUstawienia.setActionCommand(string_command_settings);
        mPomoc.setActionCommand(string_command_help);
        mStart.setActionCommand(string_command_start);

        mKoniec.addActionListener(this);
        mAutorzy.addActionListener(this);
        mPomoc.addActionListener(this);
        mStart.addActionListener(this);
        mUstawienia.addActionListener(this);
    }

    /**
     *  Metoda odpowiadajaca za przechwycenie puszczenia klawisza
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
            panelgry_.keyReleased(e);
        }

    /**
     *  Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     *  Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     * @param e Obiekt typu KeyEvent
     */
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

    /**
     *
     *  Metoda odpowiadajaca za obsluge zdarzen w obiekcie
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(graTrwa) {
           panelgry_.actionPerformed(e);
           pasekWyniku_.actionPerformed(e);
       }
        if (e.getActionCommand().equals(string_command_exit)){
            //this.dispose(); to dziala ciekawie
            System.exit(1);
        }
        else if(e.getActionCommand().equals(string_command_help))
        {
            JOptionPane.showMessageDialog(getParent(),string_help_message);
        }
        else if(e.getActionCommand().equals(string_command_authors))
        {
            JOptionPane.showMessageDialog(getParent(), "Autorzy gry:\n-Daniel R�kawek\n-Konrad J�drzejczak");
        }
        else if(e.getActionCommand().equals(string_command_start))
        {
            zacznijGre();
        }
        else if (e.getActionCommand().equals(string_command_settings)){
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                config.zapisz_config();
                config.wczytaj_config(chooser.getSelectedFile().toString());
                wczytaj_config();
                /*dodajElementy();
                dodajGUI();*/
                init = true;
            }
            else {
                JOptionPane.showMessageDialog(getParent(),"Blad otwarcia pliku");
            }
        }
           repaint();
     }

    /**
     * Metoda odpowiadajaca za obsluge zdarzenia skalowania okna
     * @param e Obiekt typu ComponentEvent
     */
    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println("Komponent resized");
        panelgry_.skaluj();
    }

    /**
     * Metoda odpowiadajaca za obsluge zdarzenia przesuniecia okna
     * @param e Obiekt typu ComponentEvent
     */
    @Override
    public void componentMoved(ComponentEvent e) {
        System.out.println("Komponent moved");
    }

    /**
     *
     * @param e Obiekt typu ComponentEvent
     */
    @Override
    public void componentShown(ComponentEvent e) {

    }

    /**
     *
     * @param e Obiekt typu ComponentEvent
     */
    @Override
    public void componentHidden(ComponentEvent e) {

    }

}