package com.datastructure.sort;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        int [] arr= {8,9,1,7,2,3,5,4,6,0};
      //  shellSort(arr);
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }
    // 交换法
    public  static  void shellSort(int [] arr){
        int temp=0;
        int count=0;
        // 逐步分析 使用循环处理
        for (int gap = arr.length/2; gap >0 ; gap /=2) {
            for (int i = gap; i <arr.length; i++) {
                // 遍历各组中的元素(共gap组) )  步长是gap
                for (int j = i-gap; j >=0 ; j-=gap) {
                    // 如果当前的值 大于 加上步长的那个元素 则交换
                    if(arr[j]>arr[j+gap]){
                        temp= arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
            System.out.println("第"+(++count)+"轮的数据"+ Arrays.toString(arr));
        }

    }
    // 对希尔排序做一个优化 ---》移位法
    // gap =2 的初始数据  第1轮的数据[3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
    public  static  void shellSort2(int [] arr){
        for (int gap =arr.length/2 ; gap >0; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                int j =i;
                int temp =arr[j];  // 将待插入的数保存起来  在同一组的数据进行简单的选择排序
                if(arr[j]<arr[j-gap]){
                    // 找位置
                    while (j-gap>= 0 && temp<arr[j-gap]){
                        // 将前面的那个大的值后移
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    // 循环结束 说明找到了位置
                    arr[j]=temp;
                }

            }



        }


    }



}
