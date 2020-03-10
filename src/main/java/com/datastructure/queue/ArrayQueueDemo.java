package com.datastructure.queue;

import java.util.Scanner;
/*
*   todo
*   将这个数据使用算法 改进成一个环形队列 取模 %
*
* */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        // 测试
        ArrayQueue queue = new ArrayQueue(3);
        char key= ' ';// 接收用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s:显示队列");
            System.out.println("e:退出");
            System.out.println("a:添加数据到队列");
            System.out.println("g:从队列中取出数据");
            System.out.println("h:查看队列的头的数据");
            key= scanner.next().charAt(0); // 接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res= queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res= queue.headQueue();;
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                 default:
                     break;
            }
        }
        System.out.println("程序退出======");

    }


}
// 使用数组模拟一个队列---编写一个ArrayQueue的类
class ArrayQueue{

    private  int  maxSize; // 表示数组的最大的容量
    private  int  font; // 队列头
    private  int rear; // 队列尾
    private  int [] arr; // 用于存放数据 模拟队列
    // 创建 队列的构造器
    public  ArrayQueue(int arrMaxSize){
        maxSize =arrMaxSize;
        arr= new int[maxSize];
        font=-1;  // 指向队列头部 分析font 是指向队列头的前一个位置
        rear=-1;  // 指向队列尾 指向队列尾的数据（即就是队列最后一个数据）
    }
    // 队列是否满
    public  boolean isFull(){
        return  rear==maxSize-1;
    }
    // 判断队列是否为空
    public  boolean isEmpty(){
        return  rear==font;
    }
    public  void addQueue(int n){
        // 判断队列是否是空
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        rear++; //让rear 后移
        arr[rear]=n;
    }
    // 获取队列的数据 出队列
    public int getQueue(){
        if(isEmpty()){
            // 抛出异常
            throw  new RuntimeException("队列空 不能去数据");
        }
        font++; //  font 后移
        return  arr[font];
    }
    // 显示队列的所有的数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    // 显示队列的头数据  注意不是取数据
    public  int headQueue(){
        // 判断
        if(isEmpty()){
            throw  new RuntimeException("队列空 不能去数据");
        }
        return  arr[font+1];
    }



}