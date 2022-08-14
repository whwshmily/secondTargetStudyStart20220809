package game.bullet;

import game.check.BaseShape;
import game.enumerate.Dir;
import game.enumerate.TeamGroup;
import lombok.Data;
import util.ProjectCache;

import java.awt.*;

@Data
public abstract class BaseBullet implements BaseShape {

    protected int width;
    protected int high;
    protected int speed;
    protected int x;
    protected int y;
    protected Dir dir;
    protected boolean isDie = false;
    protected static long FRAME_WIDTH = Long.parseLong(ProjectCache.getValue("frame-width"));
    protected static long FRAME_HEIGHT = Long.parseLong(ProjectCache.getValue("frame-high"));
    private final TeamGroup teamGroup;
    private boolean isLiving;

    public BaseBullet(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }

    public abstract void paint(Graphics g);

    public abstract void move();


}
