package node;

import util.ArrUtil;

import java.util.Arrays;

public class ArrCode {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(maxValue(arr, 0, 5));
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int[] randomArr = ArrUtil.getRandomArr(100, 100);
            int length = randomArr.length;
            int L = (int) (Math.random() * length);
            int R = (int) (Math.random() * length);
            while (R < L) {
                R = (int) (Math.random() * length);
            }
            if (ArrUtil.checkRangeMaxValue(randomArr, L, R, maxValue(randomArr, L, R))) {
                System.out.println(Arrays.toString(randomArr));
                System.out.println("L:" + L + "--R:" + R);
                break;
            }

        }
    }

    /**
     * 求数组L-R范围的最大值
     */

    public static Integer maxValue(int[] arr, int L, int R) {
        if (arr == null || arr.length == 0 || L > R || L < 0 || R >= arr.length) {
            return null;
        }
        if (L == R) {
            return arr[L];
        }
        if (R - L == 1) {
            return Math.max(arr[L], arr[R]);
        }
        int mid = (L + R) >> 1;
        int leftValue = maxValue(arr, L, mid);
        int rightValue = maxValue(arr, mid + 1, R);
        return Math.max(leftValue, rightValue);
    }

}
