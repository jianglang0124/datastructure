package com.datastructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // 先创建一个结点111
        HeroNode heroNode1 = new HeroNode(1,"111","111");
        HeroNode heroNode2 = new HeroNode(2,"222","222");
        HeroNode heroNode3 = new HeroNode(3,"333","333");
        HeroNode heroNode4 = new HeroNode(4,"333","333");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        /*singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.list();*/

        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);
        // singleLinkedList.addByOrder(heroNode3);
        // 显示
        System.out.println("链表原来的情况~~~~");
        singleLinkedList.list();

      /*  System.out.println("链表反转的情况~~~~");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();*/
        System.out.println("链表逆序的打印 链表的结构吗该变~~~~");
        reversePrint(singleLinkedList.getHead());



      /*  HeroNode newNode = new HeroNode(1,"111···","111···");
        singleLinkedList.update(newNode);
        System.out.println("链表修改的情况~~~~~");
        singleLinkedList.list();

        // 删除一个结点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("链表删除的情况~~~~~");
        singleLinkedList.list();
        // 测试一个有效结点的个数
        System.out.println("统计有效结点的个数~~~~");
        System.out.println(getLength(singleLinkedList.getHead()));
        // 测试一下看看是否得到了倒数第k个结点
        System.out.println(getLastIndexNode(singleLinkedList.getHead(),2));*/
    }
    // 将单链表逆序打印  将链表的元素压入栈中  利用栈的先进后出的特点 实现单链表的逆序打印
    public static void  reversePrint(HeroNode head){
        if (head.next == null){
            return; // 链表是空的
        }
        HeroNode cur = head.next;  // 辅助指针（辅助结点）
        Stack<HeroNode> stack = new Stack<>();
        while (cur != null){
             stack.push(cur);  // 将结点存入栈中
             cur = cur.next;   // 指针后移
        }
        // 将栈中的元素进行出栈  pop 出栈
        while (stack.size()>0){
            System.out.println(stack.pop());
        }


    }





    // 方法 将单链表的反转  reverse
    // 先定义一个结点 reverHead =  new HeroNode()
    // 从头到尾遍历原来的链表 没遍历一个结点  就将其取出 并放在新的链表reverseHead 的最前端
    // 原来的链表的 head.next= reverseHead.next
    public  static  void reverseList(HeroNode head){
        // 如果当前链表为空 或者只有一个结点 无需反转 直接返回
        if(head.next == null || head.next.next==null){
            return;
        }
        // 定义一个辅助的指针（结点） 来遍历原来的单链表
        HeroNode cur = head.next;
        HeroNode next =null;  // 指向当前节点的下一个结点
        HeroNode reverseHead = new HeroNode(0,"","");
        // 遍历原来的链表 没遍历一个结点 就将其取出 并放在新的链表的reveseHead 的最前端
        // 动脑筋
        while (cur !=null){
            next = cur.next; // 保存当前结点（cur）的下一个结点
            cur.next= reverseHead.next; // 将cur下一个结点指向新的链表 reverseHead 的最前端
            reverseHead.next = cur; // 将cur 连接到新的链表上
            cur=next; // cur后移
        }
        //将原来的头结点的位置指向 reversHead 的next
        head.next = reverseHead.next;
    }

    // 方法 查找单链表中倒数第k个结点
    // 编写一个方法
    public  static  HeroNode getLastIndexNode(HeroNode head, int index){
        if(head.next == null){
            return null;  // 链表为空
        }
        // 获取这个链表的总的个数
        int size=getLength(head);
        // index 的合法性的校验
        if(index <=0 || index>size){
            return  null;
        }
        HeroNode cur = head.next;
        // 通过for 循环 遍历size-index次  就可以得到这个结点
        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }
        return  cur;
    }

    // 方法：统计单链表有效结点的个数  有头结点的话 不参与统计
    /*
    * head 链表的头结点
    * */
    public  static  int getLength(HeroNode head){
        // 判断链表是否为空
        if(head.next == null){
            return 0;
        }
        HeroNode cur = head.next; // 这边表示头结点不参与统计
        int length = 0;
        while (cur !=null){
            length++;
            cur = cur.next;
        }
        return length;
    }


}
// 创建单链表
class SingleLinkedList{


    HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    // 添加结点 不考虑循序的情况下天剑
    public void  add(HeroNode heroNode){
        // 找到最后一个结点  头结点不能动 因此我们需要一个辅助的结点
        HeroNode  temp = head;
            while (true){
                // 找到了链表的最后
                if (temp.next==null){
                    break;
                }
                temp=temp.next;
            }
        // 退出循环的时候 说明已经找到了最后的一个结点
        temp.next =heroNode;
    }

    // 第二种添加英雄的方式 根据排名奖英雄插入到指定的位置
    // 如果有这个排名 则添加失败 并给出提示
    public  void addByOrder(HeroNode heroNode){
        // 因为头结点不能动 所以我们要通过一个辅助的结点来帮助我们找到添加的位置
        // 因为单链表，所以我们找的temp 是位于添加位置的前一个结点 否则插入不了
        HeroNode temp = head;
        boolean flag =false;  // flag 标志添加的编号是否存在 否则插入不了
        while (true){
            if (temp.next==null){ // 说明 temp 已经在链表的最后
                break;
            }
            if(temp.next.no>heroNode.no){ // 位置已经找到 就在temp 后面插入
                break;
            }else  if(temp.next.no==heroNode.no){ // 说明希望添加的heroNode的编号已经存在
                flag =true; // 编号存在
                break;
            }
            temp=temp.next; // 后移 遍历当前的链表
        }

        if(flag){
            System.out.printf("不能添加已经存在=====",heroNode.no);
        }else {
            // 插入到链表中
             heroNode.next=temp.next;
             temp.next=heroNode;
        }
    }
    // 修改结点的信息 根据编号来修改 即 no不能改

    public  void update(HeroNode newHeroNode){
        HeroNode temp = head.next;
        boolean flag =false; // 这个编号的结点是否找到
        while (true){
            if(temp==null){ // 在链表的结尾
                break;
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            System.out.printf("这个结点没有找到======",newHeroNode.no);
        }
    }

    // 删除结点
    // head 不能动 因此我们需要一个temp 辅助的结点来找到删除结点的前一个结点
    // 说明： 我们在比较的时候 是 temp.next.no 和需要删除的结点进行比较
    public  void  del(int no){
        HeroNode temp = head;
        boolean flag = false; // 是否找到待删除的结点的前一个结点
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next; // temp后移
        }
        // 退出循环的时候判断
        if(flag){
            temp.next=temp.next.next;
        }else {
            System.out.println("未能找到这个结点"+no);
        }
    }

    // 打印出链表
    public  void list(){
        // 判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            // 说明链表为空
            if(temp == null){
                break;
            }
            // 打印出结点的信息 指针后移
            System.out.println(temp);
            temp=temp.next;
        }
    }

}

// 创建结点
class HeroNode{

    public  int no;
    public  String name;
    public  String nickName;
    public  HeroNode next;

    public HeroNode(int no,String name,String nickName){
        this.name=name;
        this.no=no;
        this.nickName=nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
