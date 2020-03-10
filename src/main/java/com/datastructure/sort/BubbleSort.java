package com.datastructure.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int arr[]={3,9,-1,10,20};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int arr[]){
        int  temp=0;
        // 冒泡排序的 时间复杂度O(n2)
        for (int i=0;i<arr.length-1;i++){
            boolean flag =false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]> arr[j+1]){
                    temp =arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    flag =true;
                }
            }
            // 假设我们现在排序ar[]={1,2,3,4,5,6,7,8,10,9}这组数据，
            // 按照上面的排序方式，第一趟排序后将10和9交换已经有序，接下来的8趟排序就是多余的，什么也没做。所以我们可以在交换的地方加一个标记，如果那一趟排序没有交换元素，说明这组数据已经有序，不用再继续下去。
            if(!flag){
                return;
            }
          /*  System.out.println("第"+(i+1)+"趟排序的后的数组");
            System.out.println(Arrays.toString(arr));*/
        }
    }



}
