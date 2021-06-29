package com.lv.demo.code.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @desc:
 * @author: huichaolv@tencent.com
 * @create: 2021/6/29
 */
public class Quick {

    public static void main(String[] args) {
        int[] arr = createArr(100);
        int[] copy = Arrays.copyOf(arr, arr.length);
        System.out.println(Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(copy);
        boolean equals = Arrays.equals(arr, copy);
        System.out.println(equals);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int value = arr[right];
        int l = left, r = right;
        while (l < r) {
            while (l < r) {
                if (arr[l] <= value)
                    l++;
                else {
                    swap(arr, l, r);
                    r--;
                    break;
                }
            }
            while (l < r) {
                if (arr[r] >= value)
                    r--;
                else {
                    swap(arr, l, r);
                    l++;
                    break;
                }
            }
        }
        sort(arr, left, l - 1);
        sort(arr, r + 1, right);
    }

    private static void swap(int[] arr, int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }

    private static int[] createArr(int length) {
        int[] arr = new int[length];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }
}
