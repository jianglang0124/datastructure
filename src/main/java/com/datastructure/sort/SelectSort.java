package com.datastructure.sort;

import java.util.Arrays;

// 选择排序
 //1 选择排序一共进行数组-1 轮排序
 //2 每一轮排序 又是一个循环 循环的规则
//  2.1 先假定当前是最小数
 // 2.2 然后和后面的每一个数进行比较 如果发现有更小的数 就重新确定最小的数 并得到下标
 // 2.3 当遍历到数据的最后时候 就得到本轮最小数和下标‘
 //  2.4 交换

public class SelectSort {

    public static void main(String[] args) {
        int [] arr = {101,34,119,1};
        selectSort(arr);
        System.out.println("Arr===="+ Arrays.toString(arr));
    }

    public  static  void selectSort(int [] arr){
        // 算法  先简单后复杂
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            int  min = arr[minIndex];
            for (int j= i+1;j<arr.length;j++){
                if(arr[j]<min){           //说明假定的值不是最小值
                    minIndex = j;        //      重置 minIndex
                    min =arr[j];         //     重置min      c
                }
            }
            // 如果最小值的下标和假设的下表不一致
            if(minIndex !=i){
                arr[minIndex] =arr[i];
                arr[i]=min;
            }


        }




    }
}
