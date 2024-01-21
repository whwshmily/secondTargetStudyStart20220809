package util;

public class ArrUtil {


    public static int[] getRandomArr(int maxValue, int maxLength) {
        int length = (int) (Math.random() * maxLength);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            int value = (int) (Math.random() * maxValue);
            arr[i] = value;
        }
        return arr;
    }


    public static boolean checkRangeMaxValue(int[] arr, int L, int R, Integer maxValue) {
        if (arr == null || L < 0 || R >= arr.length || maxValue == null) {
            return false;
        }
        int value = arr[L];
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] > value) {
                value = arr[i];
            }
        }
        return maxValue != value;
    }

    public static boolean checkArrSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return false;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return true;
            }
        }
        return false;
    }

}
