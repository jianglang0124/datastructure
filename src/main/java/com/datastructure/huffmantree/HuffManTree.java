package com.datastructure.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffManTree {
    public static void main(String[] args) {
        int [] arr= {13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);


    }
    public static  void  preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空");
        }
    }


    // 创建赫夫曼树
    /*
       arr 需要创建的哈弗曼树的数组
       return 创建好的赫夫曼树的根节点
     */
    public  static  Node  createHuffmanTree(int [] arr){

         // 第一步为了操作方便
        // 将这个数组放在一个arrayList 中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr ){
            nodes.add(new Node(value));
        }
        Collections.sort(nodes);
        System.out.println("nodes===="+nodes);
        // 构建huffman数 最后集合中只剩下一个结点 根节点
        while (nodes.size()>1){

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value+rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
            Collections.sort(nodes);
        }
        return nodes.get(0);

    }
}





// 创建结点
// 为了让Node 对象使用排序 Collections 集合排序
// 让Node 实现Comparable 接口
class  Node implements Comparable<Node>{

    int value; // 结点的权值
    Node  left;  // 指向左子节点
    Node right ;  // 指向 右子节点
    // 构造方法
    public Node(int value){
        this.value = value;
    }
    // 前序遍历
    public  void  preOrder(){

        System.out.println(this.value);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }

    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到大排序
        return this.value - o.value;
    }
}