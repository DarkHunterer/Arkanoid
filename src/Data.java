import java.awt.*;
import java.io.FileWriter;
import javax.json.*;
import javax.json.stream.JsonGenerator;

/**
 * Created by Daniel on 14.11.2015.
 */
public class Data {
    public int OknoGlowne_Delay =10;
    public int OknoGlowne_width=500;
    public int OknoGlowne_heigth=500;
    public String OknoGlowne_command_timer_off="TIMER_MAIN_TICK_OFF";
    public String OknoGlowne_command_timer_on="TIMER_MAIN_TICK";
    public String OknoGlowne_command_exit="EXIT";
    public String OknoGlowne_command_authors="AUTORZY";
    public String OknoGlowne_command_help="POMOC";
    public String OknoGlowne_command_start="START";
    public String OknoGlowne_command_settings="USTAWIENIA";

    public String OknoGlowne_string_menuPomoc_title="Pomoc";
    public String OknoGlowne_string_start="Start";
    public String OknoGlowne_string_end="Koniec";
    public String OknoGlowne_string_bestScore="Najlepsze wyniki";
    public String OknoGlowne_string_config="Ustawienia";
    public String OknoGlowne_string_rules="Zasady gry";
    public String OknoGlowne_string_authors="O autorach";
    public String OknoGlowne_string_menu="Menu";
    public String OknoGlowne_string_menu_title="Pasek menu";


    public Color OknoGlowne_kolor_pasekWyniku = Color.red;
    public Color OknoGlowne_kolor_panelGry = Color.red;
    public Color OknoGlowne_kolor_background = Color.darkGray;

    Data(){
        //JSONObject model = new JSONObject();
    }

    public void zapisz_config(){
        try {
            FileWriter writer = new FileWriter("testjson.txt");
            JsonGenerator gen = Json.createGenerator(writer);

            gen.writeStartObject()
                    .writeStartArray("OknoGlowne")
                        .writeStartObject()
                            .write("Delay", OknoGlowne_Delay)
                            .write("width",OknoGlowne_width)
                            .write("heigth",OknoGlowne_heigth)
                            .write("command_timer_off",OknoGlowne_command_timer_off)
                            .write("command_timer_on",OknoGlowne_command_timer_on)
                            .write("command_exit",OknoGlowne_command_exit)
                            .write("command_autors",OknoGlowne_command_authors)
                            .write("command_help",OknoGlowne_command_help)
                            .write("command_start",OknoGlowne_command_start)
                            .write("command_settings",OknoGlowne_command_settings)
                            .write("string_menuPomoc_title",OknoGlowne_string_menuPomoc_title)
                            .write("string_start",OknoGlowne_string_start)
                            .write("string_end",OknoGlowne_string_end)
                           .write("string_bestScore",OknoGlowne_string_bestScore)
                           .write("string_config",OknoGlowne_string_config)
                           .write("string_rules",OknoGlowne_string_rules)
                           .write("string_authors",OknoGlowne_string_authors)
                           .write("string_menu",OknoGlowne_string_menu)
                           .write("string_menu_title",OknoGlowne_string_menu_title)
                            .writeEnd()
                           .writeEnd()
               .writeEnd();
            gen.close();

        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
    public void wczytaj_config(String sciezka){
      //  JSONObject obj = new JSONObject(sciezka);

    }

}
