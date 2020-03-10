package com.datastructure.sort;

import java.util.Arrays;

// 归并算法
public class MergeSort {
    public static void main(String[] args) {
        int arr[]= {8,4,5,7,1,3,6,2};
        int temp[] = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));

    }
    // 分+合的方法
    public static  void  mergeSort(int [] arr,int left,int right, int[]temp){

        if(left<right){
            int mid= (left+right)/2;
            // 向左递归
            mergeSort(arr,left,mid,temp);
            // 向右递归
            mergeSort(arr,mid+1,right,temp);
            // 合并
            merge(arr,left,right,mid,temp);
        }


    }

    /*
    *  arr 排序的原始的数组
    *  left 左边有序序列的初始索引
    *  mid  中间索引
    *  right 右边索引
    *  temp 做中转的数组
    *
    * */
    public  static  void merge(int []arr, int left,int right,int mid,int [] temp){
        int i= left;
        int j=mid+1;
        int t=0;
        // 一 先把左右两边有序的数据按照规则填充到temp数组中
        // 直到左右两边的有序序列 有一个处理完毕为止
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t]=arr[i];
                i++;
                t++;
            }else {
                temp[t]=arr[j];
                j++;
                t++;
            }
        }
        // 二 将剩余的数据依次拷贝要数组中
        while (i<=mid){
            temp[t]=arr[i];
            i++;
            t++;
        }

        while (j<=right){
            temp[t]=arr[j];
            j++;
            t++;
        }
        // 三 将temp数组元素拷贝要arr
        // 注意 并不是每次都拷贝所有
        // 最后一次 tempLeft= 0 right = 7
        t=0;
        int tempLeft = left;
        while (tempLeft<=right){
            arr[tempLeft]= temp[t];
            t++;
            tempLeft++;
        }
    }
}
