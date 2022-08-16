package game.frame;

import game.bullet.BaseBullet;
import game.check.ImpactCheck;
import game.enumerate.TeamGroup;
import game.strategy.bulletStrategy.BulletStrategy;
import game.strategy.bulletStrategy.DefaultPlayerStrategy;
import game.strategy.bulletStrategy.ThreeBulletStrategy;
import game.tank.BaseTank;
import game.tank.impl.ComputerTank;
import game.tank.impl.PlayerTank;
import util.ProjectCache;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题 ： 把属性抽象出来 这些属性应该属性坦克的 不应该出现在游戏界面的属性里面
 * 这个因该用哪个设计模式  这样做好不好
 * 移动的封装成工具类  需要怎么设计  应该是哪个坦克移动 所以参数应该是坦克
 */

public class PlayFrame extends Frame {

    private static final int X = Integer.parseInt(ProjectCache.getValue("frame-x"));
    private static final int Y = Integer.parseInt(ProjectCache.getValue("frame-y"));
    private static final int WIDTH = Integer.parseInt(ProjectCache.getValue("frame-width"));
    private static final int HIGH = Integer.parseInt(ProjectCache.getValue("frame-high"));

    private static final BulletStrategy<BaseTank> STRATEGY1 = new DefaultPlayerStrategy();
    private static final BulletStrategy<BaseTank> STRATEGY2 = new ThreeBulletStrategy();

    private ImpactCheck impactCheck;

    PlayerTank player1 = new PlayerTank(TeamGroup.PLAYER);

    List<BaseTank> computerTank = new ArrayList<BaseTank>(Integer.parseInt(ProjectCache.getValue("computer-tank-live-num")));

    private final List<BaseBullet> BULLETS = new ArrayList<BaseBullet>();

    private volatile int createdComputedTankCounts = 0;

    private boolean isGameOver = false;

    public PlayFrame(String title, ImpactCheck impactCheck) {
        this.impactCheck = impactCheck;
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

//        this.setBackground(Color.black);

        //设置位置和大小
        this.setBounds(X, Y, WIDTH, HIGH);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量：" + BULLETS.size(), 30, 50);
        g.drawString("剩余敌人坦克数量：" + (20 - createdComputedTankCounts), 30, 70);
        g.drawString("剩余命数：" + player1.getLiveNum(), 30, 90);
        g.drawString("场上tank数量：" + computerTank.size(), 30, 110);
        g.setColor(color);
        if (isGameOver) {
            g.drawString("GAME_OVER", 500, 300);
        }
        for (int j = 0; j < computerTank.size(); j++) {
            BaseTank baseTank = computerTank.get(j);
            boolean collide1 = impactCheck.isCollide(baseTank, player1);
            if (collide1) {
                player1.setDie(true);
            }
            for (int i = 0; i < BULLETS.size(); i++) {
                BaseBullet bullet = BULLETS.get(i);
                if (!bullet.getTeamGroup().equals(player1.getTeamGroup())) {
                    boolean collide = impactCheck.isCollide(player1, bullet);
                    if (collide) {
                        bullet.setDie(true);
                        player1.setDie(true);
                    }
                }
                if (!bullet.getTeamGroup().equals(baseTank.getTeamGroup())) {
                    boolean collide = impactCheck.isCollide(baseTank, bullet);
                    if (collide) {
                        bullet.setDie(true);
                        baseTank.setDie(true);
                        computerTank.remove(baseTank);
                    }
                }
            }

        }

        for (int i = 0; i < BULLETS.size(); i++) {
            BaseBullet bullet = BULLETS.get(i);
            for (int j = 0; j < BULLETS.size(); j++) {
                BaseBullet bullet1 = BULLETS.get(j);
                if (!bullet.getTeamGroup().equals(bullet1.getTeamGroup())) {
                    boolean collide = impactCheck.isCollide(bullet1, bullet);
                    if (collide) {
                        bullet.setDie(true);
                        bullet1.setDie(true);
                    }
                }
            }
        }

        if (!player1.isDie()) {
            player1.paint(g);
        }
        for (int i = 0; i < BULLETS.size(); i++) {
            BaseBullet bullet = BULLETS.get(i);
            if (bullet.isDie()) {
                BULLETS.remove(i);
                continue;
            }
            bullet.paint(g);
        }
        for (int i = 0; i < computerTank.size(); i++) {
            BaseTank baseTank = computerTank.get(i);
            baseTank.paint(g);
        }
    }

    //消除闪烁
    Image image = null;

    @Override
    public void update(Graphics g) {
        if (image == null) image = this.createImage(WIDTH, HIGH);
        Graphics graphics = image.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HIGH);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(image, 0, 0, null);
    }


    public void gameDetection() {
        if (player1.isDie() && player1.getLiveNum() > 0) {
            player1.setLiveNum(player1.getLiveNum() - 1);
            player1.resurrection();
            player1.setDie(false);
        } else if (player1.isDie() && player1.getLiveNum() == 0) {
            this.isGameOver = true;
        }

        //此处可以改成策略模式 小于几个的时候生成tank数量不一样
        if (createdComputedTankCounts == 20) {
            return;
        }
        if (computerTank.size() < 6) {
            createdComputedTankCounts++;
            computerTank.add(new ComputerTank(TeamGroup.COMPUTER, BULLETS));
        }
    }

    public int getCreatedComputedTankCounts() {
        return createdComputedTankCounts;
    }

    private class PlayKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            operateTank(e, 1);
            player1.move();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            operateTank(e, 0);
        }

        private void operateTank(KeyEvent e, int type) {
            boolean result = type == 1;
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    player1.setLeft(result);
                    break;
                case KeyEvent.VK_RIGHT:
                    player1.setRight(result);
                    break;
                case KeyEvent.VK_DOWN:
                    player1.setDown(result);
                    break;
                case KeyEvent.VK_UP:
                    player1.setUp(result);
                    break;
                case KeyEvent.VK_SPACE:
                    player1.fire(BULLETS, getBulletStrategy(player1));
                    break;
            }
        }

    }

    private class PlayWindowAdapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    private BulletStrategy<BaseTank> getBulletStrategy(BaseTank tank) {
        //其实这个random 应该从参数tank中获取 tank可能吃一些道具 改变子弹 获取标志位生成不同的策略
        int random = (int) (Math.random() * 2);
        return random == 0 ? STRATEGY1 : STRATEGY2;
    }

}
