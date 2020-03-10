package com.datastructure.queue;

import java.util.Scanner;

public class CircleQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例~~~~~~");
        // 测试环形队列
        CircleArray queue = new CircleArray(4); // 说明设置4 期队列的有效数据是3
        char key = ' ';// 接收用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s:显示队列");
            System.out.println("e:退出");
            System.out.println("a:添加数据到队列");
            System.out.println("g:从队列中取出数据");
            System.out.println("h:查看队列的头的数据");
            key = scanner.next().charAt(0); // 接收一个字符
            switch (key) {
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
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        ;
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
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

class CircleArray {

    private int maxSize; // 表示数组的最大的容量
    // font 变量的含义做了一个调整： font  就是指向队列的第一个元素  也就是说 arr[font] 就是队列放入第一个元素
    // font的初始值为0
    private int font; // 队列头
    // rear  变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置 因为希望空出一个空间作为约定
    // rear 的初始值为0
    private int rear; // 队列尾
    private int[] arr; // 用于存放数据 模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    // 队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == font;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == font;
    }

    public void addQueue(int n) {
        // 判断队列是否是空
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        // 直接将数据插入
        arr[rear] = n;
        // 将rear 后移  这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    // 获取队列的数据 出队列
    public int getQueue() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列空 不能去数据");
        }
        //  这里需要分析出 font 指向队列的第一个元素
        // 1 先把font 对应的值取出开 保存到一个临时变量
        // 2 将 font 后移 考虑取模
        // 3 将临时的变量返回
        int value = arr[font];
        font = (font + 1) % maxSize;
        return value;
    }

    // 显示队列的所有的数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        // 思路： 从 font 开始遍历 遍历多少个元素
        for (int i = font; i < font + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前队列的有效的个数
    public int size() {
        // font=1 rear =2 maxSize=3
        return (rear + maxSize - font) % maxSize;
    }

    // 显示队列的头数据  注意不是取数据
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空 不能去数据");
        }
        return arr[font];
    }


}
