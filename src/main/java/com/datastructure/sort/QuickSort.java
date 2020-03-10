package com.datastructure.sort;

import java.util.Arrays;

// 挖坑+分治法
// https://blog.csdn.net/morewindows/article/details/6684558
public class QuickSort {
    public static void main(String[] args) {
        int [] arr = {-9,78,0,23,-567,70};
       // quick_sort(arr,0,arr.length-1);
        quickSort(arr,0,arr.length-1);

        System.out.println(Arrays.toString(arr));
    }


    public  static  void   quickSort(int [] arr,int left,int right){

        if(left<right){
            int i=left,j= right, pviot= arr[left];
            while (i<j){
                // 从右向左查找
                while (i<j && pviot<=arr[j]){
                    j--;
                }
                //
                if(i<j){
                    arr[i] = arr[j];  // 将j位置的数填到i位置  j位置形成一个新坑
                    i++;
                }
                // 从左向右排序
                while (i<j && pviot>arr[i]){
                    i++;
                }
                if(i<j){
                    arr[j]= arr[i];
                    j--;
                }
            }
            // 当i==j 的时候
            arr[i]= pviot;
            quickSort(arr,left,i-1);
            quickSort(arr,i+1,right);
        }

    }

    //快速排序
    static  void quick_sort(int s[], int l, int r)
    {
        if (l < r)
        {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j)
            {
                while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if(i < j)
                    s[i++] = s[j];

                while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if(i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }
}
