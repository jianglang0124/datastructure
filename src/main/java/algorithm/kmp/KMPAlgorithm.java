package algorithm.kmp;

// 算法的主要在于 运用
public class KMPAlgorithm {
    public static void main(String[] args) {

        String str1="BBC ABCDAB ABCDABCDABDE";
        String str2="ABCDABD";
       // String str2="BBC";
        int[] next = kmpNext(str2);
        int index = KMP(str1, str2, next);
        System.out.println("index===="+index);  //15

    }
    // kMP 算法
    public  static  int KMP(String str1,String str2,int [] next){
        for (int i = 0,j=0; i < str1.length(); i++) {
            // KMP算法的核心
            while (j>0 &&str1.charAt(i)!=str2.charAt(j)){
                j=next[j-1];
            }

            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            if(j==str2.length()){
                return  i-j+1;
            }
        }
        return  -1;
    }


    // 创建子串的部分匹配表
    public  static  int [] kmpNext(String str){
        int [] next= new int[str.length()];
        next[0]=0;  // 一位数的时候  前缀和后缀都是0
        for (int i = 1,j=0; i <str.length() ; i++) {  // i j 错开来
            // KMP 算法的核心
            while (j>0 &&str.charAt(i)!=str.charAt(j)){
                j=next[j-1];
            }

            if(str.charAt(i)==str.charAt(j)){
                j++;
            }
            next[i]=j;

        }
        return  next;
    }
}
