package horse;
// 骑士周游问题
/*
*  1  创建一个棋盘 chessBoard 这是一个二维数组
*  2  将当前位置设置已经访问 然后根据当前的位置 计算马儿还能走那些位置
*     并放入到一个集合中 最多有8个位置 没走一步  就使用step+1
*  3  遍历集合存放的所有的位置 看看哪一个可以走通 如果走走通 就继续 走不通就回溯
*  4  判断马儿是否完成任务 使用step 和应该应该走的步数比较 如果没有达到数量 则表示没有完成任务 将整个期盼置0
*  5
* */

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessBoard {

     private  static  int x;  // 棋盘的列数
     private  static  int y;  // 棋盘的行数
     // 创建一个数组 标记棋盘的每个位置是否被访问过
    private  static  boolean visited[];
    private  static  boolean finished;  // 如果为true 表示成功
    public static void main(String[] args) {
        System.out.println("开始运行====");
        x=8;
        y=8;
        int row =1;  // 马儿初始的位置的行 从1 开始
        int col =1;   //  马儿初始的位置的列 从1 开始
        int[][] chessBoard = new int[x][y];
        visited=new boolean[x*y];
        long start = System.currentTimeMillis();
        travelsalChessboard(chessBoard,row-1,col-1,1);
        long end = System.currentTimeMillis();
        System.out.println("共消耗："+(end -start)+"毫秒");
        // 输出
       for (int []link :chessBoard){
           for (int i :link){
               System.out.print(i+" ");
           }
           System.out.println();
       }

    }

    /**
     *
     * @param chessBoard  棋盘
     * @param row     马儿当前位置的行 从0开始
     * @param col    马儿当前的位置的列 从0 开始
     * @param step  是第几步  初始位置就是第一步
     */
    public  static  void  travelsalChessboard(int [][] chessBoard,int row,int col ,int step){
        chessBoard[row][col]=step;
        visited[row*x+col]= true;  // 第36个位置
        // 列代表 y  代表x
        ArrayList<Point> ps = next(new Point(col, row));
        // 对ps进行排序  排序的规则就是对 ps所有的point对象的下一步的位置进行非递减排序
        sort(ps);
        // 遍历 ps
        while (!ps.isEmpty()){
            Point p = ps.remove(0);  // 取出下一个可以走的位置
            // 判断该点是否被访问过
            if(!visited[p.y *x + p.x]){  // 如果这个点没有被访问过
                travelsalChessboard(chessBoard,p.y,p.x,step+1);
            }

        }
        // 判断马儿是否完成任务 使用 step 和应该走的步数比较
        // 如果没有达到数量 这没有完成任务
        // 说明 ：step < x* y 成立的情况有两种
        // 1。 棋盘到目前的位置还没有走完
        // 2。棋盘处于一个回溯的位置
        if(step < x* y && !finished){
            chessBoard[row][col] =0;
            visited[row*x+col]= false;
        }else {
            finished = true;
        }
    }

    /**
     * 然后根据当前的位置 计算马儿还能走那些位置
     * *     并放入到一个集合中 最多有8个位置 没走一步  就使用step+1
     * @param curPoint
     * @return
     */
    public  static ArrayList<Point> next(Point curPoint){

        // 创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();

        Point p1 = new Point();
        // 表示马儿可以走 5 这个位置
        if((p1.x =curPoint.x-2)>=0 && (p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        // 表示马儿可以走 6 这个位置
        if((p1.x =curPoint.x-1)>=0 && (p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        // 表示马儿可以走 7 这个位置
        if((p1.x =curPoint.x+1) < x  && (p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        // 表示马儿可以走 0 这个位置
        if((p1.x =curPoint.x+2) < x  && (p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        // 表示马儿可以走 1 这个位置
        if((p1.x =curPoint.x+2) < x  && (p1.y=curPoint.y+1)<y){
            ps.add(new Point(p1));
        }
        // 表示马儿可以走 2 这个位置
        if((p1.x =curPoint.x+1) < x  && (p1.y=curPoint.y+2)<y){
            ps.add(new Point(p1));
        }
        // 表示马儿可以走 3这个位置
        if((p1.x =curPoint.x-1) >=0  && (p1.y=curPoint.y+2)<y){
            ps.add(new Point(p1));
        }
        // 表示马儿可以走 4 这个位置
        if((p1.x =curPoint.x-2) >=0  && (p1.y=curPoint.y+1)<y){
            ps.add(new Point(p1));
        }

       return  ps;
    }
     // 找到下一步能够的步数找最少的  这样可以减少回溯的次数  ====== 贪心算法
    // 根据当前这一步的所有下一步的选择位置 进行非递减排序
    public  static  void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取o1 的下一步的所有位置的个数
                int len1 = next(o1).size();
                // 获取o2 的下一步的所有位置的个数
                int len2 = next(o2).size();
                if(len1<len2){
                    return  -1;
                }else if(len1==len2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });

    }


}
