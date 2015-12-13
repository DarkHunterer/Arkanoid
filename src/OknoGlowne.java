import org.json.simple.JSONObject;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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

    private Image dbImage;
    private Graphics dbGfx;

    private String string_command_timer_off;
    private String string_command_timer_on;
    private String string_command_exit;
    private String string_command_authors;
    private String string_command_help;
    private String string_command_start;
    private String string_command_settings;
    private String string_command_bestScore;

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
    private SettingsFrame settFrame;
    private BestScoreFrame scoreFrame;
    private pasekWyniku pasekWyniku_;
    private panelGry panelgry_;
    private int DELAY;
    private Timer timerGlowny;
    private Boolean pauza = false;
    private Boolean graTrwa = false;
    private Boolean init = false;
    private long weightx_pasek_wyniku;
    private double weighty_pasek_wyniku;
    private int gridx_pasek_wyniku;
    private int gridy_pasek_wyniku;
    private int gridx_panel_gry;
    private int gridy_panel_gry;
    private double weighty_panel_gry;
    private String string_authors_data;

    Map<String,Long> highScore = new HashMap<String,Long>();

    /**
     * Konstruktor okna głównego
     */
    public OknoGlowne(){
      //  super();
        wczytaj_config();
        dodajElementy();
        dodajMenu();
        dodajGUI();

        ///wczytuje bestscore
        try {
            org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
            Object obj = parser.parse(new FileReader("HighScore.txt"));

            JSONObject jsonObjMain = (JSONObject) obj;
            highScore.putAll((Map)jsonObjMain.get("HighScore"));
            System.out.println("Najlepsze wyniki: "+ highScore);
        }
        catch (Exception ex){
            System.out.println("Zlapano wyjatek: "+ ex.toString());
        }
        ///
    }

    /**
     * Main okna głównego. Tworzy obiekt okna i pojawia go na ekranie
     *
     */
    public static void main(String [] args){
        OknoGlowne okno = new OknoGlowne();
        okno.setVisible(true);
        System.out.println(Color.darkGray.getRGB()+" "+Color.PINK.getRGB()+" "+Color.BLUE.getRGB());
        //okno.panelgry_ = null;
      //  Frame f = new Frame();
      //  f.setPreferredSize(new Dimension(500,500));
      //  f.pack();
      //  f.setVisible(true);
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
        string_command_bestScore = config.OknoGlowne_command_bestScore;
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
        weightx_pasek_wyniku=config.OknoGlowne_weightx_pasek_wyniku;
        weighty_pasek_wyniku=config.OknoGlowne_weighty_pasek_wyniku;
        gridx_pasek_wyniku=config.OknoGlowne_gridx_pasek_wyniku;
        gridy_pasek_wyniku=config.OknoGlowne_gridy_pasek_wyniku;
        gridx_panel_gry=config.OknoGlowne_gridx_panel_gry;
        gridy_panel_gry=config.OknoGlowne_gridy_panel_gry;
        weighty_panel_gry=config.OknoGlowne_weighty_panel_gry;
        string_authors_data=config.OknoGlowne_string_authors_data;

    }


    public pasekWyniku getPasekWyniku_() {
        return pasekWyniku_;
    }

    /**
     * Metoda tworząca obiekty panelu gry i pasku wyniku oraz dodająca je do głównego okna na GridBackLayout'cie
     */
    private  void dodajElementy(){
        pasekWyniku_ = new pasekWyniku(config);
        panelgry_ = new panelGry(config,this);

        Dimension rozmiar_okna = new Dimension(width,heigth);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(rozmiar_okna);
        this.getContentPane().setBackground(kolor_background);

        timerGlowny = new Timer(DELAY,this);
        addKeyListener(this);
        timerGlowny.setActionCommand(string_command_timer_off);
        this.addComponentListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
       // c.weightx=1;
        c.weightx=weightx_pasek_wyniku;
        //c.weighty=0.01;
        c.weighty=weighty_pasek_wyniku;
       // c.gridx=0;
        c.gridx=(int) gridx_pasek_wyniku;
       // c.gridy=0;
        c.gridy=(int) gridy_pasek_wyniku;

        this.add(pasekWyniku_,c);
      //  c.gridx=0;
        c.gridx=gridx_panel_gry;
       // c.gridy=1;
        c.gridy=gridy_panel_gry;
       // c.weighty=0.95;
        c.weighty=weighty_panel_gry;
        add(panelgry_, c);
    }

    /**
     * Metoda uruchamiająca logikę gry
     */
    private void zacznijGre(){
        //if(init) {
            pasekWyniku_.start(config);
            //panelgry_.setVisible(true);
            graTrwa = true;
            panelgry_.start();
            timerGlowny.start();
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
        MenuItem mbestScore = new MenuItem(string_bestScore);
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
        menu.add(mbestScore);
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
        mbestScore.setActionCommand(string_command_bestScore);

        mKoniec.addActionListener(this);
        mAutorzy.addActionListener(this);
        mPomoc.addActionListener(this);
        mStart.addActionListener(this);
        mUstawienia.addActionListener(this);
        mbestScore.addActionListener(this);
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
            if(!pauza) {
                pauza = true;
            //    getMenuBar().getMenu(0).setEnabled(pauza);
                panelgry_.wlaczPauze();
                System.out.println("Pauza wlaczona");
            }
            else {
                pauza = false;
             //   getMenuBar().getMenu(0).setEnabled(pauza);
                panelgry_.wylaczPauze();
                System.out.println("Pauza wylaczona");
            }
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
          // panelgry_.actionPerformed(e);
           pasekWyniku_.actionPerformed(e);
            repaint();

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
      //      JOptionPane.showMessageDialog(getParent(), "Autorzy gry:\n-Daniel Rękawek\n-Konrad Jędrzejczak");
            JOptionPane.showMessageDialog(getParent(), string_authors_data);
        }
        else if(e.getActionCommand().equals(string_command_start))
        {
            zacznijGre();
        }
        else if(e.getActionCommand().equals(string_command_bestScore)){
             scoreFrame = new BestScoreFrame(getWidth()/2,getHeight()/2,this);
            scoreFrame.rysuj();
            scoreFrame.wczytajZPliku();
            scoreFrame.dodajElementy();
        }
        else if (e.getActionCommand().equals(string_command_settings)){
         /*   JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(getParent());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                config.zapisz_config();
                config.wczytaj_config(chooser.getSelectedFile().toString());
                wczytaj_config();
                /*dodajElementy();
                dodajGUI();*//*
                init = true;
            }
            else {
                JOptionPane.showMessageDialog(getParent(),"Blad otwarcia pliku");
            }*/
          settFrame = new SettingsFrame(getWidth()/2,getHeight()/2,this);
        }
     }


    public BestScoreFrame getScoreFrame() {
        return scoreFrame;
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

    private class SettingsFrame extends JFrame implements ActionListener{
     //   private String string_accept="Akceptuj";
     //  private String string_ip="Wrowadz tu swoje ip";
        private String string_accept=config.SettingFrame_string_accept;
        private String string_ip=config.SettingFrame_string_ip;
        JButton acceptButton;
        JTextField textField;
        private Inet4Address addressIP;
        public SettingsFrame(int width,int heigth,Frame parentFrame){

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(width,heigth));
            parentFrame.setEnabled(false);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    parentFrame.setEnabled(true);
                    super.windowClosing(e);
                }
            });
            setVisible(true);
            dodajElementy();
            pack();
        }
        private void wczytaj_config(){
            string_accept=config.SettingFrame_string_accept;
            string_ip=config.SettingFrame_string_ip;
        }

       private void dodajElementy(){
           this.setLayout(new GridLayout(4,4));
       //    GridBagConstraints c = new GridBagConstraints();
        //   c.fill = GridBagConstraints.BOTH;
      /*     c.weightx=0.2;
           c.gridx=0;
           c.gridy=0;
           c.weighty=0.8;
           c.weighty=0.2;
           c.gridx=0;
           c.gridy=1;*/
           acceptButton = new JButton(string_accept);
           acceptButton.setSize(new Dimension(getWidth(),getHeight()));
           textField = new JTextField(string_ip);
           textField.setBackground(Color.white);
           textField.setCaretColor(Color.red);
           add(textField);
           add(acceptButton);
           acceptButton.addActionListener(this);
       }
        @Override
        public void actionPerformed(ActionEvent e) {
          //  if(e.equals(acceptButton)){
                try {
                    addressIP = (Inet4Address) Inet4Address.getByName(textField.getText());
                    JOptionPane.showMessageDialog(getParent(), addressIP);
                }catch (UnknownHostException ex){
                    JOptionPane.showMessageDialog(getParent(), ex.getMessage());
           //     }
            }
        }
    }
    public class BestScoreFrame extends JFrame{

        Frame parentFrame_;
        public BestScoreFrame(int width,int heigth,Frame parentFrame){
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(width,heigth));
            parentFrame_ = parentFrame;
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    parentFrame.setEnabled(true);
                    super.windowClosing(e);
                }
            });
        }
        public void rysuj(){
            parentFrame_.setEnabled(false);
            setVisible(true);
            pack();
        }
        private void dodajElementy(){
          //  this.setLayout(new BorderLayout());

            String[] columnNames = {"Nick","Wynik"};
            Object[][] data = new Object[highScore.size()][2];


            int i=0;

            for(Map.Entry<String,Long> entr : highScore.entrySet()){
                data[i][0] = entr.getKey();
                data[i][1] = entr.getValue();
                i++;
            }
            JTable table = new JTable(data,columnNames);
            table.setAutoCreateRowSorter(true);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);

            add(scrollPane);
        }
        public void zapiszDoPliku(){
            try {
                FileWriter writer = new FileWriter("HighScore.txt");
                StringWriter out = new StringWriter();
                Map highScore2 = new HashMap<String,Long>();

                JSONObject objMain = new JSONObject();
                objMain.put("HighScore",highScore2);

                objMain.writeJSONString(out);
                writer.write(out.toString());
                writer.close();
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        private void wczytajZPliku(){try {
            org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
            Object obj = parser.parse(new FileReader("HighScore.txt"));

            JSONObject jsonObjMain = (JSONObject) obj;
            highScore.putAll((Map<String,Long>)jsonObjMain.get("HighScore"));
            System.out.println("Najlepsze wyniki: "+ highScore);
        }
        catch (Exception ex){
            System.out.println("Zlapano wyjatek: "+ ex.toString());
        }
        }
    }
}