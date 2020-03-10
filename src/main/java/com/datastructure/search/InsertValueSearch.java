package com.datastructure.search;
// 插值查找的算法
// 适用的条件  对于数据量比较大 关键字分布比较均匀(跳跃性不大)的查找表来说
//
public class InsertValueSearch {

    public static void main(String[] args) {
        int [] arr = new int[100];
        for (int i = 0; i <100 ; i++) {
            arr[i]=i+1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(i);


    }
    // 编写插值查找的算法
    // 插值查找算法也要求数组是有序的
    public  static  int  insertValueSearch(int []arr, int left,int right,int findVal){

        // 递归结束的条件
        //  arr[0]<findVal || arr[arr.length-1]<findVal
        // 否则我们得到的mid可能越界
        if(left>right || arr[0]>findVal || arr[arr.length-1]<findVal){
            return  -1;
        }
        int mid = left+(right -left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal = arr[mid];
        if(arr[mid]<findVal){  // 右
            return  insertValueSearch(arr,mid+1,right,findVal);
        }else if (arr[mid]>findVal){  // 左
            return  insertValueSearch(arr,left,mid-1,findVal);
        }  else {
            return  mid;
        }

    }
}
