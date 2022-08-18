package game.check;

import game.face.GameObject;

import java.awt.*;

public class RectangleImpactCheck implements ImpactCheck<GameObject> {


    @Override
    public boolean isCollide(GameObject shape1, GameObject shape2) {
        Rectangle s1 = shape1.getRectangleShape();
        Rectangle s2 = shape2.getRectangleShape();
        return s1.intersects(s2);
    }
}
