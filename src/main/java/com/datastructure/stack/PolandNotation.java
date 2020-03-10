package com.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
*   逆波兰表达式的计算     借助栈
*   中缀转后缀 然后后缀计算出结果
*
* */
public class PolandNotation {

    public static void main(String[] args) {

        // 中缀表达式 转 后缀表达式
        // 说明 1+((2+3)*4)-5
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println("list========"+list);
        // 将得到的中缀表达式对应的list  转换成 后缀表达式的list
        // [1, +, (, (, 20, +, 3, ), *, 4, ), -, 5] ====>
        // 1 2 3 + 4 * + 5 -
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println("list1========="+list1);
        int cal = cal(list1);
        System.out.println("计算的结果是====="+cal);

        // 先定义一个逆波兰表达式 空格隔开
        // （30+4）*5-6 ===》 30 4 + 5 * 6 - ==> 29
      /*  String suffixExpression = "30 4 + 5 * 6 -";
        // 为了读取方便 将表达式放在一个 ArrayList
        List<String> listString = getListString(suffixExpression);
        System.out.println("list====="+listString);
        int cal = cal(listString);
        System.out.println("计算的结果是====="+cal);*/


    }
    // 方法 将得到的中缀表达式对应的list  转换成 后缀表达式的list
    public static  List<String> parseSuffixExpressionList(List<String> ls){
        // 定义两个栈
        Stack<String> s1= new Stack<String>();

        //  因为s2 没有pop 操作 且后面我们要求是逆序输出 所以 用一个list 代替栈进行存储的操作
        //  Stack<String> s2= new Stack<String>();
        List<String> s2 = new ArrayList<>();
        // 扫描这个中缀表达式
        for (String item : ls) {
            // 如果是操作数就直接加入到 s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                // 如果是左括号的时候 直接压入s1
                s1.push(item);
            } else if (item.equals(")")) {
                // 当遇到右括号的时候 将s1 pop 加入到s2 知道遇到 左括号为止'
                while (!s1.peek().equals("(") && s1.size() > 0) {
                    s2.add(s1.pop());
                }
                // 将这一对括号消除 就是将与之匹配的左括号删除
                s1.pop();
            } else {
                // 如果item 的优先级小于等于s1的栈顶运算符 将s1的栈顶运算符加入s2 再次4.1
                // 与s1中的新的栈顶运算符相比较
                while (s1.size() > 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 将这个优先级小的运算符压栈
                s1.push(item);
            }
        }
        // 遍历结束后 将s1 剩余的元素依次加入到 s2 中
        while (s1.size() > 0) {
            s2.add(s1.pop());
        }
        return  s2;
    }



    // 方法：将中缀表达式 转换成对应的List
    //
    public static List<String> toInfixExpressionList(String s){

        // S:1+((2+3)*4)-5
        List<String> ls = new ArrayList<>();
        int i =0;
        String ch;
        // 扫描 这个字符串
        do{
            // 使用的的是asc码  非数字的情况下
            if(s.charAt(i)<48 || s.charAt(i)>57){
                ls.add(""+s.charAt(i) );
                i++;
            }
            // 是数字的时候要考虑 多位数的情况  做一个拼接
            else {
                // 拼接之前要先清空
                ch= "";
                while (i<s.length() && s.charAt(i)>=48 && s.charAt(i)<=57){
                    ch += s.charAt(i);
                    i++;
                }
                // 循环结束 多位数已经拼接好
                ls.add(ch);
            }
        }while (i<s.length());

        return  ls;
    }

    public static List<String>  getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele :split){
            list.add(ele);
        }
        return  list;
    }
    // 开始计算
    // 思路 从左向右扫描集合 如果是数字 就入栈  如果是运算符 出栈顶的两个元素进行运算 在入栈 最后栈顶的元素就是结果
    public  static int cal(List<String> list){
        // 借助一个栈来实现
        Stack<String> stack = new Stack<>();
        // 遍历这个集合
        for(String ele : list){
            // 判断是不是数字 采用的是正则表达式
            if(ele.matches("\\d+")){
                stack.push(ele);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(ele.equals("+")){
                    res =num1+num2;
                }else  if(ele.equals("-")){
                    res = num1 - num2;
                }else if(ele.equals("*")){
                    res = num1 * num2;
                }else if(ele.equals("/")){
                    res = num1/num2;
                }
                stack.push(String.valueOf(res));
            }
        }
        return  Integer.parseInt(stack.pop());
    }

}
class  Operation{
    private  static int ADD =1 ;
    private  static int SUB =1;
    private  static int MUL =2;
    private  static int DIV =2;
    public  static  int getValue(String ess){
        int res= 0;
        switch (ess){
            case "+":
                res=ADD;
                break;
            case "-":
                res=SUB;
                break;
            case "*":
                res=MUL;
                break;
            case "/":
                res=DIV;
                break;
            default:
                break;

        }
        return  res;
    }
}
