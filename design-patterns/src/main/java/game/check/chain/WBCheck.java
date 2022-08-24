package game.check.chain;

import game.check.ImpactCheck;
import game.face.GameObject;
import game.model.tank.BaseTank;
import game.model.wall.BaseWall;

import java.awt.*;

public class WBCheck implements ImpactCheck<GameObject> {
    @Override
    public boolean isCollide(GameObject shape1, GameObject shape2) {
        if (shape1 instanceof BaseWall && shape2 instanceof game.bullet.BaseBullet) {
            Rectangle rectangleShape = shape1.getRectangleShape();
            Rectangle rectangleShape1 = shape2.getRectangleShape();
            if (rectangleShape.intersects(rectangleShape1)) {
                shape2.setDie(true);
                return true;
            }
        } else if (shape2 instanceof BaseWall && shape1 instanceof BaseTank) {
            return isCollide(shape2, shape1);
        }
        return false;
    }
}
