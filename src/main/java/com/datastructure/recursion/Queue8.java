package com.datastructure.recursion;

public class Queue8 {

    // 定义有多少个皇后
    int max=8;
    // 定义一个数组 array 保存皇后存放的位置
    int [] array = new int[max];
    static  int count = 0;  // 统计解法的数量
    public static void main(String[] args) {
        // 测试一把 8皇后
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("解法的个数======="+count);
    }
    // 编写一个方法  放置第n个皇后
    // 特别注意: check 是每一次递归时 进入到check中都有 for (int i = 0; i < max; i++) 因此会回溯
    private  void check(int n){
        if(n==max){  // n=8 其实8个皇后就已然放好
            print();
            return;
        }
        for (int i = 0; i < max; i++) {   // 一行的位置
            // 把第一个皇后放在第一列
            array[n]=i;
            if (judge(n)){ // 出冲突
                check(n+1);
            }
            // 如果冲突 就继续执行 array[n]=i; 即将第n个皇后放置在 本行的 后移的一个位置(i++)
        }
    }
    /*
       查看我们
       n 表示第n个皇后
    * */
    private  boolean judge(int n){
        for (int i = 0; i <n ; i++) {
            // 说明
            // array[i]==array[n] 表示判断第n个皇后和前面n-1个皇后是否在同一列
            // Math.abs(n-i) == Math.abs(array[n]-array[i]) 判断第n个皇后是否和第i个皇后在同一斜线
            // 判断是否在同一行 没有必要 n每次都是递增的
            if(array[i]==array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return  false;
            }
        }
        return  true;
    }

    // 打印
    private  void print(){
        count++;
        for (int i = 0; i <array.length ; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

}
