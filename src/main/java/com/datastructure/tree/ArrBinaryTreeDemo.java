package com.datastructure.tree;
// 顺序存储二叉树
// 应用：
// 堆排序
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
       // arrBinaryTree.preOrder(0);
        arrBinaryTree.preOrder(); // 1 2 4 5 3 6 7
    }
}
// 编写一个 ArrBinaryTree 实现顺序存储二叉树遍历
class  ArrBinaryTree{

    private  int [] arr;
    public  ArrBinaryTree(int []arr){
        this.arr = arr;
    }
    // 重载一下
    public void preOrder(){
        this.preOrder(0);
    }


    // 前序遍历
    // index 是元素对应的数组的下标
    public  void  preOrder(int index){
        if(arr == null || arr.length==0){
            System.out.println("数组为空");
            return;
        }
        // 先输出本身
        System.out.println(arr[index]);
        // 然后向左递归
        if((2*index+1)<arr.length){
            preOrder(2*index+1);
        }
        // 向右递归
        if((2*index+2)<arr.length){
            preOrder(2*index+2);
        }
    }

}
