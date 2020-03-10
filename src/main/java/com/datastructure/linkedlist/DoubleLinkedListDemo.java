package com.datastructure.linkedlist;

// 双向环形列表

public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        System.out.println("双向链表的测试");

        // 测试
        HeroNode2 heroNode1 = new HeroNode2(1,"111","111");
        HeroNode2 heroNode2 = new HeroNode2(2,"222","222");
        HeroNode2 heroNode3 = new HeroNode2(3,"333","333");
        HeroNode2 heroNode4 = new HeroNode2(4,"444","4444");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        doubleLinkedList.list();
        // 修改
        HeroNode2 newHeroNode = new HeroNode2(3,"33","33");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的~~~~~");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的====");
        doubleLinkedList.list();




    }
}

class  DoubleLinkedList{
    HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }
    // 打印出链表
    public  void list(){
        // 判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
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


    // 添加结点到双向链表的最后
    public void  add(HeroNode2 heroNode){
        // 找到最后一个结点  头结点不能动 因此我们需要一个辅助的结点
        HeroNode2  temp = head;
        while (true){
            // 找到了链表的最后
            if (temp.next==null){
                break;
            }
            temp=temp.next;
        }
        // 退出循环的时候 说明已经找到了最后的一个结点
        temp.next =heroNode;
        heroNode.pre =temp;
    }

    // 修改一个双向链表结点的内容   和单向链表的结点修改一样
    public  void update(HeroNode2 newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
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
    // 从双向链表的删除一个结点
    // f对于双向链表 我们可以直接找到要删除的结点
    // 找到后 自我删除即可
    public  void  del(int no){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false; // 是否找到待删除的结点的前一个结点
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next; // temp后移
        }
        // 退出循环的时候判断
        if(flag){
           // temp.next=temp.next.next;
            temp.pre.next = temp.next;
            //  这里需要注意一下
            //  如果是最后的一个结点 就不需要执行下面的这句话 否则会包空指针异常
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("未能找到这个结点"+no);
        }
    }





}

// 创建结点
class HeroNode2{

    public  int no;
    public  String name;
    public  String nickName;
    public  HeroNode2 next;  // 指向下一个结点 默认是 null
    public  HeroNode2 pre;   // 指向上一个结点 默认是 null

    public HeroNode2(int no,String name,String nickName){
        this.name=name;
        this.no=no;
        this.nickName=nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}



