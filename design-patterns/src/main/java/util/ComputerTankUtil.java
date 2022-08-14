package util;

import game.enumerate.Dir;

public class ComputerTankUtil {
    private static final int X1 = Integer.parseInt(ProjectCache.getValue("computer-tank-x1"));
    private static final int X2 = Integer.parseInt(ProjectCache.getValue("computer-tank-x2"));
    private static final int X3 = Integer.parseInt(ProjectCache.getValue("computer-tank-x3"));
    private static final int SPEED1 = Integer.parseInt(ProjectCache.getValue("computer-tank-speed1"));
    private static final int SPEED2 = Integer.parseInt(ProjectCache.getValue("computer-tank-speed2"));
    private static final int SPEED3 = Integer.parseInt(ProjectCache.getValue("computer-tank-speed3"));
    private static final int IS_CHANGE_DIR = Integer.parseInt(ProjectCache.getValue("computer-tank-change-dir"));
    private static final int IS_FIRE = Integer.parseInt(ProjectCache.getValue("computer-tank-fire"));

    //等概率的获取[0-number)之间的一个整数
    private static int getRandomNumber(int number) {
        return (int) (Math.random() * number);
    }

    private static Dir getDir() {
        int num = getRandomNumber(4);
        switch (num) {
            case 0:
                return Dir.UP;
            case 1:
                return Dir.RIGHT;
            case 3:
                return Dir.LEFT;
            default:
                return Dir.DOWN;
        }
    }

    public static Dir changeTankDir() {
        int randomNumber = getRandomNumber(10);
        if (randomNumber > IS_CHANGE_DIR) {
            return getDir();
        }
        return null;
    }

    public static int getX(int width) {
        int num = getRandomNumber(3);
        return num == 0 ? X1 : (num == 1 ? X1 - width / 2 : X3 - width);
    }

    public static boolean isFire() {
        return getRandomNumber(10) > IS_FIRE;
    }

    public static int getSpeed() {
        int num = getRandomNumber(3);
        return num == 0 ? SPEED1 : (num == 1 ? SPEED2 : SPEED3);
    }

}
