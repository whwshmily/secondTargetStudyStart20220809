package game.decorator;

import game.check.ImpactCheck;
import game.enumerate.FieldActionEnum;
import game.face.GameObject;
import game.strategy.bulletStrategy.BulletStrategy;

import java.awt.*;
import java.util.List;

public abstract class Decorator extends GameObject {
    public GameObject go;

    public Decorator(GameObject go) {
        super(null);
        this.go = go;
    }

    @Override
    public void statusCheck(List<GameObject> gameObjects, ImpactCheck<GameObject> impactCheck) {
        go.statusCheck(gameObjects, impactCheck);
    }

    @Override
    public void executeElementSpecialField(List<GameObject> elements, FieldActionEnum type, BulletStrategy<GameObject> strategy) {
        go.executeElementSpecialField(elements, type, strategy);
    }

    @Override
    public void paintElement(Graphics g) {
        go.paintElement(g);
        paint(g);
    }

    protected abstract void paint(Graphics g);

    @Override
    public void resurrection() {

    }
}
