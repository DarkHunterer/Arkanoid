import java.awt.*;

/**
 * Created by Daniel on 02.12.2015.
 */
public class perk {
    private int pos_x;
    private int pos_y;
    private int height;
    private int width;
    private String kod;
    Color col = Color.yellow;
    private int dy;
    paletka paletka_;

    public perk(int spawn_x,int spawn_y,int szer,int wys,String code,paletka pale){
        pos_x = spawn_x;
        pos_y = spawn_y;
        height = wys;
        width = szer;
        dy = 3;
        kod = code;
        paletka_ = pale;
        switch (code){
            case "z":{
                col = Color.magenta;
            }
            break;
            case "p":{
                col = Color.black;
            }
            break;
            default:
                break;
        }
        System.out.println("Perk spawn!");
    }

    public void akcja(){
        switch (kod){
            case "z":{
                col = Color.magenta;
                paletka_.setSzer_(paletka_.getSzer_()*3/4);
            }
            break;
            case "p":{
                col = Color.black;
                paletka_.setSzer_(paletka_.getSzer_()*4/3);

            }
            break;
            default:
                break;
        }
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void porusz(){

            pos_y+=dy;
    }
    public Rectangle getBounds(){
        return new Rectangle(pos_x,pos_y,width,height);
    }

}
