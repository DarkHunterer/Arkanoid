import jdk.nashorn.internal.parser.JSONParser;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.json.simple.JSONObject;

/**
 * Created by Daniel on 14.11.2015.
 */
public class Data {
    ///
    ///
    public int OknoGlowne_Delay =10;
    public int OknoGlowne_width=500;
    public int OknoGlowne_heigth=500;
    public String OknoGlowne_command_timer_off;//="TIMER_MAIN_TICK_OFF";
    public String OknoGlowne_command_timer_on;//="TIMER_MAIN_TICK";
    public String OknoGlowne_command_exit;//="EXIT";
    public String OknoGlowne_command_authors;//="AUTORZY";
    public String OknoGlowne_command_help;//="POMOC";
    public String OknoGlowne_command_start;//="START";
    public String OknoGlowne_command_settings;//="USTAWIENIA";
    public String OknoGlowne_string_menuPomoc_title;//="Pomoc";
    public String OknoGlowne_string_start;//="Start";
    public String OknoGlowne_string_end;//="Koniec";
    public String OknoGlowne_string_bestScore;//="Najlepsze wyniki";
    public String OknoGlowne_string_config;//="Ustawienia";
    public String OknoGlowne_string_rules;//="Zasady gry";
    public String OknoGlowne_string_authors;//="O autorach";
    public String OknoGlowne_string_menu;//="Menu";
    public String OknoGlowne_string_menu_title;//="Pasek menu";
    public String OknoGlowne_string_help_message;//="Serio...? Pilka zbija klocki.\nSterujesz strzalkami.\nW czym tu potrzeba pomocy?";
    public Color OknoGlowne_kolor_pasekWyniku;// = Color.red;
    public Color OknoGlowne_kolor_panelGry;// = Color.red;
    public Color OknoGlowne_kolor_background;// = Color.darkGray;
    ///
    public int PasekWyniku_const_zycie;
    public int PasekWyniku_const_czas;

    Data(){
        wczytaj_config("domyslny_config.json");
    }

    public void zapisz_config(){
        try {
            FileWriter writer = new FileWriter("testjson.json");
            StringWriter out = new StringWriter();
            JSONObject objMain = new JSONObject();
            JSONObject objOknoGlowne = new JSONObject();
            JSONObject objPasekWyniku = new JSONObject();
            JSONObject objPilka = new JSONObject();

            zapisz_OknoGlowne(objOknoGlowne);
            zapisz_PasekWyniku(objPasekWyniku);
            zapisz_Pilke(objPilka);

            objMain.put("OknoGlowne",objOknoGlowne);
            objMain.put("PasekWyniku",objPasekWyniku);
            objMain.put("Pilka",objPilka);

            objMain.writeJSONString(out);
            writer.write(out.toString());
            writer.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void wczytaj_config(String sciezka){
        try {
            org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
            Object obj = parser.parse(new FileReader(sciezka));
            JSONObject jsonObjMain = (JSONObject) obj;
            JSONObject jsonObjOknoGlowne = (JSONObject) jsonObjMain.get("OknoGlowne");
            JSONObject jsonObjPasekWyniku = (JSONObject) jsonObjMain.get("PasekWyniku");

            wczytaj_OknoGlowne(jsonObjOknoGlowne);
            wczytaj_PasekWyniku(jsonObjPasekWyniku);
        }
        catch (Exception ex){
                    System.out.println("Zlapano wyjatek: "+ ex.toString());
            }
    }
    private void wczytaj_OknoGlowne(JSONObject jsonObjOknoGlowne){
        long temp = (long) jsonObjOknoGlowne.get("Delay");
        OknoGlowne_Delay = (int)temp;
        temp = (long)(jsonObjOknoGlowne.get("width"));
        OknoGlowne_width = (int)temp;
        temp = (long) (jsonObjOknoGlowne.get("heigth"));
        OknoGlowne_heigth=(int)temp;
        OknoGlowne_command_timer_off=(String) jsonObjOknoGlowne.get("command_timer_off");
        OknoGlowne_command_timer_on=(String) jsonObjOknoGlowne.get("command_timer_on");
        OknoGlowne_command_exit=(String) jsonObjOknoGlowne.get("command_exit");
        OknoGlowne_command_authors=(String) jsonObjOknoGlowne.get("command_autors");
        OknoGlowne_command_help=(String) jsonObjOknoGlowne.get("command_help");
        OknoGlowne_command_start=(String) jsonObjOknoGlowne.get("command_start");
        OknoGlowne_command_settings=(String) jsonObjOknoGlowne.get("command_settings");

        OknoGlowne_string_menuPomoc_title=(String) jsonObjOknoGlowne.get("string_menuPomoc_title");
        OknoGlowne_string_start=(String) jsonObjOknoGlowne.get("string_start");
        OknoGlowne_string_end=(String) jsonObjOknoGlowne.get("string_end");
        OknoGlowne_string_bestScore=(String) jsonObjOknoGlowne.get("string_bestScore");
        OknoGlowne_string_config=(String) jsonObjOknoGlowne.get("string_config");
        OknoGlowne_string_rules=(String) jsonObjOknoGlowne.get("string_rules");
        OknoGlowne_string_authors=(String) jsonObjOknoGlowne.get("string_authors");
        OknoGlowne_string_menu=(String) jsonObjOknoGlowne.get("string_menu");
        OknoGlowne_string_menu_title=(String) jsonObjOknoGlowne.get("string_menu_title");
        OknoGlowne_string_help_message = (String) jsonObjOknoGlowne.get("string_help_message");
        temp = (long)jsonObjOknoGlowne.get("color_pasekwyniku");
        int temp2 = (int) temp;
        OknoGlowne_kolor_pasekWyniku = new Color(temp2);
        temp = (long) jsonObjOknoGlowne.get("color_panelgry");
        temp2 = (int) temp;
        OknoGlowne_kolor_panelGry= new Color(temp2);
        temp = (long) jsonObjOknoGlowne.get("color_background");
        temp2 = (int) temp;
        OknoGlowne_kolor_background= new Color(temp2);
    }
    private void wczytaj_PasekWyniku(JSONObject jsonObjPasekWyniku){
        long temp = (long) jsonObjPasekWyniku.get("zycie");
        PasekWyniku_const_zycie= (int)temp;
        temp = (long)(jsonObjPasekWyniku.get("czas"));
        PasekWyniku_const_czas= (int)temp;
    }
    private void zapisz_OknoGlowne(JSONObject objOknoGlowne){
        objOknoGlowne.put("Delay", OknoGlowne_Delay);
        objOknoGlowne.put("width", OknoGlowne_width);
        objOknoGlowne.put("heigth", OknoGlowne_heigth);
        objOknoGlowne.put("command_timer_off", OknoGlowne_command_timer_off);
        objOknoGlowne.put("command_timer_on", OknoGlowne_command_timer_on);
        objOknoGlowne.put("command_exit", OknoGlowne_command_exit);
        objOknoGlowne.put("command_autors", OknoGlowne_command_authors);
        objOknoGlowne.put("command_help", OknoGlowne_command_help);
        objOknoGlowne.put("command_start", OknoGlowne_command_start);
        objOknoGlowne.put("command_settings", OknoGlowne_command_settings);
        objOknoGlowne.put("string_menuPomoc_title", OknoGlowne_string_menuPomoc_title);
        objOknoGlowne.put("string_start", OknoGlowne_string_start);
        objOknoGlowne.put("string_end", OknoGlowne_string_end);
        objOknoGlowne.put("string_bestScore", OknoGlowne_string_bestScore);
        objOknoGlowne.put("string_config", OknoGlowne_string_config);
        objOknoGlowne.put("string_rules", OknoGlowne_string_rules);
        objOknoGlowne.put("string_authors", OknoGlowne_string_authors);
        objOknoGlowne.put("string_menu", OknoGlowne_string_menu);
        objOknoGlowne.put("string_menu_title", OknoGlowne_string_menu_title);
        objOknoGlowne.put("string_help_message",OknoGlowne_string_help_message);
        objOknoGlowne.put("color_pasekwyniku", OknoGlowne_kolor_pasekWyniku.getRGB());
        objOknoGlowne.put("color_panelgry", OknoGlowne_kolor_panelGry.getRGB());
        objOknoGlowne.put("color_background", OknoGlowne_kolor_background.getRGB());
    }
    private void zapisz_PasekWyniku(JSONObject ObjPasekWyniku){
        ObjPasekWyniku.put("zycie",PasekWyniku_const_zycie);
        ObjPasekWyniku.put("czas",PasekWyniku_const_czas);
    }
    private void zapisz_Pilke(JSONObject ObjPilka){
    }
}
