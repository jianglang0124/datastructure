package com.datastructure.sort;

import java.util.Arrays;
// n*k
public class RadixSort {

    public static void main(String[] args) {
        int arr[] = {53,3,542,748,14,214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

    }
    public  static  void radixSort(int [] arr){

        // 获取最大的shu
        int max =0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max = arr[i];
            }
        }
        // 获取位数
        int arrLength = (max+"").length();
        // 第一轮
        // 定义一个二维数组 表示10个桶 每一个桶 就是一个一维数组
        // 说明
        // 1 二维数组包含10个一维数组
        // 2 为了防止在放入数的时候 数据溢出 则每个一维数组(桶) 大小定义为 arr.length
        // 3 基数排序是用空间换时间的经典算法
        int [][] bucket = new int[10][arr.length];

        // 为了记录每个桶中 实际放了多少的数据 我们定义一个一维数组来记录每个桶每次放入数据的个数
        int[] bucketElementCounts = new int[10];

        for (int l=0,n=1;l<arrLength;l++,n*=10){
            // 放入桶中
            for (int j = 0; j < arr.length; j++) {
                // 取出对应的个位数
                int digitOfElement = arr[j]/n%10;
                //从初始化的时候 bucketElementCounts 默认值都是0
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //  取出
            // 10个桶
            int index=0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 说明 K桶中有元素 放入到原数组
                if (bucketElementCounts[k]!=0){
                    // bucketElementCounts 对应的数值(bucketElementCounts[k]) 就是这个桶中对应数的个数
                    for (int j = 0; j < bucketElementCounts[k]; j++) {
                        arr[index]=bucket[k][j];
                        index++;
                    }
                }
                // 置 0  为了下一轮排序  基数排序！！！！！不要忘记这个
                bucketElementCounts[k]=0;
            }



        }




   /*     // 放入桶中
        for (int j = 0; j < arr.length; j++) {
            // 取出对应的个位数
            int digitOfElement = arr[0]%10;
            //从初始化的时候 bucketElementCounts 默认值都是0
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //  取出
        // 10个桶
        int index=0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 说明 K桶中有元素 放入到原数组
            if (bucketElementCounts[k]!=0){
                // bucketElementCounts 对应的数值(bucketElementCounts[k]) 就是这个桶中对应数的个数
                for (int j = 0; j < bucketElementCounts[k]; j++) {
                    arr[index]=bucket[k][j];
                    index++;
                }
            }
            // 置 0  为了下一轮排序  基数排序！！！！！不要忘记这个
            bucketElementCounts[k]=0;
        }

*/


    }
}
