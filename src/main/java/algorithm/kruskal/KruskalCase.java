package algorithm.kruskal;

import java.util.Arrays;

//  克鲁斯卡尔算法  最小生成数
// 1 边的权值进行排序
public class KruskalCase {

    private  int edgeNum; // 边的个数(有效边的个数)
    private  char [] vertexs; // 顶点的数组
    private  int [][] matrix; // 邻接矩阵
    // 如果边之间是不连通的时候 使用这个数来表示
    private  static  final  Integer  INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char [] vertex = {'A','B','C','D','E','F','G'};
        int matrix[][] = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0},
        };
        KruskalCase kruskalCase = new KruskalCase(vertex, matrix);
        System.out.println("测试一下====");
        kruskalCase.print();
        EData[] edges = kruskalCase.getEdges();
        System.out.println("未排序==="+Arrays.toString(edges));
        kruskalCase.sort(edges);
        System.out.println("未排序==="+Arrays.toString(edges));
        kruskalCase.kruskal();

    }


    public  KruskalCase(char[] vertex,int [][] matrixs){

        int vLen =vertex.length;
        // 初始化顶点
        vertexs = new char[vLen];
        // 使用复制拷贝的方式来进行初始化
        for (int i = 0; i <vLen ; i++) {
            this.vertexs[i]= vertex[i];
        }
        // 初始化邻接矩阵  使用复制拷贝的方式
        matrix=new int[vLen][vLen];
        for (int i = 0; i <vLen ; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j]=matrixs[i][j];
            }
        }
        // 记录有效边的个数
        for (int i = 0; i <vLen ; i++) {
            for (int j = i+1; j <vLen ; j++) {  // 这里的i+1 统计上三角的数据 因为是 无向的 还有排除0
                if(this.matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }
    }
    // 克鲁斯卡尔算法
    public void  kruskal(){

        int [] ends = new int[edgeNum]; // 用于保存"已有最小生成树" 中每个顶点在最小生成树的终点
        EData [] rest = new EData[edgeNum]; // 结果数组 保存最后的最小生成树
        int index=0; // 表示结果数组的索引
        // 获取图中所有边的集合 一共12条边
        EData[] edges = getEdges();
        // 按照边的权值进行从大到小排序
        sort(edges);
        // 遍历edges 数组 将边的添加到最小生成树中 判断准备加入的边是否形成回路 如果没有 加加入rest 否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            // 获取到第i条边的第一个顶点
            int p1= getPosition(edges[i].start); // 4
            // 获取到第i条边对应的第二个顶点
            int p2 =getPosition(edges[i].end);//  5
            // 获取p1这个顶点在最小生成树的终点
            int m = getEnd(ends,p1);  // m=4
            // 获取p2这个顶点在最小生成树的终点
            int n =  getEnd(ends,p2); // n=5
            // 是否构成回路
            if(m !=n){ // 没有构成回路
                ends[m]=n; // 设置m 在"最小生成树"的终点  <E,F> [0,0,0,0,5,0,0,0,0,0,0,0]
                rest[index++]= edges[i]; // 将一条边加入到rest数组
            }

        }

        // 统计并打印最小生成树 输出 rest
        for (int i = 0; i < index; i++) {
            System.out.println(rest[i]);
        }
       //  System.out.println("最小生成树==="+Arrays.toString(rest));



    }


    // 打印
    public  void  print(){
        System.out.println("邻接矩阵：");
       /* for (int[] link :matrix){
            System.out.println(Arrays.toString(link));
        }*/
        for (int i = 0; i <vertexs.length ; i++) {
            for (int j = 0; j <vertexs.length ; j++) {
                // 为了方便好看
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }
    // 对边的进行排序 冒泡排序

    /**
     *  对边权值进行排序  冒泡算法
     * @param edges
     *
     */
     public  void  sort(EData[] edges){
        // 5个数 确定 4个数即可  （轮数）
         for (int i = 0; i < edges.length-1; i++) {
             for (int j = 0; j <edges.length-i-1 ; j++) {
                 if(edges[j].weight>edges[j+1].weight){
                     EData temp =edges[j];
                     edges[j]=edges[j+1];
                     edges[j+1]=temp;
                 }
             }
         }
     }

    /**
     *
     * @param ch
     * @return  返回ch 对应的下标  A =》 0
     */
     public int  getPosition(char ch){
         for (int i = 0; i <vertexs.length ; i++) {
             if(vertexs[i]==ch){
                 return i;
             }
         }
         return  -1;
     }
     // 返回有效的边对象 后面需要遍历

    /**
     *  能够连通边的数组
     * @return  [['A','B',12],[]......]
     */
    public EData [] getEdges(){
        int index=0;
        System.out.println("------"+Arrays.toString(vertexs));
        EData edges[]=new EData[edgeNum];
        for (int i = 0; i <vertexs.length ; i++) {
            for (int j = i+1; j <vertexs.length ; j++) {
                if(matrix[i][j]!=INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
         return  edges;
    }

    /**
     *  功能： 获取下标为i的顶点的终点 用于后面判断两个顶点的终点是否相同
     *  去理解一个这个算法
     * @param ends 数组就是记录了个顶点对应的终点是哪一个 ends 数组是遍历的过程中 逐步形成
     * @param i  表示传入顶点的下标
     * @return  返回的就是 下标为i 的这个顶点对应的下标  （上面的 EF 举例就是对这个方法的理解）
     */
    private  int getEnd(int [] ends,int i){
        while (ends[i] !=0){
            i=ends[i];
        }
        return  i;
    }

}

// 创建一个类EDate  它的对象实例就表示一条边
class  EData{
    char start;
    char end;
    int weight;
    public EData(char start,char end,int weight){
        this.start= start;
        this.end =end;
        this.weight=weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
