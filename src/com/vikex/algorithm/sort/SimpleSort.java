package com.vikex.algorithm.sort;

public class SimpleSort {

    // 冒泡排序，总的平均时间复杂度为：O(n2) 。
    // 依次比较相邻的两个数，将小数放在前面，大数放在后面。即在第一趟：首先比较第1个和第2个数，将小数放前，大数放后。
    // 然后比较第2个数和第3个数，将小数放前，大数放后，如此继续，直至比较最后两个数，将小数放前，大数放后。重复第一
    // 趟步骤，直至全部排序完成。
    public static void bubbleSort(int[] numbers) {
        int temp;
        // 外层循环控制排序趟数
        for (int i = 0; i < numbers.length - 1; i++) {
            // 内层循环控制每一趟排序多少次
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }

    /**
     *  插入排序
     *  从第二个元素开始，将其作为要插入值。取出要插入值的下一个元素，在已经排序的元素序列中从后向前扫描。
     *  如果前面元素大于要插入的值，则将前面元素移到下一个位置。
     *  例如
     *      3 2 4 1 5 7 6
     *  第一次排序 要插入的值2  和3比较  将3移动到2的地方，将2放到index=0的位置
     *      2 3 4 1 5 7 6
     *  第二次排序 要插入的值4  和3比较  比3大，直接跳出循环， 放到自己的位置即可
     *      2 3 4 1 5 7 6
     *    ……
     *
     */
    public static void insertSort(int[] numbers) {

        for (int i = 1; i < numbers.length; i++) {
            int temp = numbers[i];
            int j = i -1;
            for (; j >= 0; j--) {
                if (numbers[j] > temp) {
                    numbers[j + 1] = numbers[j];
                    continue;
                }

                break;
            }

            numbers[j + 1] = temp;
        }
    }

    //快速排序，时间复杂度O(nlogn)
    public static void quickSort(int[] numbers, int low, int high) {
        int l = low;
        int h = high;
        //将第一个值作为轴心值
        int pivot = numbers[low];
        while (l < h) {
            //从最后一个元素向前查找比轴心值小的元素
            while (l < h && numbers[h] >= pivot) {
                h--;
            }

            //将所找到的小于轴心值得元素和轴心值进行互换
            if (l < h) {
                int tmp = numbers[h];
                numbers[h] = numbers[l];
                numbers[l] = tmp;
                l++;
            }

            //从第一个元素开始向后查找比轴心值大的元素
            while (l < h && numbers[l] <= pivot) {
                l++;
            }

            //将所找到的大于轴心值得元素和轴心值进行互换
            if (l < h) {
                int tmp = numbers[h];
                numbers[h] = numbers[l];
                numbers[l] = tmp;
                h--;
            }
        }

        /*
         * 将轴心值前面和后面的部分进行迭代排序
         */
        if (l > low) {
            quickSort(numbers, low, l - 1);
        }
        if (h < high) {
            quickSort(numbers, l + 1, high);
        }
    }

}
