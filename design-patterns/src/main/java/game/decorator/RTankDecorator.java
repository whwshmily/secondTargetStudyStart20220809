package game.decorator;

import game.face.GameObject;

import java.awt.*;

public class RTankDecorator extends Decorator {
    public RTankDecorator(GameObject go) {
        super(go);
        x = go.getX();
        y = go.getY();
        width = go.getWidth();
        high = go.getHigh();
    }

    @Override
    protected void paint(Graphics g) {
        x = go.getX();
        y = go.getY();
        width = go.getWidth();
        high = go.getHigh();
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawRect(x - 10, y - 10, width + 10, high + 10);
        g.setColor(color);
    }
}
