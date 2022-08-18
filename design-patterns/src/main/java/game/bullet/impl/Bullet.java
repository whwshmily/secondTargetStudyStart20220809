package game.bullet.impl;

import game.bullet.BaseBullet;
import game.enumerate.Dir;
import game.enumerate.TeamGroup;
import util.ProjectCache;
import util.ResourcesUtil;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Bullet extends BaseBullet {
    private String name;

    public Bullet(TeamGroup teamGroup) {
        super(teamGroup);
        width = Integer.parseInt(ProjectCache.getValue("bullet-width"));
        high = Integer.parseInt(ProjectCache.getValue("bullet-high"));
        speed = Integer.parseInt(ProjectCache.getValue("bullet-speed"));
        x = Integer.parseInt(ProjectCache.getValue("bullet-x"));
        y = Integer.parseInt(ProjectCache.getValue("bullet-y"));
        dir = Dir.UP;
        name = teamGroup.equals(TeamGroup.PLAYER) ? "bullet" : "computer_bullet";
    }

    public Bullet(int x, int y, Dir dir, TeamGroup teamGroup) {
        super(teamGroup);
        width = Integer.parseInt(ProjectCache.getValue("bullet-width"));
        high = Integer.parseInt(ProjectCache.getValue("bullet-high"));
        speed = Integer.parseInt(ProjectCache.getValue("bullet-speed"));
        this.x = x;
        this.y = y;
        this.dir = dir;
        name = teamGroup.equals(TeamGroup.PLAYER) ? "bullet" : "computer_bullet";
    }

    protected void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.black);
        BufferedImage dirImage = ResourcesUtil.getDirBufferImage(name, dir.getValue());
        width = dirImage.getWidth();
        high = dirImage.getHeight();
        g.drawImage(dirImage, x - width / 2, y - high / 2, width, high, null);
//        g.drawOval(x, y, width, high);
        move();
    }

    protected void move() {
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
        if (x < 0 || x > FRAME_WIDTH || y < 0 || y > FRAME_HEIGHT) {
            this.isDie = true;
        }
    }

    public Rectangle getRectangleShape() {
        return new Rectangle(x - width / 2, y - high / 2, width, high);
    }
}
