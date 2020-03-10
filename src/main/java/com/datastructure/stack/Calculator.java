package com.datastructure.stack;

public class Calculator {

    public static void main(String[] args) {
        // 根据思路 完成表达式的计算


    }

}
class ArrayStack2{

    private  int  maxSize;  // 栈的容量
    private  int [] stack; // 用数组模拟栈  存放数据
    private  int top =-1; // 栈顶

    public ArrayStack2(int maxSize){
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

    // 判断优先级 操作符的优先级越大 数值就越大
    public  int  priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '-' || oper == '+'){
            return  0;
        }else {
            return  -1;
        }
    }
    // 判断是不是运算符
    public  boolean isOper(char oper){
        return oper == '/' || oper == '*' || oper == '-' || oper =='+';
    }
    public  int cal(int num1,int num2,char oper){
        int  res = 0;
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
             default:
                 break;
        }
        return  res;
    }

}



