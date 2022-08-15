package game.tank.impl;

import game.bullet.BaseBullet;
import game.bullet.impl.Bullet;
import game.enumerate.Dir;
import game.enumerate.TeamGroup;
import game.tank.BaseTank;
import util.ComputerTankUtil;
import util.ProjectCache;
import util.ResourcesUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.List;

public class ComputerTank extends BaseTank {

    private BufferedImage dirImage;
    private long intervalTime = Long.parseLong(ProjectCache.getValue("computer-fire-interval-time"));
    private Timestamp fireTime;
    private boolean isMoving = true;

    private final List<BaseBullet> bullets;

    public ComputerTank(TeamGroup teamGroup, List<BaseBullet> bullets) {
        super(teamGroup);
        dir = Dir.DOWN;
        speed = ComputerTankUtil.getSpeed();
        width = Integer.parseInt(ProjectCache.getValue("computer-tank-width"));
        high = Integer.parseInt(ProjectCache.getValue("computer-tank-high"));
        dirImage = ResourcesUtil.getDirBufferImage("computer", dir.getValue());
        x = ComputerTankUtil.getX(dirImage.getWidth());
        y = Integer.parseInt(ProjectCache.getValue("computer-tank-y"));
        this.bullets = bullets;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.black);
        dirImage = ResourcesUtil.getDirBufferImage("computer", dir.getValue());
        width = dirImage.getWidth();
        high = dirImage.getHeight();
        g.drawImage(dirImage, x, y+high/2, width, high, null);
        g.setColor(color);
        this.move();
    }
    public void isChangeDir(){
        Dir dir = ComputerTankUtil.changeTankDir();
        if (dir != null) {
            this.dir = dir;
            this.isMoving = true;
        }
    }
    public void move() {
        this.isChangeDir();
        if (!this.isMoving) {
            return;
        }

        this.fire(bullets);
        if (this.dir == Dir.LEFT) {
            if (x < 0) {
                this.isMoving = false;
            } else {
                x -= speed;
            }
        }
        if (this.dir == Dir.RIGHT) {
            if (x > FRAME_WIDTH - width) {
                this.isMoving = false;
            } else {
                x += speed;
            }
        }
        if (this.dir == Dir.UP) {
            if (y < 0) {
                this.isMoving = false;
            } else {
                y -= speed;

            }
        }
        if (this.dir == Dir.DOWN) {

            if (y > FRAME_HEIGHT - high * 2) {
                this.isMoving = false;
            } else {
                y += speed;
            }
        }
    }

    public void fire(List<BaseBullet> bullets) {
        boolean fire = ComputerTankUtil.isFire();
        if(!fire){
            return;
        }
        if (fireTime != null && System.currentTimeMillis() - fireTime.getTime() < intervalTime) {
            return;
        } else {
            fireTime = new Timestamp(System.currentTimeMillis());
        }
        bullets.add(new Bullet(x + width / 2, y + high, dir, getTeamGroup()));
    }

    public Rectangle getRectangleShape() {
        return new Rectangle(x, y + high, width, high);
    }
}
