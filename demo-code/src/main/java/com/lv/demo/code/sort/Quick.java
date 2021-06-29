package com.lv.demo.code.sort;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.LinkedList;
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
//        sort(arr, 0, arr.length - 1);
        sort(arr);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(copy);
        boolean equals = Arrays.equals(arr, copy);
        System.out.println(equals);
    }

    /**
     * 非递归实现
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        LinkedList<Pair<Integer, Integer>> linkedList = new LinkedList<>();
        linkedList.add(Pair.of(0, arr.length - 1));
        while (!linkedList.isEmpty()) {
            Pair<Integer, Integer> pair = linkedList.poll();
            int left = pair.getLeft();
            int right = pair.getRight();
            if (left >= right)
                continue;
            int l = left;
            int r = right;
            int value = arr[right];
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
                    if (arr[r] >= value) {
                        r--;
                    } else {
                        swap(arr, l, r);
                        l++;
                        break;
                    }
                }
            }
            linkedList.add(Pair.of(left, l - 1));
            linkedList.add(Pair.of(r + 1, right));
        }
    }

    /**
     * 递归实现
     *
     * @param arr
     * @param left
     * @param right
     */
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
