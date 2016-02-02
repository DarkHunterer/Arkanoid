import org.json.simple.JSONObject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Główna klasa, w której umieszczane komponenty panelGry i pasekWyniku
 */

public class OknoGlowne extends JFrame implements ActionListener, KeyListener, ComponentListener {
    //Wszystko to idzie do konfigu
    /**
     * Zmienna przechowująca obiekt Data z konfiguracją
     */
    private Data config;
    /**
     * Zmienna odpowiadająca za kolor paska wyniku
     */
    private Color kolor_pasekWyniku;
    /**
     * Zmienna odpowiadająca za kolor Panelu Gry
     */
    private Color kolor_panelGry;
    /**
     * Zmienna odpowiadająca za kolor tła
     */
    private Color kolor_background;
    /**
     * Zmienna odpowiadająca za szerokość ekranu
     */
    private int width;
    /**
     * Zmienna odpowiadająca za wysokość ekranu
     */
    private int heigth;

    //   private Image dbImage;
    // private Graphics dbGfx;
    /**
     * Zmienna odpowiadająca za komendę wyłączenia zegara
     */
    private String string_command_timer_off;
    /**
     * Zmienna odpowiadająca za komendę włączeniaa zegara
     */
    private String string_command_timer_on;
    /**
     * Zmienna odpowiadająca za komendę wyłączenia gry
     */
    private String string_command_exit;
    /**
     * Zmienna odpowiadająca za włączenie okna o autorach
     */
    private String string_command_authors;
    /**
     * Zmienna odpowiadająca za włączenie okna pomocy
     */
    private String string_command_help;
    /**
     * Zmienna odpowiadająća za start gry
     */
    private String string_command_start;
    /**
     * Zmienna odpowiadająca za włączenie ustawień
     */
    private String string_command_settings;
    /**
     * Zmienna odpowiadająca za włączenie highscore
     */
    private String string_command_bestScore;
    /**
     * Zmienna odpowiadająca za tytuł menu pomocy
     */
    private String string_menuPomoc_title;
    /**
     * Zmienna odpowiadająca za napis Start
     */
    private String string_start;
    /**
     * Zmienna odpowiadająca za napis Koniec
     */
    private String string_end;
    /**
     * Zmienna odpowiadająca za napis Najlepsze Wyniki
     */
    private String string_bestScore;
    /**
     * Zmienna odpowiadająca za napis Ustawienia
     */
    private String string_config;
    /**
     * Zmienna odpowiadająca za napis Zasady Gry
     */
    private String string_rules;
    /**
     * Zmienna odpowiadająca za napis O autorach
     */
    private String string_authors;
    /**
     * Zmienna odpowiadająca za napis Menu
     */
    private String string_menu;
    /**
     * Zmienna odpowiadająća za napis Pasek Menu
     */
    private String string_menu_title;
    /**
     * Zmienna odpowiadająca za napis Pomoc
     */
    private String string_help_message;

    //
    /**
     * Przechowuje IP serwera
     */
    private String hostname;
    /**
     * Numer portu wykorzystywanego do komunikacji
     */
    private int port = 4455;
    /**
     * Gniazdo klienta
     */
    Socket socketClient;
    // /**
    //  * Pole przehcowujące Frame ustawień
    //  */
    // private SettingsFrame settFrame;

    /**
     * Pole przechowujące Frame Najlepszych wyników
     */
    private BestScoreFrame scoreFrame;
    /**
     * Pole przechowujące pasek wyniku
     */
    private pasekWyniku pasekWyniku_;
    /**
     * Pole przehcowujące panel gry
     */
    private panelGry panelgry_;
    /**
     * Zmienna odpowiadająca za opóźnienie zegara
     */
    private int DELAY;
    /**
     * Pole przechowujące obiekt klasy Timer
     */
    private Timer timerGlowny;
    /**
     * Zmienna wykorzystywana do zastosowania pauzy
     */
    private Boolean pauza;
    /**
     * Zmienna informująca czy trwa gra
     */
    private Boolean graTrwa;
    ///**
    // *
    // */
    // private Boolean init = false;
    /**
     * Zmienna wykorzystywana do umiejscowienia paska wyniku
     */
    private long weightx_pasek_wyniku;
    /**
     * Zmienna wykorzystywana do umiejscowienia paska wyniku
     */
    private double weighty_pasek_wyniku;
    /**
     * Zmienna wykorzystywana do umiejscowienia paska wyniku
     */
    private int gridx_pasek_wyniku;
    /**
     * Zmienna wykorzystywana do umiejscowienia paska wyniku
     */
    private int gridy_pasek_wyniku;
    /**
     * Zmienna wykorzystywana do umiejscowienia panelu gry
     */
    private int gridx_panel_gry;
    /**
     * Zmienna wykorzystywana do umiejscowienia panelu gry
     */
    private int gridy_panel_gry;
    /**
     * Zmienna wykorzystywana do umiejscowienia panelu gry
     */
    private double weighty_panel_gry;
    /**
     * Przechowuje dane o autorach
     */
    private String string_authors_data;
    /**
     * HashMap wykorzystywana do przehcowywania Najlepszych wyników
     */
    Map<String, Long> highScore = new HashMap<String, Long>();

    /**
     * Konstruktor okna głównego
     * pobiera i wczytuje konfiguracfje
     * tworzy interfejs graficzny
     */
    public OknoGlowne() {
        //  super();
        sciagnij_config();
        wczytaj_config();
        dodajElementy();
        dodajMenu();
        dodajGUI();

        ///wczytuje bestscore
        try {
            org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
            Object obj = parser.parse(new FileReader("HighScore.txt"));

            JSONObject jsonObjMain = (JSONObject) obj;
            highScore.putAll((Map) jsonObjMain.get("HighScore"));
            System.out.println("Najlepsze wyniki: " + highScore);
        } catch (Exception ex) {
            System.out.println("Zlapano wyjatek highscore: " + ex.toString());
        }
        ///
    }

    /**
     * Main okna głównego.
     * Tworzy obiekt okna i pojawia go na ekranie
     */
    public static void main(String[] args) {
        OknoGlowne okno = new OknoGlowne();
        okno.setVisible(true);
        //  System.out.println(Color.darkGray.getRGB()+" "+Color.PINK.getRGB()+" "+Color.BLUE.getRGB());
        //okno.panelgry_ = null;
        //  Frame f = new Frame();
        //  f.setPreferredSize(new Dimension(500,500));
        //  f.pack();
        //  f.setVisible(true);
        // okno.panelgry_.setVisible(false);
    }

    /**
     * Metoda wczytująca konfigurację okna głównego
     */
    private void wczytaj_config() {
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
        string_command_exit = config.OknoGlowne_command_exit;
        string_command_help = config.OknoGlowne_command_help;
        string_command_start = config.OknoGlowne_command_start;
        string_command_settings = config.OknoGlowne_command_settings;
        string_command_bestScore = config.OknoGlowne_command_bestScore;
        string_menuPomoc_title = config.OknoGlowne_string_menuPomoc_title;
        string_start = config.OknoGlowne_string_start;
        string_end = config.OknoGlowne_string_end;
        string_bestScore = config.OknoGlowne_string_bestScore;
        string_config = config.OknoGlowne_string_config;
        string_rules = config.OknoGlowne_string_rules;
        string_authors = config.OknoGlowne_string_authors;
        string_menu = config.OknoGlowne_string_menu;
        string_menu_title = config.OknoGlowne_string_menu_title;
        string_help_message = config.OknoGlowne_string_help_message;
        weightx_pasek_wyniku = config.OknoGlowne_weightx_pasek_wyniku;
        weighty_pasek_wyniku = config.OknoGlowne_weighty_pasek_wyniku;
        gridx_pasek_wyniku = config.OknoGlowne_gridx_pasek_wyniku;
        gridy_pasek_wyniku = config.OknoGlowne_gridy_pasek_wyniku;
        gridx_panel_gry = config.OknoGlowne_gridx_panel_gry;
        gridy_panel_gry = config.OknoGlowne_gridy_panel_gry;
        weighty_panel_gry = config.OknoGlowne_weighty_panel_gry;
        string_authors_data = config.OknoGlowne_string_authors_data;
        pauza = config.OknoGlowne_pauza;
        graTrwa = config.OknoGlowne_graTrwa;
        port = config.OknoGlowne_port;

    }

    /**
     * Metoda pobierajaca pasek wyniku
     * @return pasek wyniku
     */
    public pasekWyniku getPasekWyniku_() {
        return pasekWyniku_;
    }

    /**
     * Metoda tworząca obiekty panelu gry i pasku wyniku oraz dodająca je do głównego okna na GridBackLayout'cie
     */
    private void dodajElementy() {
        pasekWyniku_ = new pasekWyniku(config);
        panelgry_ = new panelGry(config, this);

        Dimension rozmiar_okna = new Dimension(width, heigth);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(rozmiar_okna);
        this.getContentPane().setBackground(kolor_background);

        timerGlowny = new Timer(DELAY, this);
        addKeyListener(this);
        timerGlowny.setActionCommand(string_command_timer_off);
        this.addComponentListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        // c.weightx=1;
        c.weightx = weightx_pasek_wyniku;
        //c.weighty=0.01;
        c.weighty = weighty_pasek_wyniku;
        // c.gridx=0;
        c.gridx = (int) gridx_pasek_wyniku;
        // c.gridy=0;
        c.gridy = (int) gridy_pasek_wyniku;

        this.add(pasekWyniku_, c);
        //  c.gridx=0;
        c.gridx = gridx_panel_gry;
        // c.gridy=1;
        c.gridy = gridy_panel_gry;
        // c.weighty=0.95;
        c.weighty = weighty_panel_gry;
        add(panelgry_, c);
    }

    /**
     * Metoda uruchamiająca logikę gry
     */
    private void zacznijGre() {
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
    private void dodajGUI() {
        this.pack();
    }

    /**
     * Metoda dodajaca pasek menu do głównego okna
     */

    private void dodajMenu() {

        MenuBar mbar;
        Menu menu;
        Menu menuPomoc = new Menu(string_menuPomoc_title);

        MenuItem mStart = new MenuItem(string_start);
        MenuItem mKoniec = new MenuItem(string_end);
        MenuItem mbestScore = new MenuItem(string_bestScore);
        //  MenuItem mUstawienia = new MenuItem(string_config);
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
        // menu.add(mUstawienia);
        menu.add(mKoniec);
        menuPomoc.add(mPomoc);
        menuPomoc.add(mAutorzy);
        mbar.setName(string_menu_title);

        mKoniec.setActionCommand(string_command_exit);
        mAutorzy.setActionCommand(string_command_authors);
        // mUstawienia.setActionCommand(string_command_settings);
        mPomoc.setActionCommand(string_command_help);
        mStart.setActionCommand(string_command_start);
        mbestScore.setActionCommand(string_command_bestScore);

        mKoniec.addActionListener(this);
        mAutorzy.addActionListener(this);
        mPomoc.addActionListener(this);
        mStart.addActionListener(this);
        //  mUstawienia.addActionListener(this);
        mbestScore.addActionListener(this);
    }

    /**
     * Metoda odpowiadajaca za przechwycenie puszczenia klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        panelgry_.keyReleased(e);
    }

    /**
     * Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metoda odpowiadajaca za przechwycenie wcisniecia klawisza
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        panelgry_.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (!pauza) {
                pauza = true;
                //    getMenuBar().getMenu(0).setEnabled(pauza);
                panelgry_.wlaczPauze();
                System.out.println("Pauza wlaczona");
            } else {
                pauza = false;
                //   getMenuBar().getMenu(0).setEnabled(pauza);
                panelgry_.wylaczPauze();
                System.out.println("Pauza wylaczona");
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            panelgry_.ukryta_pauza_wylacz();
        }
    }

    /**
     * Metoda odpowiadajaca za obsluge zdarzen w obiekcie
     *
     * @param e Obiekt typu KeyEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graTrwa) {
            // panelgry_.actionPerformed(e);
            pasekWyniku_.actionPerformed(e);
            repaint();

        }
        if (e.getActionCommand().equals(string_command_exit)) {
            //this.dispose(); to dziala ciekawie
            System.exit(1);
        } else if (e.getActionCommand().equals(string_command_help)) {
            JOptionPane.showMessageDialog(getParent(), string_help_message);
        } else if (e.getActionCommand().equals(string_command_authors)) {
            //      JOptionPane.showMessageDialog(getParent(), "Autorzy gry:\n-Daniel Rękawek\n-Konrad Jędrzejczak");
            JOptionPane.showMessageDialog(getParent(), string_authors_data);
        } else if (e.getActionCommand().equals(string_command_start)) {
            zacznijGre();
        } else if (e.getActionCommand().equals(string_command_bestScore)) {
            scoreFrame = new BestScoreFrame(getWidth() / 2, getHeight() / 2, this);
            scoreFrame.rysuj();
            scoreFrame.wczytajZPliku();
            scoreFrame.dodajElementy();
        } else if (e.getActionCommand().equals(string_command_settings)) {
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
            //settFrame = new SettingsFrame(getWidth()/2,getHeight()/2,this);
        }
    }

    /**
     * metoda do pobrania Frame z Najlepszymi wynikami
     * @return Frame z wynikami
     */
    public BestScoreFrame getScoreFrame() {
        return scoreFrame;
    }

    /**
     * Metoda odpowiadajaca za obsluge zdarzenia skalowania okna
     *
     * @param e Obiekt typu ComponentEvent
     */
    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println("Komponent resized");
        panelgry_.skaluj();
    }

    /**
     * Metoda odpowiadajaca za obsluge zdarzenia przesuniecia okna
     *
     * @param e Obiekt typu ComponentEvent
     */
    @Override
    public void componentMoved(ComponentEvent e) {
        System.out.println("Komponent moved");
    }

    /**
     * Metoda uaktywniająca się w momencie pokazania okna
     * @param e Obiekt typu ComponentEvent
     */
    @Override
    public void componentShown(ComponentEvent e) {

    }

    /**
     * Metoda uaktywniająca się w momencie schowania okna
     * @param e Obiekt typu ComponentEvent
     */
    @Override
    public void componentHidden(ComponentEvent e) {

    }

    /**
     * Metoda do pobierania map z serwera
     */
    public void odbierzMapy() {
        try {
            send_command("Gimme maps nigga!");
            int number_of_files;
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            number_of_files = Integer.parseInt(stdIn.readLine());
            String filename[] = new String[number_of_files];

            for (int i = 0; i < number_of_files; i++)
                filename[i] = stdIn.readLine();
            System.out.println("Mapy: " + filename.toString());
            for (int i = 0; i < number_of_files; i++) {
                send_command("Daj " + filename[i]);
                readFileResponse();
            }

        } catch (UnknownHostException ex) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException ex) {
            System.err.println("Cannot establish connection. Server may not be up. " + ex.toString());
        }
    }

    /**
     * Metoda pobierająca konfigurację z serwera
     */
    public void odbierzConfig() {
        try {
            send_command("Gimme config nigga!");
            readFileResponse();
        } catch (UnknownHostException ex) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException ex) {
            System.err.println("Cannot establish connection. Server may not be up. " + ex.toString());
        }
    }

    /**
     * Metoda pobierająca listę najlepszych wyników z serwera
     */
    public void odbierzHighscore() {
        try {
            send_command("Gimme highscore nigga!");
            readFileResponse();
        } catch (UnknownHostException ex) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException ex) {
            System.err.println("Cannot establish connection. Server may not be up. " + ex.toString());
        }
    }

    /**
     * metoda zapisująca odebrany strumien danych plików
     * @throws IOException
     */
    public void readFileResponse() throws IOException {
        String filename;
        // int number_of_files;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        // number_of_files=Integer.parseInt(stdIn.readLine());
        //filename = new String[number_of_files];
        // for (int i=0;i<number_of_files;i++) {
        // filename[i] = stdIn.readLine();
        filename = stdIn.readLine();
        byte[] contents = new byte[10000];
        //Initialize the FileOutputStream to the output file's full path.
        // FileOutputStream fos = new FileOutputStream(filename[i]);
        FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = socketClient.getInputStream();
        //No of bytes read in one read() call
        int bytesRead = 0;
        while ((bytesRead = is.read(contents)) != -1) {
            bos.write(contents, 0, bytesRead);
            if (contents.equals('}'))
                break;
        }
        bos.flush();
        bos.close();
        System.out.println("Odebralem plik " + filename);
        //}
        socketClient.close();
        System.out.println("File saved successfully!");
    }

    /**
     * Metoda odczytująca odpowiedz serwera
     * @throws IOException
     */
    public void readStringResponse() throws IOException {
        String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

        System.out.println("Response from server:");
        while ((userInput = stdIn.readLine()) != null) {
            System.out.println(userInput);
        }
        socketClient.shutdownInput();
        stdIn.close();
        socketClient.close();
    }

    /**
     * Metoda wysylajaca komendę do serwera
     * @param command komenda
     */
    public void send_command(String command) {
        try {
            System.out.println("Attempting to connect to " + hostname + ":" + port);
            socketClient = new Socket(hostname, port);
            System.out.println("Connection Established");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            writer.write(command);
            writer.flush();
            socketClient.shutdownOutput();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Metoda mająca na celu sciągnięcie konfiguracji gry.
     * W tym celu łączymy się z serwerem, jeżeli połączenie nie powiedzie się, gra się wyłącza.
     */
    public void sciagnij_config() {
        hostname = (String) JOptionPane.showInputDialog(
                this,
                "Aby uruchomić grę, należy nawiązać połączeznie z serwerem\n" +
                        "W przeciwnym wypadku gra nie uruchomi się",
                "Podaj IP serwera",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        );
        System.out.println("IP TO: " + hostname);
        try {
            socketClient = new Socket(hostname, port);
            if (socketClient.isConnected()) {
                System.out.println("Udalo sie nawiazac polaczenie z serwerem");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println("Nie udalo sie nawiazac polaczenie z serwerem");
            JOptionPane.showMessageDialog(null, "Nie udalo sie nawiazac polaczenia", "Serwer error: " + "Polaczenie z serwerem", JOptionPane.INFORMATION_MESSAGE);
            System.exit(2);
        }
        odbierzConfig();
        odbierzMapy();
        odbierzHighscore();
    }

    /**
     * metoda odpowiedzialna za okno Najlepszych wyników
     */
    public class BestScoreFrame extends JFrame {

        Frame parentFrame_;

        public BestScoreFrame(int width, int heigth, Frame parentFrame) {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(width, heigth));
            parentFrame_ = parentFrame;
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    parentFrame.setEnabled(true);
                    super.windowClosing(e);
                }
            });
        }

        /**
         * Metoda odpowiedzialna za rysowanie ramki wyników
         */
        public void rysuj() {
            parentFrame_.setEnabled(false);
            setVisible(true);
            pack();
        }

        /**
         * metoda dodajaca elementy do listy najlepszych wynikow
         */
        private void dodajElementy() {
            //  this.setLayout(new BorderLayout());
            String[] columnNames = {"Nick", "Wynik"};
            Object[][] data = new Object[highScore.size()][2];

            int i = 0;
            for (Map.Entry<String, Long> entr : highScore.entrySet()) {
                data[i][0] = entr.getKey();
                int temp = entr.getValue().intValue();
                data[i][1] = temp;
                i++;
            }
            JTable table = new JTable(data, columnNames);
            table.setAutoCreateRowSorter(true);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            add(scrollPane);
        }

        /**
         * metoda wcztujaca najlepsdze wyniki z pliku
         */
        private void wczytajZPliku() {
            try {
                org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
                Object obj = parser.parse(new FileReader("HighScore.txt"));
                JSONObject jsonObjMain = (JSONObject) obj;
                highScore.putAll((Map<String, Long>) jsonObjMain.get("HighScore"));
                System.out.println("Najlepsze wyniki: " + highScore);
            } catch (Exception ex) {
                System.out.println("Zlapano wyjatek highscore : " + ex.toString());
            }
        }
    }
}
