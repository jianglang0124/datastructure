package com.datastructure.sort;

import java.util.Arrays;

// 插入排序
public class InsertSort {

    public static void main(String[] args) {
        int [] arr = {101,34,119,1};
        insertSort(arr);
        System.out.println("======="+Arrays.toString(arr));
    }

    public  static  void  insertSort(int[] arr){
        // 注意: 插入排序（比如第一轮）是从第二个数开始的  第一个数（下标为0）默认是一个有序的序列
        for (int i = 1; i < arr.length; i++) {
            // 定义待插入的数
            int insertVal = arr[i];
            int insertIndex=i-1;  // 从要插入数的前一个位置开始(第一轮就是 arr[1]的前一个数的下标)
            // insertIndex>= 0  保证数组不越界
            // insertVal<arr[insertIndex] 待插入的数绵羊找到待插入的位置
            // 就需要将 arr[insertIndex] 后移
            while (insertIndex>= 0 && insertVal<arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex]; // 将这个数后移
                insertIndex--;
            }
            // 要插入的位置找到了  就是insertIndex+1 的位置
            if(insertIndex +1 != i){
                arr[insertIndex+1]=insertVal;
            }

        }
    }
}
