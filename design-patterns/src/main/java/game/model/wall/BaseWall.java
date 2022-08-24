package game.model.wall;

import game.check.ImpactCheck;
import game.enumerate.FieldActionEnum;
import game.enumerate.TeamGroup;
import game.face.GameObject;
import game.strategy.bulletStrategy.BulletStrategy;

import java.awt.*;
import java.util.List;

public abstract class BaseWall extends GameObject {
    public BaseWall(TeamGroup group) {
        super(group);
    }

    protected abstract void paint(Graphics g);
    @Override
    public void statusCheck(List<GameObject> gameObjects, ImpactCheck<GameObject> impactCheck) {

    }

    @Override
    public void executeElementSpecialField(List<GameObject> elements, FieldActionEnum type, BulletStrategy<GameObject> strategy) {

    }

    @Override
    public void paintElement(Graphics g) {
        this.paint(g);
    }

    @Override
    public void resurrection() {

    }
}
