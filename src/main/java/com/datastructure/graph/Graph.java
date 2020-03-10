package com.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

// 图的代码
public class Graph {

    private ArrayList<String> vertexList;  // 存储顶点的集合
    private int [][]edges;  // 存储对应的邻接矩阵
    private  int numOfEdges; // 表示边的数量
    boolean [] isvisited;

    public static void main(String[] args) {
        int n=5;

        Graph graph = new Graph(n);
        String [] verTexs={"A","B","C","D","E"};
        // 添加点
        for (String vertesx:verTexs){
            graph.addVertex(vertesx);
        }
        // 添加边
        // A-B A-C B-C B-D B-E
        graph.addEdges(0,1,1);
        graph.addEdges(0,2,1);
        graph.addEdges(1,2,1);
        graph.addEdges(1,3,1);
        graph.addEdges(1,4,1);

        graph.show();
        // 测试一个
         System.out.println("深度遍历");
         graph.dfs(); // AＢＣDＥ
        System.out.println("广度优先遍历");
        graph.bfs();

    }

    public Graph(int n) {
       edges= new int[n][n];
       numOfEdges=0;
       vertexList=new ArrayList<>(n);

    }
    // 获取第一个邻接节点的下标w
    /**
     *
     * @param index
     * @return
     */
    public  int getFirstNegihbor(int index){

        for (int i = 0; i <vertexList.size(); i++) {
            if(edges[index][i]>0){
                return  i;
            }
        }
        return -1;
    }
    // 根据前一个邻接节点获取下一个邻接节点
    public  int getNextNegibhor(int v1,int v2){

        for (int i = v2+1; i <vertexList.size() ; i++) {
            if(edges[v1][i]>0){
                return  i;
            }
        }
        return -1;
    }
    // dfs  深度优先遍历
    // i 第一次就是0
    public  void dfs(boolean []isvisited,int i){
        // 访问这个结点
        System.out.print(getValueByIndex(i)+"->");
        // 设置访问的标识
        isvisited[i]=true;
        // 查找结点i的第一个邻接节点
        int w = getFirstNegihbor(i);
        // 第一个邻接节点找到
        while (w!=-1){ // 说明有
            //
            if(!isvisited[w]){
                dfs(isvisited,w);
            }
            // 如果w结点已经被访问过
            w=getNextNegibhor(i,w);  // 这个w 在这个while 循环里面
        }
    }

    // 对dfs 进行一个重载 遍历我们的结点 进行 dfs
    public void dfs(){
        isvisited=new boolean[5];  // 为了广度和深度都可以进行遍历
        // 遍历所有的结点 进行dfs[回溯]
        for (int i = 0; i < getNum(); i++) {
            if(!isvisited[i]){
                dfs(isvisited,i);
            }
        }
    }
    // 一个结点广度优先遍历
    public  void  bfs(boolean[] isvisited,int i){
        int u; // 队列头的下标
        int w; // 邻接节点w
        // 访问这个结点
        System.out.print(getValueByIndex(i)+"->");
        isvisited[i]=true;
        LinkedList queue = new LinkedList();
        // 入队  记录访问的顺序
        queue.addLast(i);
        while (!queue.isEmpty()){
            // 出队
            u=(Integer) queue.removeFirst();
            // 得到第一个邻接节点w
            w = getFirstNegihbor(u);
            while (w!=-1){
                if(!isvisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isvisited[w]=true;
                    queue.addLast(w);
                }
                // 找 u开头的w结点的下一个结点
                w=getNextNegibhor(u,w);
            }
        }
    }
    // 重载一个广度优先遍历的方法
    public  void  bfs(){
        isvisited=new boolean[5];
        for (int i = 0; i <vertexList.size() ; i++) {
            if(!isvisited[i]){
                bfs(isvisited,i);
            }

        }
    }

    // 获取边的数量
    public  int getNumOfEdges(){
        return numOfEdges;
    }
    // 返回结点i 对应的数据 0->"A" 1->"B"
    public  String getValueByIndex(int i){
        return  vertexList.get(i);
    }
    // 获取v1 v2 的权值
    public  int getWeight(int v1,int v2){
        return  edges[v1][v2];
    }
    // 获取结点的数量
    public  int getNum(){
        return  vertexList.size();
    }
    // 显示邻接矩阵
    public void show(){
        // 二维数组的for循环的增强
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }

    // 添加结点
    public  void  addVertex(String value){
        vertexList.add(value);
    }
    // 添加边
    public  void addEdges(int v1,int v2,int weight){
        // 无向图
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }


}
