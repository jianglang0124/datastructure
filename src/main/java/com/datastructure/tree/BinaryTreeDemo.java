package com.datastructure.tree;

public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4= new HeroNode(4,"林冲");
        HeroNode node5= new HeroNode(5,"关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        // 设置根结点
        binaryTree.setRoot(root);
        // 前序遍历
     /*   System.out.println("前序遍历"); // 1 2 3 4
        binaryTree.preOrder();
        System.out.println("中序遍历"); // 2 1 3 4
        binaryTree.infixOrder();
        System.out.println("后序遍历"); // 2 4 3 1
        binaryTree.postOrder();

        //  前序遍历查找
        System.out.println("前序遍历查找========");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if(resNode != null){
            System.out.println(resNode.getName());
        }else {
            System.out.println("没有找到");
        }*/

       // 测试一下删除结点  递归debug
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder(); //  1 2 3 5 4
        binaryTree.delNode(5);
        System.out.println("删除后 前序遍历");
        binaryTree.preOrder(); // 1 2 3 4

    }
}

// 定义一个 BinaryTreeDemo
class  BinaryTree{

    // 根节点的设置
    private  HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    // 删除结点
    public  void delNode(int no){
        // 判断是不是空树
        if(root !=null){
            // 如果只有一个root结点 这里立即判断root是不是就是要删除的结点
            if(root.getNo() ==no){
                root = null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("二叉树为空 不能删除");
        }
    }

    // 前序遍历
    public  void  preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 中序遍历
    public  void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 后续遍历
    public  void  postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 前序遍历查找
    public  HeroNode preOrderSearch(int no){
        if(root != null){
            return  root.preOrderSearch(no);
        }else {
            return  null;
        }
    }
    // 中序遍历查找
    public  HeroNode infixOrderSearch(int no){
        if(root != null){
            return  root.infixOrderSearch(no);
        }else {
            return  null;
        }
    }
    // 后序查找
    public  HeroNode postOrderSearch(int no){
        if(root != null){
            return  root.postOrderSearch(no);
        }else {
            return  null;
        }
    }

}



// 创建heroNode 结点
class  HeroNode{

    private  int no;
    private  String name;
    private  HeroNode left;  // 默认 null
    private  HeroNode right;  // 默认 null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    // 删除结点
    public  void  delNode(int no){
        //  如果左子节点是删除的结点
        if(this.left !=null && this.left.no ==no){
            this.left =null;
            return;
        }
        // 如果右子节点是要删除的结点
        if(this.right !=null && this.right.no ==no){
            this.right = null;
            return;
        }
        // 左右结点都不是要删除的结点
        // 左子树递归删除
        if(this.left != null){
            this.left.delNode(no);
        }
        // 右子树递归删除
        if(this.right !=null){
            this.right.delNode(no);
        }
    }




    // 编写前序遍历
    public  void preOrder(){
        System.out.println(this); // 输出父节点
        // 递归左子树遍历
        if (this.left !=null){
            this.left.preOrder();
        }
        // 递归右子树
        if(this.right !=null){
            this.right.preOrder();
        }
    }

    // 中序遍历
    public  void  infixOrder(){
        // 递归遍历左子树
        if(this.left!=null){
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        if(this.right !=null){
            this.right.infixOrder();
        }
    }
    // 后序遍历
    public  void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right !=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }
    // 前序遍历查找
    public  HeroNode preOrderSearch(int no){
        // 1 先判断当前的这个结点 是否等于要查找的
        if(this.no == no){
            return  this;
        }
        // 用一个临时的变量接收
       HeroNode resNode = null;
        //3 如果不相等 则判断当前结点的左子节点是否为空 如果不为空 则递归前序查找
        //4 如果左递归查找 找到结点 则返回 否则继续判断 当前结点的右子节点是否为空
        //如果不空 则继续向右递归前序查找
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode!= null){  // 这个说明左子树找到
            return  resNode;
        }
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return  resNode;
    }

    // 中序遍历查找
    public  HeroNode infixOrderSearch(int no){
        HeroNode resNode =null;
        if(this.left!=null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode !=null){
            return  resNode;
        }
        if(this.no == no){
            return  this;
        }
        if(this.right!=null){
            resNode = this.right.infixOrderSearch(no);
        }
        return  resNode;
    }
    // 后序遍历查找
    public  HeroNode postOrderSearch(int no){

        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){   // 左子树递归找到
            return  resNode;
        }
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){  // 右子树递归找到
            return  resNode;
        }
        if(this.no == no){
            return  this;
        }
        return  resNode;
    }

}