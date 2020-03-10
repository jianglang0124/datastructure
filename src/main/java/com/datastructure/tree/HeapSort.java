package com.datastructure.tree;

import java.util.Arrays;
//  nlogn  线性对数阶
public class HeapSort {
    public static void main(String[] args) {
        int [] arr= {4,6,8,5,9,90};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));


    }

    public static void  heapSort(int []arr){
        int temp=0;
        /*
        分步完成
        adjust(arr,1,arr.length);
        System.out.println("第一次调整==="+Arrays.toString(arr));

        adjust(arr,0,arr.length);
        System.out.println("第二次调整==="+Arrays.toString(arr));
        */
        // 有 arr.length/2-1 个非叶子结点
        // 将无序的序列构成一个堆 根据升序需求选择大顶堆或者小顶堆
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjust(arr,i,arr.length);
        }
        // 5个数只需要确定4的数就可以
        /*
        *  2. 将堆顶元素和末尾元素交换 将最大的元素 "沉"到数组的末端
        *  3. 重新调整结构 使其满足堆的定义 然后继续交换堆顶元素和当前的末尾的元素 反复执行
        *  调整+交换的步骤 直到整个序列有序
        * */
        for (int j = arr.length-1; j >0 ; j--) {
            // 交换
            temp = arr[j];
            arr[j]= arr[0];
            arr[0]=temp;
            adjust(arr,0,j);
        }
    }


    //  调整
    public  static  void   adjust( int []arr,int i,int length){

        int temp = arr[i]; // 先取出当前元素的值 保存在临时变量中
        // 调整
        // 说明
        // 1。 k=i*2+1 k是i结点的左结点
        for (int k = 2*i+1;k<length;k=2*k+1){
            if(k+1<length && arr[k]<arr[k+1]){ // 说明左结点的值小于右结点 k+1<length 在前面
                k++;
            }
            if(temp<arr[k]){  // 如果子节点大于父节点
                arr[i]=arr[k]; // 把较大的值赋给父节点
                i=k;   // !!!!  i 指向k 继续循环比较
            }else {
                break; // !!!!!
            }
        }
       // 当for循环结束后 我们已经将i 为父节点的树的最大值 放在的最顶部(局部)
        arr[i] =temp; // 将temp值放在调整后的位置
    }
}

