package game.tank;

import game.bullet.BaseBullet;
import game.check.BaseShape;
import game.enumerate.Dir;
import game.enumerate.TeamGroup;
import lombok.Data;
import util.ProjectCache;

import java.awt.*;
import java.util.List;

@Data
public abstract class BaseTank implements BaseShape {
    protected int width;
    protected int high;
    protected int speed;
    protected int x;
    protected int y;
    protected Dir dir;

    protected static long FRAME_WIDTH = Long.parseLong(ProjectCache.getValue("frame-width"));
    protected static long FRAME_HEIGHT = Long.parseLong(ProjectCache.getValue("frame-high"));

    private final TeamGroup teamGroup;

    private boolean isDie;

    public BaseTank(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    public abstract void paint(Graphics g);

    public abstract void move();

    public abstract void fire(List<BaseBullet> bullets);
}
