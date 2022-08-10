package game.tank.impl;

import game.bullet.BaseBullet;
import game.bullet.impl.Bullet;
import game.enumerate.Dir;
import game.tank.BaseTank;
import util.ProjectCache;

import java.awt.*;
import java.sql.Timestamp;
import java.util.List;


public class PlayerTank extends BaseTank {

    private boolean left = false;
    private boolean right = false;
    private boolean down = false;
    private boolean up = false;
    private long intervalTime = Long.parseLong(ProjectCache.getValue("player-fire-interval-time"));
    private Timestamp fireTime;


    public PlayerTank() {
        width = Integer.parseInt(ProjectCache.getValue("player-tank-width"));
        high = Integer.parseInt(ProjectCache.getValue("player-tank-high"));
        speed = Integer.parseInt(ProjectCache.getValue("player-tank-speed"));
        x = Integer.parseInt(ProjectCache.getValue("player-tank-x"));
        y = Integer.parseInt(ProjectCache.getValue("player-tank-y"));
        liveNum = Integer.parseInt(ProjectCache.getValue("player-tank-live-num"));
        dir = Dir.UP;
    }


    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.black);
        g.drawRect(x, y, width, high);
        g.setColor(color);
    }

    @Override
    public void move() {
        if (left) {
            dir = Dir.LEFT;
            x -= speed;
        }
        if (right) {
            dir = Dir.RIGHT;
            x += speed;
        }
        if (up) {
            dir = Dir.UP;
            y -= speed;
        }
        if (down) {
            dir = Dir.DOWN;
            y += speed;
        }
    }

    @Override
    public void fire(List<BaseBullet> bullets) {
        if (fireTime != null && System.currentTimeMillis() - fireTime.getTime() < intervalTime) {
            return;
        } else {
            fireTime = new Timestamp(System.currentTimeMillis());
        }

        bullets.add(new Bullet(x + width / 2, y + high / 2, dir));
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
