package com.datastructure.recursion;
// 迷宫问题
public class MiGong {
    public static void main(String[] args) {
     // 创建一个迷宫  二维数组来模拟
        int map[][]= new int [8][7];
        //使用 1 表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i]=1;
            map[7][i] =1;
        }
        // 左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6] =1;
        }
        // 设置挡板
        map[3][1]=1;map[3][2]=1;
        // 输出的地图的情况
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        // 使用递归回溯
       // setWay(map,1,1);
        setWay2(map,1,1);
        //
        System.out.println("找到后=====");

        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    //  使用递归回溯给削球找路

    /**
     *  map 表示地图
     * i j 表示从地图的哪一个位置开始出发(1,1)
     *  如果小球能找到map[6][5] 位置 则说明通路找到
     * 约定： 当map[]i[j] 为 0 表示该点没有走过 当为1的时候 表示墙 2 表示通路可以走 3 表示已经走过 但不通
     *
     *策略： 在走迷宫的时候 需要制定一个策略  下->右->上->左 如果该点走不通 再回溯
     *
     *
     * @param map 表示地图
     * @param i  从哪个位置开始找
     * @param j
     * @return  找到痛苦 返回 true 否则 返回 false
     */
    public  static  boolean setWay(int [][]map,int i,int j){
        if(map[6][5]==2){
            return  true;
        }else {
            if(map[i][j]==0){ // 表示这条路没有走过
                map[i][j]=2; // 假设表示该点可以走通
                // 下->右->上->左
                if(setWay(map,i+1,j)) {  // 向下走
                    return true;
                }else if(setWay(map,i,j+1)){  // 右
                    return  true;
                }else if(setWay(map,i-1,j)){ // 上
                    return  true;
                }else if(setWay(map,i,j-1)){ // 左
                    return  true;
                }else {
                    // 经过试探后 发现全都走不通
                    map[i][j]=3;
                    return  false;
                }
            }else {  // map[i][j]!=0 的时候 可以为 1 2 3
                return  false;
            }
        }
    }
    // 改变小球走的策略
    public  static  boolean setWay2(int [][]map,int i,int j){
        if(map[6][5]==2){
            return  true;
        }else {
            if(map[i][j]==0){ // 表示这条路没有走过
                map[i][j]=2; // 假设表示该点可以走通
                // 下->右->上->左
                if(setWay2(map,i-1,j)) {  // 向上走
                    return true;
                }else if(setWay2(map,i,j+1)){  // 右
                    return  true;
                }else if(setWay2(map,i+1,j)){ // 下
                    return  true;
                }else if(setWay2(map,i,j-1)){ // 左
                    return  true;
                }else {
                    // 经过试探后 发现全都走不通
                    map[i][j]=3;
                    return  false;
                }
            }else {  // map[i][j]!=0 的时候 可以为 1 2 3
                return  false;
            }
        }
    }



}
