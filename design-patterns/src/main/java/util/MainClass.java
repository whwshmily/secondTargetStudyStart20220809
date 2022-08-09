package util;

import tank.PlayFrame;

//坦克大战入口
public class MainClass {
    public static void main(String[] args) throws Exception{

        PlayFrame pf = new PlayFrame("tank awt");

        while (true){
            Thread.sleep(50);
            pf.repaint();
        }

    }
}
