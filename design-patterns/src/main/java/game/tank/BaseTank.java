package game.tank;

import game.bullet.BaseBullet;
import game.enumerate.Dir;

import java.awt.*;
import java.util.List;

public abstract class BaseTank {
    protected int width;
    protected int high;
    protected int speed;
    protected int x;
    protected int y;
    protected int liveNum;
    protected Dir dir;

    public abstract void paint(Graphics g);

    public abstract void move();

    public abstract void fire(List<BaseBullet> bullets);
}
