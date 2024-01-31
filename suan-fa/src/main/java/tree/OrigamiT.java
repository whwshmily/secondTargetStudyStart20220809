package tree;

/**
 * 折纸条 第一次折纸 出现一个凹折痕 再次对折 就会在这个折痕的上方出现一个凹折痕 下方出现一个凸折痕
 * 再次对折会在上一次出现的所有折痕上方出现一个凹折痕 下方出现一个凸折痕
 */
public class OrigamiT {

    public static void origamiT(int count, int curTime, String type) {
        if (count == curTime) {
            return;
        }

        origamiT(count, curTime + 1, "凸");
        System.out.println(type);
        origamiT(count, curTime + 1, "凹");
    }

    public static void main(String[] args) {
        origamiT(4, 1, "凹");
    }
}
