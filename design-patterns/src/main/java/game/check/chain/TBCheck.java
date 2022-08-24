package game.check.chain;

import game.bullet.BaseBullet;
import game.check.ImpactCheck;
import game.face.GameObject;
import game.model.tank.BaseTank;

import java.awt.*;

public class TBCheck implements ImpactCheck<GameObject> {
    @Override
    public boolean isCollide(GameObject shape1, GameObject shape2) {
        if (((shape1 instanceof BaseTank && shape2 instanceof BaseBullet) ||
                (shape1 instanceof BaseBullet && shape2 instanceof BaseTank))
                        && !shape1.getTeamGroup().equals(shape2.getTeamGroup())
        ) {
            Rectangle rectangle = shape1.getRectangleShape();
            Rectangle rectangleShape = shape2.getRectangleShape();
            if (rectangle.intersects(rectangleShape)) {
                shape1.setDie(true);
                shape2.setDie(true);
                return true;
            }
        }
        return false;
    }
}
