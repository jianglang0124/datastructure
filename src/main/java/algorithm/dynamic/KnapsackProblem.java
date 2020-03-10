package algorithm.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {

        int w[]={1,4,3}; //  物品的重量
        int []val={1500,3000,2000};   // 物品的价值 这里的 val[i] 就是前面讲的 v[i]

        int m=4; //背包的容量
        int n=val.length; // 物品的个数

        // v[i][j] 表示前i个物品中能够放入装入容量为j的背包中的最大值
        int[][]v=new int[n+1][m+1];
        // 存放的路径
        int [][]path =new int[n+1][m+1];
        // 处理第一行和第一列
        for (int i=0;i<v.length;i++){
            for (int j=0;j<v[i].length;j++){
                 v[0][j]=0;  //处理行
            }
        }
        for (int i = 0; i <v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                v[i][0]=0;
            }
        }
        // 根据前面的公式动态规划的公式
        for (int i=1;i<v.length;i++){  // 不处理第一行 i从1开始
            for (int j = 1; j <v[i].length ; j++) { // 不处理第一列 j从1开始
                // 如果放入的物品的重量大于当前物品的重量 采取上一次存放的策略
                if(w[i-1]>j){  // 因为我们程序是从 1 开始的 因此原来的公式w[i] -> w[i-1]
                    v[i][j]=v[i-1][j];
                }else {  // 准备放入的物品的重量 小于当前背包的重量 有两种策略
                    // 一种是放入该物品 一种是不放入该物品  价值的计算是两者最大值
                    // 不放入就采取之前上一次存放的策略 放入的话就是 放入的物品的价值+前(i-1)个物品
                    // 放图背包剩余容量的价值 （前i-1 个商品不一定全部放进去）
                   // v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    // 为了记录商品的存放到背包的情况 我们不能直接使用上面的公式 使用 if-else 来体现
                    if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                        // 其中这个i 表示第几个商品
                        path[i][j]=1;
                    }else {
                        v[i][j]=v[i-1][j];
                    }
                }
            }
        }

        // 输出这个二维数组
        for (int i = 0; i <v.length ; i++) {
            for (int j = 0; j <v[i].length ; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("=================");
        // 输出这个path 动点脑筋  是
        int i = path.length-1;
        int j=path[0].length-1;
        while (i>0 && j>0){
            if(path[i][j]==1){
                System.out.println("将第"+i+"个物品放入背包");
                j-=w[i-1];  // j 背包的容量要减少
            }
            i--;
        }



    }
}
