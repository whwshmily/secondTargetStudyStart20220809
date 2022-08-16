package game.tank.impl;

import game.bullet.BaseBullet;
import game.enumerate.Dir;
import game.enumerate.TeamGroup;
import game.strategy.bulletStrategy.BulletStrategy;
import game.tank.BaseTank;
import util.ProjectCache;
import util.ResourcesUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.List;


public class PlayerTank extends BaseTank {

    private boolean left = false;
    private boolean right = false;
    private boolean down = false;
    private boolean up = false;
    private long intervalTime = Long.parseLong(ProjectCache.getValue("player-fire-interval-time"));
    private Timestamp fireTime;
    private int liveNum;

    public PlayerTank(TeamGroup teamGroup) {
        super(teamGroup);
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
        BufferedImage dirImage = ResourcesUtil.getDirBufferImage("player2", dir.getValue());
        width = dirImage.getWidth();
        high = dirImage.getHeight();
        g.drawImage(dirImage, x, y - high, width, high, null);
        g.setColor(color);
    }

    @Override
    public void move() {
        if (left) {
            dir = Dir.LEFT;
            if (x > 0)
                x -= speed;
        }
        if (right) {
            dir = Dir.RIGHT;
            if (x < FRAME_WIDTH - width)
                x += speed;
        }
        if (up) {
            dir = Dir.UP;
            if (y > high * 1.5)
                y -= speed;
        }
        if (down) {
            dir = Dir.DOWN;
            if (y < FRAME_HEIGHT)
                y += speed;
        }
    }

    @Override
    public void fire(List<BaseBullet> bullets, BulletStrategy<BaseTank> strategy) {
        if (fireTime != null && System.currentTimeMillis() - fireTime.getTime() < intervalTime) {
            return;
        } else {
            fireTime = new Timestamp(System.currentTimeMillis());
        }

        bullets.addAll(strategy.produceBullet(this));
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

    public int getLiveNum() {
        return liveNum;
    }

    public void setLiveNum(int liveNum){
        this.liveNum = liveNum;
    }

    public void resurrection(){
        width = Integer.parseInt(ProjectCache.getValue("player-tank-width"));
        high = Integer.parseInt(ProjectCache.getValue("player-tank-high"));
        speed = Integer.parseInt(ProjectCache.getValue("player-tank-speed"));
        x = Integer.parseInt(ProjectCache.getValue("player-tank-x"));
        y = Integer.parseInt(ProjectCache.getValue("player-tank-y"));
        dir = Dir.UP;
    }

    public Rectangle getRectangleShape() {
        return new Rectangle(x, y - high, width, high);
    }
}
