package game.bullet.impl;

import game.bullet.BaseBullet;
import game.enumerate.Dir;
import util.ProjectCache;

import java.awt.*;


public class Bullet extends BaseBullet {

    public Bullet() {
        width = Integer.parseInt(ProjectCache.getValue("bullet-width"));
        high = Integer.parseInt(ProjectCache.getValue("bullet-high"));
        speed = Integer.parseInt(ProjectCache.getValue("bullet-speed"));
        x = Integer.parseInt(ProjectCache.getValue("bullet-x"));
        y = Integer.parseInt(ProjectCache.getValue("bullet-y"));
        dir = Dir.UP;
    }

    public Bullet(int x, int y, Dir dir) {
        width = Integer.parseInt(ProjectCache.getValue("bullet-width"));
        high = Integer.parseInt(ProjectCache.getValue("bullet-high"));
        speed = Integer.parseInt(ProjectCache.getValue("bullet-speed"));
        this.x = x - width / 2;
        this.y = y - high / 2;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.blue);
        g.drawOval(x, y, width, high);
        move();
    }

    public void move() {
        if (dir == Dir.LEFT) {
            x -= speed;
        }
        if (dir == Dir.RIGHT) {
            x += speed;
        }
        if (dir == Dir.UP) {
            y -= speed;
        }
        if (dir == Dir.DOWN) {
            y += speed;
        }
        if (x < 0 || x > 800 || y < 0 || y > 600) {
            this.isDie = true;
        }
    }
}
