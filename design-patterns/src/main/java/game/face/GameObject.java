package game.face;

import game.check.BaseShape;
import game.check.ImpactCheck;
import game.enumerate.Dir;
import game.enumerate.FieldActionEnum;
import game.enumerate.TeamGroup;
import game.strategy.bulletStrategy.BulletStrategy;
import lombok.Data;
import util.ProjectCache;

import java.awt.*;
import java.util.List;

/**
 * 调停者模式
 * 玩家坦克 子弹 敌人坦克 墙 等等 之间的关系全有它处理
 */
@Data
public abstract class GameObject implements BaseShape {

    protected int x;
    protected int y;
    protected boolean isDie;
    protected int width;
    protected int high;
    protected final TeamGroup teamGroup;
    protected int liveNum;
    protected int speed;
    protected Dir dir;

    protected static long FRAME_WIDTH = Long.parseLong(ProjectCache.getValue("frame-width"));
    protected static long FRAME_HEIGHT = Long.parseLong(ProjectCache.getValue("frame-high"));

    public GameObject(TeamGroup group) {
        teamGroup = group;
    }

    public abstract void statusCheck(List<GameObject> gameObjects, ImpactCheck<GameObject> impactCheck);

    public abstract void executeElementSpecialField(List<GameObject> elements, FieldActionEnum type, BulletStrategy<GameObject> strategy);

    public abstract void paintElement(Graphics g);

    public abstract void resurrection();

}
