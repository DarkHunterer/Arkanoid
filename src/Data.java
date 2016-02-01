import jdk.nashorn.internal.parser.JSONParser;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Klasa odpowiadajaca za konfiguracje
 */

public class Data {
    /**
     * Zmienna odpowiadajaca za opóźnienie zegara
     */
    public int OknoGlowne_Delay = 10;
    /**
     * Zmienna odpowiadająca za szerokosc ekranu
     */
    public int OknoGlowne_width = 500;
    /**
     * Zmienna odpowiadająca za wysokosc ekranu
     */
    public int OknoGlowne_heigth = 500;
    /**
     * Zmienna odpowiadająca za komende wylaczenia zegara
     */
    public String OknoGlowne_command_timer_off;//="TIMER_MAIN_TICK_OFF";
    /**
     * Zmienna odpowiadająca za komende włączenia zegara
     */
    public String OknoGlowne_command_timer_on;//="TIMER_MAIN_TICK";
    /**
     * Zmienna odpowiadajaca za komende wyłączenia gry
     */
    public String OknoGlowne_command_exit;//="EXIT";
    /**
     * Zmienna odpowiadająca za wlaczenia okna o autorach
     */
    public String OknoGlowne_command_authors;//="AUTORZY";
    /**
     * Zmienna odpowiadająca za wlaczenie okna pomocy
     */
    public String OknoGlowne_command_help;//="POMOC";
    /**
     * Zmienna odpowiadająca za start gry
     */
    public String OknoGlowne_command_start;//="START";
    /**
     * Zmienna odpowiadajaca za bestscore
     */
    public String OknoGlowne_command_bestScore;
    /**
     * Zmienna odpowiadająca za wlaczenie ustawień
     */
    public String OknoGlowne_command_settings;//="USTAWIENIA";
    /**
     * Zmienna odpowiadająca za tytuł menu pomocy
     */
    public String OknoGlowne_string_menuPomoc_title;//="Pomoc";
    /**
     * Zmienna odpowiadająca za napis start
     */
    public String OknoGlowne_string_start;//="Start";
    /**
     * Zmienna odpowiadająca za napis koniec
     */
    public String OknoGlowne_string_end;//="Koniec";
    /**
     * Zmienna odpowiadaja za napis najlepsze wyniki
     */
    public String OknoGlowne_string_bestScore;//="Najlepsze wyniki";
    /**
     * Zmienna odpowiadająca za napis ustawienia
     */
    public String OknoGlowne_string_config;//="Ustawienia";
    /**
     * Zmienna odpowiadająca za napis zasady gry
     */
    public String OknoGlowne_string_rules;//="Zasady gry";
    /**
     * Zmienna odpowiadająca za napis o autorach
     */
    public String OknoGlowne_string_authors;//="O autorach";
    /**
     * Zmienna odpowiadająca za napis menu
     */
    public String OknoGlowne_string_menu;//="Menu";
    /**
     * Zmienna odpowiadająca za napis pasek menu
     */
    public String OknoGlowne_string_menu_title;//="Pasek menu";
    /**
     * Zmienna odpowiadająca za napis komunikatu pomocy
     */
    public String OknoGlowne_string_help_message;//="Serio...? Pilka zbija klocki.\nSterujesz strzalkami.\nW czym tu potrzeba pomocy?";
    /**
     * Zmienna odpowiadająca za kolor paska wyniku
     */
    public Color OknoGlowne_kolor_pasekWyniku;// = Color.red;
    /**
     * Zmienna odpowiadająca za kolor panelu gry
     */
    public Color OknoGlowne_kolor_panelGry;// = Color.red;
    /**
     * Zmienna odpowiadająca za kolor tła panelu gry
     */
    public Color OknoGlowne_kolor_background;// = Color.darkGray;
    ///
    /**
     * Zmienna odpowiadajaca za pozostałą ilość żyć
     */
    public int PasekWyniku_const_zycie;
    /**
     * Zmienna odpowiadająca za pozostałą ilość czasu
     */
    public int PasekWyniku_const_czas;
    ///
    /**
     *
     */
    public int PasekWyniku_wynik;
    public int PasekWyniku_licznik;
    public boolean PasekWyniku_init;
    public boolean PasekWyniku_init2;
    public String PasekWyniku_string_zycie;
    public String PasekWyniku_string_wynik;
    public String PasekWyniku_string_czas;
    public String PasekWyniku_string_pauza;
    /**
     * Dwuwymiarowa tablica pozycji klocków. 0 - brak klocka 1-6 zdrowie klocka
     */
    int bricksPos[][];
    ///
    /**
     * Stała do rozmairu paska wyniku
     */
    long OknoGlowne_weightx_pasek_wyniku;
    /**
     *
     */
    double OknoGlowne_weighty_pasek_wyniku;
    /**
     *
     */
    int OknoGlowne_gridx_pasek_wyniku;
    /**
     *
     */
    int OknoGlowne_gridy_pasek_wyniku;

    /**
     *
     */
    double OknoGlowne_weighty_panel_gry;
    /**
     *
     */
    int OknoGlowne_gridx_panel_gry;
    /**
     *
     */
    int OknoGlowne_gridy_panel_gry;
    /**
     *
     */
    String OknoGlowne_string_authors_data;
    /**
     *
     */
    String SettingFrame_string_accept;
    /**
     *
     */
    String SettingFrame_string_ip;

    /**
     *
     */
    String Perk_string_bonus_0;
    String Perk_string_bonus_1;
    String Perk_string_bonus_2;
    String Perk_string_bonus_3;
    String Perk_string_bonus_4;
    String Perk_string_bonus_5;
    String Perk_string_bonus_6;
    String Perk_string_bonus_7;
    String Perk_string_bonus_8;
    String Perk_string_bonus_9;
    String Perk_string_bonus_10;
    String Perk_string_bonus_11;

    String Klocek_zycie_5;
    String Klocek_zycie_4;
    String Klocek_zycie_3;
    String Klocek_zycie_2;
    String Klocek_zycie_1;

    int Pilka_dx;
    int Pilka_dy;
    int pilka_start;
    String pilka_string_imgBall;

    int paletka_predkosc;
    String paletka_string_imgPale;

    boolean panel_gry_init;
    boolean panel_gry_pauza;
    boolean panel_gry_pozostale_klocki;
    String panel_gry_string_imgPanel;

    /**
     * Konstruktor klasy, wczytuje domyslna konfiguracje z pliku.
     */
    Data() {
        wczytaj_config("domyslny_config.json");
    }

    /**
     * Metoda odpowiadajaca za wczytanie konfiguracji z pliku
     *
     * @param sciezka Sciezka do wybranego pliku konfiguracji
     */
    public void wczytaj_config(String sciezka) {
        try {
            org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
            Object obj = parser.parse(new FileReader(sciezka));
            Object objMapa = parser.parse(new FileReader("mapa.json"));

            JSONObject jsonObjMain = (JSONObject) obj;
            JSONObject jsonObjMapa = (JSONObject) objMapa;

            JSONObject jsonObjOknoGlowne = (JSONObject) jsonObjMain.get("OknoGlowne");
            JSONObject jsonObjPasekWyniku = (JSONObject) jsonObjMain.get("PasekWyniku");
            JSONObject jsonObjSettingFrame = (JSONObject) jsonObjMain.get("SettingFrame");
            JSONObject jsonObjPerk = (JSONObject) jsonObjMain.get("Perk");
            JSONObject jsonObjKlocek = (JSONObject) jsonObjMain.get("Klocek");
            JSONObject jsonObjPilka = (JSONObject) jsonObjMain.get("Pilka");
            JSONObject jsonObjPaletka = (JSONObject) jsonObjMain.get("Paletka");
            JSONObject jsonObjPanelGry = (JSONObject) jsonObjMain.get("PanelGry");

            wczytaj_OknoGlowne(jsonObjOknoGlowne);
            wczytaj_PasekWyniku(jsonObjPasekWyniku);
            wczytaj_Mape(jsonObjMapa);
            wczytaj_Setting_Frame(jsonObjSettingFrame);
            wczytaj_Perki(jsonObjPerk);
            wczytaj_Klocki(jsonObjKlocek);
            wczytaj_Pilka(jsonObjPilka);
            wczytaj_Paletka(jsonObjPaletka);
            wczytaj_PanelGry(jsonObjPanelGry);

        } catch (Exception ex) {
            System.out.println("Zlapano wyjatek: " + ex.toString());
        }
    }

    /**
     * Metoda odpowiadajaca za wczytanie konfiguracji okna głównego
     *
     * @param jsonObjOknoGlowne Obiekt typu JSONObject
     */
    private void wczytaj_OknoGlowne(JSONObject jsonObjOknoGlowne) {
        long temp = (long) jsonObjOknoGlowne.get("Delay");
        OknoGlowne_Delay = (int) temp;
        temp = (long) (jsonObjOknoGlowne.get("width"));
        OknoGlowne_width = (int) temp;

        temp = (long) (jsonObjOknoGlowne.get("heigth"));
        OknoGlowne_heigth = (int) temp;
        OknoGlowne_command_timer_off = (String) jsonObjOknoGlowne.get("command_timer_off");
        OknoGlowne_command_timer_on = (String) jsonObjOknoGlowne.get("command_timer_on");
        OknoGlowne_command_exit = (String) jsonObjOknoGlowne.get("command_exit");
        OknoGlowne_command_authors = (String) jsonObjOknoGlowne.get("command_autors");
        OknoGlowne_command_help = (String) jsonObjOknoGlowne.get("command_help");
        OknoGlowne_command_start = (String) jsonObjOknoGlowne.get("command_start");
        OknoGlowne_command_settings = (String) jsonObjOknoGlowne.get("command_settings");
        OknoGlowne_command_bestScore = (String) jsonObjOknoGlowne.get("command_bestscore");

        OknoGlowne_string_menuPomoc_title = (String) jsonObjOknoGlowne.get("string_menuPomoc_title");
        OknoGlowne_string_start = (String) jsonObjOknoGlowne.get("string_start");
        OknoGlowne_string_end = (String) jsonObjOknoGlowne.get("string_end");
        OknoGlowne_string_bestScore = (String) jsonObjOknoGlowne.get("string_bestScore");
        OknoGlowne_string_config = (String) jsonObjOknoGlowne.get("string_config");
        OknoGlowne_string_rules = (String) jsonObjOknoGlowne.get("string_rules");
        OknoGlowne_string_authors = (String) jsonObjOknoGlowne.get("string_authors");
        OknoGlowne_string_menu = (String) jsonObjOknoGlowne.get("string_menu");
        OknoGlowne_string_menu_title = (String) jsonObjOknoGlowne.get("string_menu_title");
        OknoGlowne_string_help_message = (String) jsonObjOknoGlowne.get("string_help_message");
        temp = (long) jsonObjOknoGlowne.get("color_pasekwyniku");
        int temp2 = (int) temp;
        OknoGlowne_kolor_pasekWyniku = new Color(temp2);
        temp = (long) jsonObjOknoGlowne.get("color_panelgry");
        temp2 = (int) temp;
        OknoGlowne_kolor_panelGry = new Color(temp2);
        temp = (long) jsonObjOknoGlowne.get("color_background");
        temp2 = (int) temp;
        OknoGlowne_kolor_background = new Color(temp2);
        OknoGlowne_weightx_pasek_wyniku = (long) jsonObjOknoGlowne.get("weightx_pasek_wyniku");
        OknoGlowne_weighty_pasek_wyniku = (double) jsonObjOknoGlowne.get("weighty_pasek_wyniku");
        temp = (long) jsonObjOknoGlowne.get("gridx_pasek_wyniku");
        OknoGlowne_gridx_pasek_wyniku = (int) temp;
        temp = (long) jsonObjOknoGlowne.get("gridy_pasek_wyniku");
        OknoGlowne_gridy_pasek_wyniku = (int) temp;
        temp = (long) jsonObjOknoGlowne.get("gridx_panel_gry");
        OknoGlowne_gridx_panel_gry = (int) temp;
        temp = (long) jsonObjOknoGlowne.get("gridy_panel_gry");
        OknoGlowne_gridy_panel_gry = (int) temp;
        OknoGlowne_weighty_panel_gry = (double) jsonObjOknoGlowne.get("weighty_panel_gry");
        OknoGlowne_string_authors_data = (String) jsonObjOknoGlowne.get("string_authors_data");
    }

    /**
     * Metoda odpowiadająca za wczytanie konfiguracji paska wyniku
     *
     * @param jsonObjPasekWyniku Obiekt typu JSONObject
     */
    private void wczytaj_PasekWyniku(JSONObject jsonObjPasekWyniku) {
        long temp = (long) jsonObjPasekWyniku.get("zycie");
        PasekWyniku_const_zycie = (int) temp;
        temp = (long) (jsonObjPasekWyniku.get("czas"));
        PasekWyniku_const_czas = (int) temp;
        temp = (long) (jsonObjPasekWyniku.get("wynik"));
        PasekWyniku_wynik = (int) temp;
        temp = (long) (jsonObjPasekWyniku.get("licznik"));
        PasekWyniku_licznik = (int) temp;
        PasekWyniku_init = (boolean) jsonObjPasekWyniku.get("init");
        PasekWyniku_init2 = (boolean) jsonObjPasekWyniku.get("init2");
        PasekWyniku_string_zycie = (String) jsonObjPasekWyniku.get("string_zycie");
        PasekWyniku_string_wynik = (String) jsonObjPasekWyniku.get("string_wynik");
        PasekWyniku_string_czas = (String) jsonObjPasekWyniku.get("string_czas");
        PasekWyniku_string_pauza = (String) jsonObjPasekWyniku.get("string_pauza");
    }

    /**
     * Metoda odpowiadająca za wczytanie mapy pozycji klocków i ich zdrowia
     *
     * @param jsonObjMapa Obiekt typu JSONObject
     */
    private void wczytaj_Mape(JSONObject jsonObjMapa) {

        JSONArray tabex = (JSONArray) jsonObjMapa.get("MAPA");
        JSONArray tabin = new JSONArray();
        Boolean init = false;

        for (int i = 0; i < tabex.size(); i++) {
            tabin = (JSONArray) tabex.get(i);
            for (int j = 0; j < tabin.size(); j++) {
                if (!init) {
                    bricksPos = new int[tabex.size()][tabin.size()];
                    init = true;
                }
                bricksPos[i][j] = (int) ((long) (tabin.get(j)));
            }
        }
    }

    /**
     * metoda czytaj perk
     */
    private void wczytaj_Perki(JSONObject jsonObjPerk) {
        Perk_string_bonus_0 = (String) jsonObjPerk.get("string_bonus_0");
        Perk_string_bonus_1 = (String) jsonObjPerk.get("string_bonus_1");
        Perk_string_bonus_2 = (String) jsonObjPerk.get("string_bonus_2");
        Perk_string_bonus_3 = (String) jsonObjPerk.get("string_bonus_3");
        Perk_string_bonus_4 = (String) jsonObjPerk.get("string_bonus_4");
        Perk_string_bonus_5 = (String) jsonObjPerk.get("string_bonus_5");
        Perk_string_bonus_6 = (String) jsonObjPerk.get("string_bonus_6");
        Perk_string_bonus_7 = (String) jsonObjPerk.get("string_bonus_7");
        Perk_string_bonus_8 = (String) jsonObjPerk.get("string_bonus_8");
        Perk_string_bonus_9 = (String) jsonObjPerk.get("string_bonus_9");
        Perk_string_bonus_10 = (String) jsonObjPerk.get("string_bonus_10");
        Perk_string_bonus_11 = (String) jsonObjPerk.get("string_bonus_11");

    }

    /**
     * metoda cyztaj klocek
     */
    private void wczytaj_Klocki(JSONObject jsonObjectKlocek) {
        Klocek_zycie_1 = (String) jsonObjectKlocek.get("string_zycie_1");
        Klocek_zycie_2 = (String) jsonObjectKlocek.get("string_zycie_2");
        Klocek_zycie_3 = (String) jsonObjectKlocek.get("string_zycie_3");
        Klocek_zycie_4 = (String) jsonObjectKlocek.get("string_zycie_4");
        Klocek_zycie_5 = (String) jsonObjectKlocek.get("string_zycie_5");

    }

    /**
     * Metoda do wczytania Settinf Frame
     */
    private void wczytaj_Setting_Frame(JSONObject jsonObjSettingFrame) {
        SettingFrame_string_ip = (String) jsonObjSettingFrame.get("string_ip");
        SettingFrame_string_accept = (String) jsonObjSettingFrame.get("string_accept");
    }

    /**
     *
     */
    private void wczytaj_Pilka(JSONObject jsonObjPilka) {
        System.out.println("hello wejscie");
        long temp = (long) jsonObjPilka.get("dx");
        Pilka_dx = (int) temp;
        temp = (long) jsonObjPilka.get("dy");
        Pilka_dy = (int) temp;
        temp = (long) jsonObjPilka.get("start");
        ;
        pilka_start = (int) temp;
        pilka_string_imgBall = (String) jsonObjPilka.get("string_imgBall");
        //   System.out.println("hello1 ");
        //  System.out.println(pilka_string_imgBall +"hello");
    }

    private void wczytaj_Paletka(JSONObject jsonObjPaletka) {
        long temp = (long) jsonObjPaletka.get("predkosc");
        paletka_predkosc = (int) temp;
        paletka_string_imgPale = (String) jsonObjPaletka.get("string_imgPale");
    }

    private void wczytaj_PanelGry(JSONObject jsonObjPanelGry) {
        panel_gry_init = (boolean) jsonObjPanelGry.get("init");
        panel_gry_pauza = (boolean) jsonObjPanelGry.get("pauza");
        panel_gry_pozostale_klocki = (boolean) jsonObjPanelGry.get("pozostale_klocki");
        panel_gry_string_imgPanel = (String) jsonObjPanelGry.get("string_tlo");

    }
}
