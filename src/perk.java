import java.awt.*;

/**
 * Created by Daniel on 02.12.2015.
 */
public class perk {
    private int pos_x;
    private int pos_y;
    private int height;
    private int width;
    Color col = Color.yellow;
    private int dy;
    public perk(int spawn_x,int spawn_y,int szer,int wys){
        pos_x = spawn_x;
        pos_y = spawn_y;
        height = wys;
        width = szer;
        dy = 1;
        System.out.println("Perk spawn!");
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
}
