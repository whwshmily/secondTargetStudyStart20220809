package util;

import game.frame.PlayFrame;

//坦克大战入口
public class MainClass {

    public static final int X = Integer.parseInt(ProjectCache.getValue("frame-x"));
    public static final int Y = Integer.parseInt(ProjectCache.getValue("frame-y"));
    public static final int WIDTH = Integer.parseInt(ProjectCache.getValue("frame-width"));
    public static final int HIGH = Integer.parseInt(ProjectCache.getValue("frame-high"));

    public static void main(String[] args) throws Exception {

        PlayFrame pf = new PlayFrame("tank awt", X, Y, WIDTH, HIGH);

        while (true) {
            Thread.sleep(50);
            pf.repaint();
        }

    }
}
