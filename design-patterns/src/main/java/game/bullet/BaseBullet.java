package game.bullet;

import game.enumerate.Dir;
import lombok.Data;

import java.awt.*;

@Data
public abstract class BaseBullet {

    protected int width;
    protected int high;
    protected int speed;
    protected int x;
    protected int y;
    protected Dir dir;
    protected boolean isDie = false;

    public abstract void paint(Graphics g);

    public abstract void move();


}
