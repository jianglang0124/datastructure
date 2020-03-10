package com.datastructure.stack;

import java.util.Scanner;
/*
*  数组模拟栈 的思路分析图
  定义一个top  来表示栈顶 初始化 为 -1
  入栈的操作 当有数据加入到栈的时候 top++；stac[top]=data;
  出栈的操作 int value=stack[top];top--;
  课后练习 ： 链表模拟栈
* */
public class ArrayStackDemo {

    public static void main(String[] args) {
        // 测试一下
        ArrayStack stack = new ArrayStack(4);
        String key ="";
        boolean loop = true; // 控制退出
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show：显示栈的元素");
            System.out.println("exit：退出");
            System.out.println("pop：出栈");
            System.out.println("push：入栈");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int res = stack.pop();
                        System.out.printf("出栈的元素是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    // 流关闭
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出了");

    }
}
class ArrayStack{

    private  int  maxSize;  // 栈的容量
    private  int [] stack; // 用数组模拟栈  存放数据
    private  int top =-1; // 栈顶

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    // 栈满
    public  boolean isFull(){
        return  top == maxSize-1;
    }
    // 栈空
    public  boolean isEmpty(){
        return  top== -1;
    }
    // 入栈
    public  void  push(int value){
        // 判断栈是否为满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }
    // 出栈
    public  int pop(){
        if(isEmpty()){
            throw  new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    // 遍历 从栈顶的元素输出
    public  void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]的值为%d\n",i,stack[i]);

        }
    }





}