package arr;

//基数排序

import util.ArrUtil;

import java.util.Arrays;

/**
 * 有局限性
 * 对样本要求高  非负
 * 先按照个位数大小排序 然后按照 十位 。。。
 * 先准备10队列  当排个位时
 * 当这个数的余数是几 加入到对应的队列中
 * 然后都处理完成后
 * 按照顺序 从0-9 队列 依次弹出值 放入数组中
 * 然后在按照十位排序。。。
 */
public class RadixSort {

    //在数组的L-R位置上排序, digit是最大值有多少位
    private static void radixSort(int[] arr, int L, int R, int digit) {
        if (arr == null || arr.length < 2 || L > R) {
            return;
        }
        int radix = 10;
        int[] count;
        int[] help = new int[R - L + 1];
        for (int i = 1; i <= digit; i++) {
            count = new int[radix];
            /**
             * count[index] 位置存的是当前digit 时 余数时index 的值和比他小的值的和
             */
            for (int j = L; j <= R; j++) {
                int index = getDigit(arr[j], i);
                count[index]++;
            }
            //对比当前index小的值进行累加
            for (int j = 1; j < radix; j++) {
                count[j] = count[j - 1] + count[j];
            }
            /**
             * 想象成存入的都是队列里面
             * 因为是按照数组的顺序进行计算 位 然后存入的队列中
             * 所以假设两个数的余数一样 肯定前面的先放进队列 最后整理的顺序时
             * 后面的数也会在前面的数前面
             * 把累加操作想象成从队列里面取出来放入数组中
             * 前面的会先放 ----
             * 所以从最后往前面计算时  累加的值-1 就应该是你在数组中的位置
             *
             */
            for (int j = R; j >= L; j--) {
                int index = getDigit(arr[j], i);
                help[count[index] - 1] = arr[j];
                count[index]--;
            }
            //整理放入数组
            int index = 0;
            for (int j = L; j <= R; j++) {
                arr[j] = help[index++];
            }
        }
    }

    private static int getDigit(int value, int poi) {
        return ((value / (int) (Math.pow(10, poi - 1))) % 10);
    }

    private static int getTimes(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxValue = Math.max(arr[i], maxValue);
        }
        int times = 0;
        while (maxValue != 0) {
            times++;
            maxValue /= 10;
        }
        return times;
    }

    public static void main(String[] args) {
        int[] randomArr = ArrUtil.getRandomArr(100, 10);
        System.out.println(Arrays.toString(randomArr));
        radixSort(randomArr, 0, randomArr.length - 1, getTimes(randomArr));
        System.out.println(Arrays.toString(randomArr));
        for (int i = 0; i < 10000000; i++) {
            int[] arr = ArrUtil.getRandomArr(100, 10);
            radixSort(arr, 0, arr.length - 1, getTimes(arr));
            if (ArrUtil.checkArrSort(arr)) {
                System.out.println(Arrays.toString(arr));
                break;
            }
        }
        System.out.println("success");
    }
}
