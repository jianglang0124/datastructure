package com.datastructure.search;

import java.util.ArrayList;
import java.util.List;

// 二分查找的前提是： 该数组是有序的
public class BinarySearch {
    public static void main(String[] args) {
        int arr []={1,8,10,89,1000,1000,1000,1234};
        /*int res = binarySearch(arr,0,arr.length-1,88);
        System.out.println(res);*/
        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(integers);
    }

    // 二分查找的算法

    public  static  int  binarySearch(int []arr,int left,int right,int findVal){

        // 没有找到的情况 也就是递归结束的条件
        if(left>right){
            return  -1;
        }
        int  mid = (left+right)/2;
        int  midValue= arr[mid];
        if(findVal<midValue){  // 往左递归
            return  binarySearch(arr,left,mid-1,findVal);
        }else  if(findVal>midValue){
            return  binarySearch(arr,mid+1,right,findVal);
        }else {
            return  mid;
        }
    }
    // 完成一个课后的思考题  将所有的满足的值全部查找出来
    public static List<Integer> binarySearch2(int []arr,int left,int right,int findVal){
        if(left>right){
            return  new ArrayList<>();
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if(findVal<midVal){
            return binarySearch2(arr,left,mid+1,findVal);
        }else if(findVal>midVal){
            return binarySearch2(arr,mid+1,right,findVal);
        }else {
            ArrayList<Integer>  result = new ArrayList<>();
           // 向右扫描
            int temp = mid+1;
            while (true){
                if(temp>right || arr[temp]!=findVal){
                    break;
                }
                result.add(temp);
                temp++;

            }
            // 将找到这个值 添加到集合中
            result.add(mid);
            // 从mid索引值 向左扫描
            temp= mid-1;
            while (true){
                if(mid<0 || arr[temp]!=findVal){
                    break;
                }
                result.add(temp);
                temp--;
            }
            // 返回
            return result;
        }
    }

}
