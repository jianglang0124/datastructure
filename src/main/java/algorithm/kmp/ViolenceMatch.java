package algorithm.kmp;
// 字符创的暴力匹配
public class ViolenceMatch {

    public static void main(String[] args) {

        String str1="你好啊同学";
        String str2="同学!";
        int index = violenceMatch(str1, str2);
        System.out.println("index==="+index);

    }
    public  static  int violenceMatch(String str1,String str2){

        char [] s1= str1.toCharArray();
        char [] s2 = str2.toCharArray();
        int str1len = s1.length;
        int str2len = s2.length;
        int i=0;  // i索引指向s1
        int j=0;  // j索引指向s2
        while (i<str1len && j<str2len){ // 保证匹配的时候 不越界
            if(s1[i]==s2[j]){   // 匹配ok
                i++;
                j++;
            }else {  // 没有匹配
                i=i-(j-1);
                j=0;
            }
        }
        if(j==str2len){
            return  i-j;
        }else {
            return  -1;
        }

    }


}
