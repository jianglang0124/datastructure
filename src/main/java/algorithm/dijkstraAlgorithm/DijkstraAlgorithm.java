package algorithm.dijkstraAlgorithm;

import java.util.Arrays;

public class DijkstraAlgorithm {

    public static void main(String[] args) {

        char  [] vertex={'A','B','C','D','E','F','G'};
        int [][] matrix = new int[vertex.length][vertex.length];
        final  int N  = 65535;
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertex, matrix);
        graph.show();
        System.out.println();
        graph.dsj(6);
        graph.showDsj();


    }
}
class  Graph{

    private  char []  vertex;
    private  int [][] matrix;
    private  VisitedVertex vv;
    public  Graph(char [] vertex,int [][]matrix){
        this.vertex =vertex;
        this.matrix = matrix;
    }

    public  void showDsj(){
        vv.show();
    }


    // 显示图
    public  void  show(){
        for(int [] link : matrix){
            System.out.println(Arrays.toString(link));
        }
    }
    // 迪杰斯特拉算法  index 为出发顶点
    public  void  dsj(int index){
        vv= new VisitedVertex(vertex.length,index);
        update(index);  // 更新index顶点到周围顶点的距离和前驱结点
       // 每一个结点走一遍
        for (int i = 1; i < vertex.length; i++) { // 之前已经有一个结点访问完成 从1 开始
            index = vv.update(); // 选择并返回新的访问结点
            update(index); // 更新index 顶点到周围顶点的距离和前驱
        }
    }

    // 更新index 下标的顶点到周围顶点的距离和周围结点的前驱结点
    private  void  update(int index){
        int len;
        // 根据遍历我们邻接矩阵的 matrix[index] 行
        for (int j = 0; j <matrix[index].length ; j++) {
             // 出发顶点到index的距离 + 从index顶点到j结点的距离
            len = vv.getDis(index)+matrix[index][j];
            // 如果j没有被访问过 并且len 小于 从出发点到j的距离 就需要更新
            if(!vv.in(j) && len<vv.getDis(j)){
                vv.updatePre(j,index); // 更新j结点的前驱结点为index
                vv.updateDis(j,len);  // 更新出发结点到j 的距离
            }
        }
    }





}
class VisitedVertex{
    // 记录各个结点是否被访问过 1 表示访问过 0 表示未访问过
    private  int [] already_arr;
    // 每一个下标对应的值为前一个结点 会动态更新
    private  int [] pre_visited;
    // 出发顶点到各个顶点的距离
    private  int [] dis;

    public  VisitedVertex(int length,int index){
        already_arr = new int[length];
        pre_visited = new int[length];
        dis = new int[length];
        already_arr[index]=1;  // 设置出发顶点被访问过
        // 初始化dis
        Arrays.fill(dis,65535);
        dis[index]= 0; // 设置出发点到自己的距离为零
    }

    /**
     *  判断下标为index 的结点是否被访问过
     * @param index
     * @return
     */
    public  boolean in (int  index){
        return  already_arr[index]==1;
    }

    /**
     *  更新出发点到index结点的距离
     * @param index
     * @param len
     */
    public  void updateDis(int index ,int len){
        dis[index]=len;
    }

    /**
     *  更新pre这个顶点的前驱结点为 index 结点
     * @param pre
     * @param index
     */
    public  void  updatePre(int pre,int index){
        pre_visited[pre]=index;
    }

    /**
     *   返回 出发顶点到index 的结点的距离
     * @param index
     * @return
     */
    public  int getDis(int index){
        return  dis[index];
    }

    //继续选择并返回新的访问的结点   比如这里的G点完后 就是A作为新的访问的结点进行访问
    // 选出到出发点距离最小的点
    public  int  update(){
        int min= 65535,index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if(already_arr[i]==0 && min>dis[i]){
                min =dis[i];
                index=i;
            }
        }
        // 更新index 结点被访问过
        already_arr[index]=1;
        return  index;
    }

    public  void  show(){
        System.out.println("=========");
        for(int j:already_arr){
            System.out.print(j+" ");
        }
        System.out.println();
        for(int j:pre_visited){
            System.out.print(j+" ");
        }
        System.out.println();
      /*  for(int j:dis){
            System.out.print(j+" ");
        }*/
        // 为了好看
        char [] vertex= {'A','B','C','D','E','F','G'};
        int count =0;
        for ( int j :dis){
            if(j!=65535){
                System.out.print(vertex[count]+"("+j+")");
            }else {
                System.out.print("N");
            }
            count++;
        }
    }



}




