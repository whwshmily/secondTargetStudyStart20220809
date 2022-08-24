package game.check.chain;

import game.check.ImpactCheck;
import game.enumerate.TeamGroup;
import game.face.GameObject;
import game.model.tank.BaseTank;

import java.awt.*;

public class TTCheck implements ImpactCheck<GameObject> {
    @Override
    public boolean isCollide(GameObject shape1, GameObject shape2) {
        if (shape1 instanceof BaseTank && shape2 instanceof BaseTank && !shape1.getTeamGroup().equals(shape2.getTeamGroup())) {
            Rectangle rectangle1 = shape1.getRectangleShape();
            Rectangle rectangle2 = shape2.getRectangleShape();
            boolean intersects = rectangle1.intersects(rectangle2);
            if (intersects) {
                if (shape1.getTeamGroup().equals(TeamGroup.PLAYER)) {
                    shape1.setDie(true);
                } else {
                    shape2.setDie(true);
                }
                return true;
            }
        }
        return false;

    }
}
