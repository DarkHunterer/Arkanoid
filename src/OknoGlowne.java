import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class OknoGlowne extends JFrame implements ActionListener, KeyListener,ComponentListener{
    //Wszystko to idzie do konfigu
    private  Data config = new Data();
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
    //
    pasekWyniku pasekWyniku_;
    panelGry panelgry_;
    private int DELAY;
    private Timer timerGlowny;
    private Boolean pauza = false;
    private Boolean graTrwa = false;
    private Boolean init = false;

    public OknoGlowne(){
      //  super();
        wczytaj_config();
        dodajElementy();
        dodajMenu();
        dodajGUI();
    }

    private void wczytaj_config(){
        kolor_background = config.OknoGlowne_kolor_background;
        kolor_pasekWyniku = config.OknoGlowne_kolor_pasekWyniku;
        kolor_panelGry = config.OknoGlowne_kolor_panelGry;
        width = config.OknoGlowne_width;
        heigth = config.OknoGlowne_heigth;

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



        DELAY = config.OknoGlowne_Delay;
    }
    private  void dodajElementy(){
        panelgry_ = new panelGry(kolor_panelGry);
        pasekWyniku_ = new pasekWyniku(kolor_pasekWyniku);

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
    private void zacznijGre(){
        if(init) {
            pasekWyniku_.start();
            panelgry_.setVisible(true);
            graTrwa = true;
            panelgry_.start();
            timerGlowny.setActionCommand(string_command_timer_on);
       }
        else
            JOptionPane.showMessageDialog(getParent(),"Nie wczytano ustawien");
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
        if (e.getActionCommand().equals(string_command_exit)){
            //this.dispose(); to dziala ciekawie
            System.exit(1);
        }
        else if(e.getActionCommand().equals(string_command_help))
        {
            JOptionPane.showMessageDialog(getParent(),"Serio...? Pilka zbija klocki.\nSterujesz strzalkami.\nW czym tu potrzeba pomocy?");
        }
        else if(e.getActionCommand().equals(string_command_authors))
        {
            JOptionPane.showMessageDialog(getParent(), "Autorzy gry:\n-Daniel R�kawek\n-Konrad J�drzejczak!");
        }
        else if(e.getActionCommand().equals(string_command_start))
        {
            zacznijGre();
        }
        else if (e.getActionCommand().equals(string_command_settings)){
            System.out.println("Opcje ustawien");
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                config.wczytaj_config(chooser.getSelectedFile().toString());
                System.out.println(chooser.getSelectedFile().toString());
                config.zapisz_config();
                init = true;
            }
            else {
                JOptionPane.showMessageDialog(getParent(),"Blad otwarcia pliku");
            }
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