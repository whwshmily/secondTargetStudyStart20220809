package game.model.tank.impl;

import game.enumerate.Dir;
import game.enumerate.TeamGroup;
import game.face.GameObject;
import game.strategy.bulletStrategy.BulletStrategy;
import game.model.tank.BaseTank;
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
    private int oldX;
    private int oldY;

    public PlayerTank(TeamGroup teamGroup) {
        super(teamGroup);
        width = Integer.parseInt(ProjectCache.getValue("player-tank-width"));
        high = Integer.parseInt(ProjectCache.getValue("player-tank-high"));
        speed = Integer.parseInt(ProjectCache.getValue("player-tank-speed"));
        x = Integer.parseInt(ProjectCache.getValue("player-tank-x"));
        y = Integer.parseInt(ProjectCache.getValue("player-tank-y"));
        liveNum = Integer.parseInt(ProjectCache.getValue("player-tank-live-num"));
        dir = Dir.UP;
        this.oldY = y;
        this.oldX = x;
    }


    @Override
    protected void paint(Graphics g) {
        if (isDie) {
            return;
        }
        Color color = g.getColor();
        g.setColor(Color.black);
        BufferedImage dirImage = ResourcesUtil.getDirBufferImage("player2", dir.getValue());
        width = dirImage.getWidth();
        high = dirImage.getHeight();
        g.drawImage(dirImage, x, y - high, width, high, null);
        g.setColor(color);
    }

    @Override
    protected void move() {
        if (!isMoving) {
//            this.x = oldX;
//            this.y = oldY;
            return;
        }
        if (left) {
            dir = Dir.LEFT;
            if (x > 0)
                this.oldX = x;
            x -= speed;
        }
        if (right) {
            dir = Dir.RIGHT;
            if (x < FRAME_WIDTH - width)
                this.oldX = x;
            x += speed;
        }
        if (up) {
            dir = Dir.UP;
            if (y > high * 1.5)
                this.oldY = y;
            y -= speed;
        }
        if (down) {
            dir = Dir.DOWN;
            if (y < FRAME_HEIGHT)
                this.oldY = y;
            y += speed;
        }
    }

    @Override
    protected void fire(List<GameObject> bullets, BulletStrategy<GameObject> strategy) {
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

    public void resurrection() {
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
