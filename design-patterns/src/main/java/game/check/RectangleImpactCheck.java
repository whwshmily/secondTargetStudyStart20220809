package game.check;

import java.awt.*;

public class RectangleImpactCheck implements ImpactCheck {



    public boolean isCollide(BaseShape shape1, BaseShape shape2) {
        Rectangle s1 = shape1.getRectangleShape();
        Rectangle s2 = shape2.getRectangleShape();
        return s1.intersects(s2);
    }
}
