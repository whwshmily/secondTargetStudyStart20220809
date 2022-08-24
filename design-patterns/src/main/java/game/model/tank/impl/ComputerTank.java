package game.model.tank.impl;

import game.enumerate.Dir;
import game.enumerate.TeamGroup;
import game.face.GameObject;
import game.strategy.bulletStrategy.BulletStrategy;
import game.strategy.bulletStrategy.DefaultComputerStrategy;
import game.model.tank.BaseTank;
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
    private String name = (int) (Math.random() * 2) == 1 ? "computer" : "player2";
    private final List<GameObject> gameObjects;
    private int oldX;
    private int oldY;

    public ComputerTank(TeamGroup teamGroup,List<GameObject> gameObjects) {
        super(teamGroup);
        dir = Dir.DOWN;
        speed = ComputerTankUtil.getSpeed();
        width = Integer.parseInt(ProjectCache.getValue("computer-tank-width"));
        high = Integer.parseInt(ProjectCache.getValue("computer-tank-high"));
        dirImage = ResourcesUtil.getDirBufferImage("computer", dir.getValue());
        x = ComputerTankUtil.getX(dirImage.getWidth());
        y = Integer.parseInt(ProjectCache.getValue("computer-tank-y"));
        this.gameObjects = gameObjects;
        this.oldX = x;
        this.oldY = y;
    }

    protected void paint(Graphics g) {
        if(isDie){
            return;
        }
        Color color = g.getColor();
        g.setColor(Color.black);
        dirImage = ResourcesUtil.getDirBufferImage(name, dir.getValue());
        width = dirImage.getWidth();
        high = dirImage.getHeight();
        g.drawImage(dirImage, x, y + high / 2, width, high, null);
        g.setColor(color);
        this.move();
    }

    private void isChangeDir() {
        Dir dir = ComputerTankUtil.changeTankDir();
        if(!this.isMoving && dir == this.dir){
            this.isChangeDir();
        }
        if (dir != null) {
            this.dir = dir;
            this.isMoving = true;
        }
    }

    public void move() {
        this.isChangeDir();
        if (!this.isMoving) {
            this.x = oldX;
            this.y = oldY;
            return;
        }

        this.fire(gameObjects, new DefaultComputerStrategy());
        if (this.dir == Dir.LEFT) {
            if (x < 0) {
                this.isMoving = false;
            } else {
                this.oldX = x;
                x -= speed;
            }
        }
        if (this.dir == Dir.RIGHT) {
            if (x > FRAME_WIDTH - width) {
                this.isMoving = false;
            } else {
                this.oldX =x;
                x += speed;
            }
        }
        if (this.dir == Dir.UP) {
            if (y < 0) {
                this.isMoving = false;
            } else {
                this.oldY = y;
                y -= speed;

            }
        }
        if (this.dir == Dir.DOWN) {

            if (y > FRAME_HEIGHT - high * 2) {
                this.isMoving = false;
            } else {
                this.oldY = y;
                y += speed;
            }
        }
    }


    protected void fire(List<GameObject> bullets, BulletStrategy<GameObject> strategy) {
        boolean fire = ComputerTankUtil.isFire();
        if (!fire) {
            return;
        }
        if (fireTime != null && System.currentTimeMillis() - fireTime.getTime() < intervalTime) {
            return;
        } else {
            fireTime = new Timestamp(System.currentTimeMillis());
        }
        bullets.addAll(strategy.produceBullet(this));
    }

    public Rectangle getRectangleShape() {
        return new Rectangle(x, y + high, width, high);
    }
}
