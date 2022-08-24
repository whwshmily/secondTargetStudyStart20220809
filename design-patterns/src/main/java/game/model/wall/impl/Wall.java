package game.model.wall.impl;

import game.enumerate.TeamGroup;
import game.model.wall.BaseWall;
import util.ProjectCache;

import java.awt.*;

public class Wall extends BaseWall {
    public Wall(TeamGroup group, int level, int num) {
        super(group);
        String param = ProjectCache.getValue("wall" + "-" + level + "-" + num);
        String[] split = param.split(",");
        width = Integer.parseInt(split[0]);
        high = Integer.parseInt(split[1]);
        x = Integer.parseInt(split[2]);
        y = Integer.parseInt(split[3]);
    }

    @Override
    protected void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.GRAY);
        g.drawRect(x, y, width, high);
        g.setColor(color);
    }
}
