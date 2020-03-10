package com.datastructure.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        // 测试一把
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        // 二叉树 后面会递归的创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        // 测试一下
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        // 测试 10 号结点
        HeroNode leftNode = node5.getLeft();
        System.out.println("10号结点的前驱结点："+leftNode);
        HeroNode right = node5.getRight();
        System.out.println("10的后继结点："+right);
        // 中序线索化的遍历
        threadedBinaryTree.threadedList();
    }
}

//
// 定义一个 BinaryTreeDemo
class  ThreadedBinaryTree{

    // 根节点的设置
    private  HeroNode root;
    // 为了实现线索化 需要创建要给指向当前结点的前驱结点的指针
    // 在递归线索化时 pre 总是保留前一个结点
    private  HeroNode pre = null;


    public void setRoot(HeroNode root) {
        this.root = root;
    }
    // 重载一下
    public  void threadedNodes(){
        this.threadedNodes(root);
    }
    // 遍历线索化二叉树的一个方法
    public  void threadedList(){
        // 定义一个变量 存储当前遍历的结点 从 root 开始
        HeroNode node = root;
        while (node != null){
            // 循环找到LeftType==1的结点 第一个找到的是8这个结点
            // 后面随着遍历的变化 因为当 leftType ==1时 说明该节点是按照线索化处理的有效结点
            while (node.getLeftType() ==0){
                node =node.getLeft();
            }
            // 打印当前的结点
            System.out.println(node);
            // 如果当前结点的右指针指向的是后继结点 就一直输出
            if(node.getRightType() ==1){
                // 获取当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的结点
            node = node.getRight();
        }
    }

    // 编写中序线索化二叉树
    public  void  threadedNodes(HeroNode node){
        if(node ==null){
            return;
        }
        // 一 递归向左进行线索化
        threadedNodes(node.getLeft());

        // 二 当前结点的线索化
        // 处理当前结点的前驱结点
        if(node.getLeft()==null){
            // 让当前节点的左指针指向前驱结点
            node.setLeft(pre);
            // 修改当前结点的左指针的类型  指向前驱结点
            node.setLeftType(1);
        }
        // 处理后继结点(下次进行的时候)  记住是 pre  不是 node  好好想一下
        if(pre!=null && pre.getRight()==null) {
            // 让前驱结点的指针指向当前结点
            pre.setRight(node);
            pre.setRightType(1);
        }
        // 将pre 移动 !!!!!!!!!!!!! 没处理一个结点后 让当前的结点是下一个结点的前驱结点
        pre =node;

        // 递归向右线索化
         threadedNodes(node.getRight());
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



// 创建Node 结点
// 创建heroNode 结点
class  HeroNode{

    private  int no;
    private  String name;
    private HeroNode left;  // 默认 null
    private HeroNode right;  // 默认 null
    // 新增
    // 说明  如果leftType值为1 说明指向的是前驱结点 为0 说明指向左子树
    private int leftType;
    // 如果rightType值为1 说明指向的是后继结点 为0 说明指向右子树
    private int rightType;

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

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
    public HeroNode preOrderSearch(int no){
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
    public HeroNode infixOrderSearch(int no){
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
    public HeroNode postOrderSearch(int no){

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