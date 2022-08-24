package game.decorator;

import game.face.GameObject;

import java.awt.*;

public class LTandDecorator extends Decorator {
    public LTandDecorator(GameObject go) {
        super(go);
    }

    @Override
    protected void paint(Graphics g) {
        x = go.getX();
        y = go.getY();
        width = go.getWidth();
        high = go.getHigh();
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawLine(x, y, x + width, y + high);
        g.setColor(color);
    }
}
