package com.vikex.algorithm.sort;

import java.util.Arrays;

public class SortExample {

    public static void main(String[] args) {
        int[] arr = getArray();
        SimpleSort.bubbleSort(arr);
        System.out.println("冒泡排序: " + Arrays.toString(arr));

        arr = getArray();
        SimpleSort.insertSort(arr);
        System.out.println("插入排序: " + Arrays.toString(arr));

    }

    private static int[] getArray() {
        return new int[]{3, 4, 2, 6, 1, 5, 7, 10, 9, 8};
    }
}
