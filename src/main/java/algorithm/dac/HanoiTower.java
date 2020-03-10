package algorithm.dac;
// 分治算法  汉诺塔

public class HanoiTower {
    public static void main(String[] args) {
          hanoiTower(2,'A','B','C');
    }

    public static void  hanoiTower(int num, char a, char b ,char c){
        // 如果只有一个盘
        if(num==1){
            System.out.println("第1个盘从 "+a+"->"+c);
        }else {
            // 如果大于1  将盘分为两个盘 1：最后一个盘 2：上面的所有盘
            // 先把上面的所有盘 A-B 中间借助C
            hanoiTower(num-1,a,c,b);
            // 把最下面的盘从A—C
            System.out.println("第"+num+"个盘从 "+a+"->"+c);
            // 再将B的所有盘从B->C
            hanoiTower(num-1,b,a,c);
        }



    }
}
