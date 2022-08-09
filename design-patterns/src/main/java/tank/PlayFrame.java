package tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 问题 ： 把属性抽象出来 这些属性应该属性坦克的 不应该出现在游戏界面的属性里面
 * 这个因该用哪个设计模式  这样做好不好
 * 移动的封装成工具类  需要怎么设计  应该是哪个坦克移动 所以参数应该是坦克
 */

public class PlayFrame extends Frame {

    int x = 200;
    int y = 200;

    public PlayFrame(String title) {
        //设置标题
        this.setTitle(title);
        //显示界面
        this.setVisible(true);
        //
        this.setResizable(false);
        //设置大小
//        this.setSize(800, 600);
        //窗口关闭监听事件
        this.addWindowListener(new PlayWindowAdapter());
        //键盘点击事件
        this.addKeyListener(new PlayKeyAdapter());

        //设置位置和大小
        this.setBounds(500, 150, 800, 600);
    }

    @Override
    public void paint(Graphics g) {
        g.drawRect(x, y, 50, 50);

    }

    private class PlayKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            boolean left = false;
            boolean right = false;
            boolean down = false;
            boolean up = false;
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_UP:
                    up = true;
                    break;
            }
            if (left) {
                x -= 10;
            }
            if (right) {
                x += 10;
            }
            if (down) {
                y += 10;
            }
            if (up) {
                y -= 10;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
        }
    }

    private class PlayWindowAdapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

}
