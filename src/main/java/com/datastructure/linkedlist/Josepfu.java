package com.datastructure.linkedlist;
// 约瑟夫问题  单向的环形链表

public class Josepfu {

    public static void main(String[] args) {
        // 测试一把
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
       // circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1,2,5);
}

}
// 创建一个单向的环形链表
class CircleSingleLinkedList{
    //  先创建first结点 当前没有编号
    Boy first = null;
    // 添加小孩结点 构建成环形列表
    public  void  add( int num){
        // 做的数据的合法性校验
        if(num<1){
            System.out.println("不符合要求");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <=num ; i++) {
            Boy boy = new Boy(i);
            if (i==1){
                first=boy;
                first.setNext(boy); // 构成环
                curBoy = first; // 让curBoy 指向第一个结点
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }
     // 遍历当前的环形链表
    public  void showBoy(){
        if(first == null){
            System.out.println("没有小孩");
            return;
        }
        // 因为first 指针  不能动 所以要借助一个辅助指针来完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号是 %d \n",curBoy.getNo());
            if(curBoy.getNext() == first){
                break;  //  遍历结束
            }
            curBoy= curBoy.getNext();
        }
    }
    // 根据用户的输入 计算出小孩出圈的顺序
    public  void countBoy(int startNo,int countNum,int num){
        // 合法性的校验
        if(first==null ||startNo <1 || startNo >num){
            System.out.println("不合法");
            return;
        }
        Boy helper = first;
        // 使helper 指针移动到first 的前一个位置
        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper=helper.getNext();
        }
        // 报数前 需要将helper 和first 移动到从第几位（startNo）开始报数的位置
        for (int i = 0; i <startNo-1 ; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        // 开始报数时  让first 和 helper  移动 countNum-1次
        while (true){
            // 还有一个结点的时候退出
            if(helper == first){
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            // 出圈
            System.out.printf("小孩的编号 %d出圈\n",first.getNo());
            first= first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后一个出圈的小孩的编号是 %d\n",first.getNo());
    }
}



// 创建一个结点
class Boy{

    private  int  no;
    private  Boy next;

    public  Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}