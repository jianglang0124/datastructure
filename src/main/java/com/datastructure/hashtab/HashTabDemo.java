package com.datastructure.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
       // 创建一个 HashTab
        HashTab hashTab = new HashTab(7);

        // 写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add : 添加雇员");
            System.out.println("list : 显示雇员");
            System.out.println("find : 查找雇员");
            System.out.println("del : 删除雇员");
            System.out.println("exit : 退出");
            key =scanner.next();
            switch (key){
                case "add":
                    System.out.println("请输入一个id");
                    int id = scanner.nextInt();
                    System.out.println("请输入一个名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入id");
                    id = scanner.nextInt();
                    hashTab.del(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

// 创建HashTale 管理多条链表
class  HashTab{
    private  EmpLinkedList[] empLinkedListArray;
    private  int size;

    public  HashTab(int size){
        this.size =size;
        empLinkedListArray = new EmpLinkedList[size];
       // int [] arr = new int[5];
        // 留下一个坑  数组中的每一个元素都要初始化 不然就是 null 报错
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }
    // 添加雇员
    public  void  add(Emp emp){
        // 根据员工的id 得到该员工应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }
    // 遍历所有的链表 遍历 hashtab
    public void  list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }
    //查找
    public  void findEmpById(int id){

        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if(emp != null){
            System.out.println("在"+(empLinkedListNo+1)+"条链表中找到该雇员id="+emp.id);
        }else{
            System.out.println("在哈斯表中 没有找到该雇员");
        }
    }
    // 删除
    public  void del(int id){
        int empNo = hashFun(id);
        empLinkedListArray[empNo].del(id);
    }

    // 编写散列函数 使用一个简单的取模法
    public  int hashFun(int id){
        return  id%size;
    }

}






// 表示 一个雇员
class Emp{
    public   int id;
    public  String name;
    public  Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
// 创建 EmpLinkedList 表示链表
    class EmpLinkedList{
    // 头指针 指向第一个Emp 因此我们这个链表的head 是直接指向第一个Emp
    private  Emp head; // 默认是null
    // 添加
    public  void add(Emp emp){

        if(head == null){
            head = emp;
        }else {
            // 如果不是第一个 则使用一个辅助指针（头指针不能动） 帮助定位到最后
            Emp cur =head;
            while (true){
                if(cur.next == null){
                    break;
                }
                cur = cur.next;  // 后移
            }
            // 退出循环后 直接将emp 加入链表
            cur.next = emp;
        }
    }
    // 遍历
    public  void  list(int no){
        if(head == null){
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        System.out.print("第"+(no+1)+"条链表的信息为：");
        Emp curEmp = head;
      while (true){
          System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
          if(curEmp.next == null){
              break;
          }
          curEmp =curEmp.next; // 后移
      }
        System.out.println();
    }

    public Emp findEmpById(int id){
        // 判断链表是否为空
        if(head == null){
            System.out.println("链表为空");
            return null;
        }
         Emp cur = head;  // 辅助指针
        while (true){
            if(cur.id == id){ // 找到
                break;
            }
            if (cur.next ==null){
                cur=null;
                break;
            }
           cur =cur.next;
        }
        return  cur;
    }

    public  void  del(int id){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        if(head.id == id){
            head = head.next;
            return;
        }
        Emp cur = head;
        boolean flag =false;
        while (true){
            if(cur.next ==null){
                break;
            }
            if(cur.next.id == id){
                flag =true;
                break;
            }
            cur = cur.next;
        }
        if(flag){
            cur.next=cur.next.next;
        }else {
            System.out.println("未找到这个结点");
        }



    }

}

