package algorithm.floyd;

import java.util.Arrays;
//  弗洛伊德短发  借助中间结点

public class FloydAlgorithm {

    public static void main(String[] args) {

        char  [] vertex={'A','B','C','D','E','F','G'};
        int [][] matrix = new int[vertex.length][vertex.length];
        final  int N  = 65535;
        matrix[0]=new int[]{0,5,7,N,N,N,2};
        matrix[1]=new int[]{5,0,N,9,N,N,3};
        matrix[2]=new int[]{7,N,0,N,8,N,N};
        matrix[3]=new int[]{N,9,N,0,N,4,N};
        matrix[4]=new int[]{N,N,8,N,0,5,4};
        matrix[5]=new int[]{N,N,N,4,5,0,6};
        matrix[6]=new int[]{2,3,N,N,4,6,0};
        Graph graph = new Graph(vertex,matrix,vertex.length);
        graph.Floyd();
        graph.show();
    }


}
class  Graph{

    private  char [] vertex;
    private  int [][] dis;
    private  int [][] pre;

    public  Graph(char [] vertexs,int [][]matrix,int length){
        this.vertex = vertexs;
        this.dis = matrix;
        pre = new int[length][length];
        // 初始化这个pre
        // pre 的初始化是 存放的前驱顶点的下标
        for (int i = 0; i <length ; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    public  void show(){
        char  [] vertex={'A','B','C','D','E','F','G'};
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]]+" ");
            }
            System.out.println();
            for (int k = 0; k <dis.length ; k++) {
                System.out.print("从"+vertex[i]+"到"+vertex[k]+"的距离为"+dis[i][k]+" ");
            }
            System.out.println();
        }
    }

    public  void  Floyd(){

        int len =0; // 定义一个变量保存距离
        // 中间结点遍历  k就是中间结点的下标  {'A','B','C','D','E','F','G'};
        // k 不动
        for (int k = 0; k <dis.length ; k++) {
            // 从i出发结点 {'A','B','C','D','E','F','G'};
            for (int i = 0; i < dis.length; i++) {
                // 倒搭j结点 {'A','B','C','D','E','F','G'};
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k]+dis[k][j]; // =》 求从i 点出发 结果结点k 到j点的距离
                    if(len<dis[i][j]){   // 如果len 小于 dis[i][j]
                        dis[i][j] =  len;  // 更新距离
                        pre[i][j] = pre[k][j];  // 更新前驱结点   这个前驱结点 pre[k][j]  不是很理解
                    }
                }
            }
        }
    }

}
