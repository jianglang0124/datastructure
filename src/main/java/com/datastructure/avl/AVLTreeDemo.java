package com.datastructure.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int []arr={4,3,6,5,7,8};
        int []arr={10,12,8,9,7,6};
        // 创建一个avL对象
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i <arr.length ; i++) {
            avlTree.add(new Node(arr[i]));
        }
        // 中序遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("没有平衡处理的树的高度");
        System.out.println("树的高度=="+avlTree.getRoot().height()); //4
        System.out.println("树的左子树的高度="+avlTree.getRoot().leftHeight());//1
        System.out.println("树的右子树的高度=="+avlTree.getRoot().rightHeight());//3
        System.out.println(avlTree.getRoot().left); // 7

    }
}
// 创建AVL
class  AVLTree{

    private  Node root;

    public Node getRoot() {
        return root;
    }

    // 删除结点
    public Node search(int value){
        if(root ==null){
            return  null;
        }else {
            return root.search(value);
        }
    }
    // 查找父节点
    public  Node searchParent(int value){
        if(root ==null){
            return  null;
        }else {
            return  root.searchParent(value);
        }
    }
    // 编写方法 这个方法在删除结点有两个子树的情况下调用
    // 1 找到以 node为根节点的二叉排序树的最小值
    // 2 删除并且返回这个最小值
    /**
     *
     * @param node
     * @return  返回以 node为根节点的二叉排序树的最小值
     */
    public  int  delRightTreeMin(Node node){
        Node target = node;
        while (target.left!=null){
            target = target.left;
        }
        // target 指向的就是这个二叉排序树的最小值
        del(target.value);
        return  target.value;
    }

    // 删除结点
    public void  del(int value){
        if(root==null){
            return;
        }
        // 查找结点
        Node  targetNode = search(value);
        // 说明没找到
        if(null == targetNode){
            return;
        }
        // 树只有一个根节点的时候
        if(root.left==null && root.left==null){
            root= null;
            return;
        }
        Node parent = searchParent(value);
        // 删除的结点是叶子结点的时候
        if(targetNode.left==null && targetNode.right==null){
            if(parent.left!=null && parent.left.value ==value){ // 删除的叶子结点是左子节点
                parent.left=null;
            }else  if(parent.right !=null && parent.right.value==value){ // 删除的叶子结点是右子节点
                parent.right =null;
            }
        }else  if (targetNode.left !=null && targetNode.right!=null){ // 被删除的结点有两颗子树
            // 从被删除的结点的右结点找到最小的值(最小的结点已经删除)  将最小值赋值给 target.value
            int minVal = delRightTreeMin(targetNode.right);
            targetNode.value = minVal;

        }else {  //  这个是被删除的结点只有一颗子树
            if(targetNode.left!=null){  // 被删除的结点有左子点
                if(parent!=null) { // ！！！删除到最后会出现parent结点不存在的情况 所以要加上这个判断
                    if (parent.left.value == value) { // 被删除的结点是parent的左结点
                        parent.left = targetNode.left;
                    } else { // 被删除的结点是parent的右结点
                        parent.right = targetNode.left;
                    }
                }else {
                    // 当只剩下一个根节点和他的左结点的时候
                    root =targetNode.left;
                }
            }else {  // 被删除的结点有 右子节点的
                if(parent!=null){
                    if(parent.left.value==value){ // 被删除的结点是parent的左结点
                        parent.left=targetNode.right;
                    }else { // 被删除的结点是parent 的右结点
                        parent.right=targetNode.right;
                    }
                }else {
                    // 当只剩下一个根节点和他的右结点的时候
                    root = targetNode.right;
                }

            }
        }
    }

    // 添加结点的方法
    public  void add(Node node){
        if(root ==null){ // 如果根节点为空 则直接让root指向node
            root =node;
        }else {
            root.add(node);
        }
    }
    // 中序遍历
    public  void  infixOrder(){
        if(root ==null){
            System.out.println("树为空 不能遍历");
            return;
        }else {
            root.infixOrder();
        }
    }




}
// 创建结点
class  Node{

    int value;
    Node right;
    Node left;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    // 返回左子树的高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return  left.height();
    }

    // 返回右子树的高度
    public int rightHeight(){
        if(right==null){
            return 0;
        }
        return  right.height();
    }
    // 返回以该节点为根节点的树的高度   ！！！不太理解
    public int  height(){
        return  Math.max(left==null ? 0:left.height(),right==null?0:right.height())+1;
    }
    // 左旋转的方法
    private void  leftRotate(){

        // 创建一个新的结点 值等于当前结点
        Node newNode= new Node(value);
        // 设置新的结点的左子节点是当前结点的左子节点
        newNode.left=left;
        // 设置新的结点的右子节点是当前结点的右子节点的左结点
        newNode.right=right.left;
        // 设置当前结点的值等于右子节点的值
        value=right.value;
        // 设置当前结点的右子树为当前结点的右子节点的右子节点
        right=right.right;
        // 设置当前结点的左子树是新的结点
        left=newNode;
    }
    // 右旋转的方法   // 左子树的高度大于右子树
    public void rightRotate(){

        Node newNode = new Node(value);
        newNode.right=right;
        newNode.left=left.right;
        value = left.value;
        left=left.left;
        right=newNode;
    }
    // 查找要删除的结点
    public   Node search(int value){

        if(this.value==value){
            return  this;
        }else {
            if(this.value>value){
                if(this.left==null){
                    return  null;
                }else {
                    return this.left.search(value);
                }
            }else {
                if(this.right==null){
                    return  null;
                }else {
                    return this.right.search(value);
                }
            }
        }
    }

    /**
     *
     * @param value  要找到的结点的值
     * @return  返回的是要删除结点的父节点 如果没有就返回空
     */
    // 查找要删除结点的父节点
    public  Node searchParent(int value){
        // 如果当前结点就是要删除的父节点 就返回
        if((this.left!=null && this.left.value==value) || (this.right!=null && this.right.value==value)){
            return  this;
        }else {
            // 如果查找的值小于当前的结点 并且当前结点的左子节点不为空
            if(this.value>value && this.left !=null){
                return  this.left.searchParent(value); // 向左子树递归查找
            }else if(this.value<=value && this.right !=null){
                return  this.right.searchParent(value); // 向右子树查找
            }else {
                return  null; // 没有父节点
            }
        }

       /* if(this.value ==value){
            return  this;
        }
        if(this.value >value){
            if(this.left.value==value){
                return  this.left;
            }else {
                return  this.left.searchParent(value);
            }
        }*/



    }

    // 中序遍历
    public  void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    // 添加结点
    // 递归的形式添加结点 注意需要满足二叉排序树的要求
    public  void add(Node node){
        if(node ==null){
            return;
        }
        // 判断传入的结点的值 和当前子树的根节点的值的关系
        if(this.value > node.value){
            // 当前左子结点为空
            if(this.left==null){
                this.left =node;
            }else {
                // 递归向左子树添加
                this.left.add(node);
            }
        }else {
            if(this.right==null){
                this.right=node;
            }else {
                this.right.add(node);
            }
        }
        // 添加完一个结点以后 如果(右子树的高度-左子树额的高度>1) ,左旋转
        if(rightHeight()-leftHeight()>1){ // 左旋转
            // 如果当前的结点的右结点的左子树的高度大于当前结点右结点的右子树的高度
            if(right!=null && right.leftHeight()>right.rightHeight()){
                // 对当前结点先进行右旋转
                right.rightRotate();
                //  然后对当前结点在进行左旋转
                leftRotate();
            }else {
                // 直接进行左旋转
                leftRotate();
            }
            return; // !!!  必须要 进入
        }

        // 添加完一个结点以后 如果(左子树的高度-右子树额的高度>1) ,右旋转
        if(leftHeight()-rightHeight()>1){
            // 当他的左子树的右子树的高度大于他的左子树的左子树的高度
            if(left!=null && left.rightHeight()>left.leftHeight()){
                // 先对当前结点的左子树（左结点）进行左旋转
                left.leftRotate();
                // 在对当前的结点进行右旋转
                rightRotate();
            }else {
                rightRotate();
            }
        }
    }



}