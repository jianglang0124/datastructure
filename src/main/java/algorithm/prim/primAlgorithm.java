package algorithm.prim;

import java.util.Arrays;
// 普利姆算法  寻找最小生成树
public class primAlgorithm {
    public static void main(String[] args) {
        // 测试看看 图是否创建成功
        char [] data ={'A','B','C','D','E','F','G'};
        int vertx = data.length;
        // 邻接矩阵的关系使用二维数组表示  10000这个大数 表示两个点之间不连通
        int weight[][]= new int[][]{
                //A    B C   D    E     F    G
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        // 创建Graph对象
        MGraph graph = new MGraph(vertx);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertx,data,weight);
        minTree.show(graph);
        // prim 测试
        minTree.prim(graph,0);
    }
}

// 创建最小生成树 --》村庄的图
class MinTree{

    // 创建图的邻接矩阵;

    /**
     *
     * @param graph  图的对象
     * @param vertx  顶点的个数
     * @param data    图各个顶点的值
     * @param weight  图的邻接矩阵
     */
    public void  createGraph(MGraph graph ,int vertx,char []data,int[][] weight){
        int i,j;
        for (i = 0; i <vertx ; i++) {
            graph.data[i] = data[i];
            for ( j = 0; j < vertx; j++) {
                graph.weight[i][j] =weight[i][j];
            }
        }
    }

    // 显示图的方法
    public  void  show(MGraph graph){
        for (int []link:graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }
    //编写一个prim 算法

    /**
     *  先遍历结点 把第一个结点作为出发点 继续遍历邻接点 后面在遍历寻找邻接点(第一次for)
     *  这样你最后就找到了一个权值最小的边的出发点和邻接点
     * @param graph  图
     * @param v  表示从图的第几个定点开始生成
     */
    public  void prim(MGraph graph,int v){
        // 标记数组 这个结点是否被访问过
        int[] visited = new int[graph.vertx];
        // 标记 v 结点访问过
        visited[v]=1;
        // d定义一个变量 存放最小的权值
        int minWeight=10000;  // 初始化成一个大数  后面的遍历的过程中 会被替换
        // h1 h2 记录两个顶点的下标
         int h1=-1;
         int h2= -1;
        for (int k = 1; k <graph.vertx ; k++) {  // 有vertx个结点  所以生成的边的数量是 vertex -1

            // 这个一次for循环结束 找到了和当前结点(已访问过)的 相邻最小的那个权值的点
            // 这个是确定每一次生成的子图 和哪个结点距离最近
            for (int i = 0; i <graph.vertx ; i++) {  // i结点表示已经被访问过的结点
                for (int j = 0; j <graph.vertx ; j++) {  // j 结点是表示还没有被访问过的结点
                    if(visited[i]==1 && visited[j]==0&& minWeight>graph.weight[i][j]){
                        // 替换这个最小权值(寻找已经访问过的结点和未访问过的结点间的权值最小的表)
                        minWeight=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            System.out.println("边"+graph.data[h1]+"->"+graph.data[h2]+"权值："+minWeight);
            //把这个结点标记成一家访问过
            visited[h2]=1;
            // 将minWeight重置为10000
            minWeight =10000;
        }

    }


}



class MGraph{

    int vertx;  // 顶点的个数
    char [] data;  // 结点的数据
    int [][] weight; // 存放边  邻接矩阵

    public  MGraph(int vertx){
        this.vertx =vertx;
        data = new char[vertx];
        weight = new int[vertx][vertx];
    }

}
