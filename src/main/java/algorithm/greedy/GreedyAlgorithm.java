package algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
// 集合覆盖问题  贪心算法
public class GreedyAlgorithm {

    public static void main(String[] args) {

        HashMap<String, HashSet<String>> broadCast= new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadCast.put("k1",hashSet1);
        broadCast.put("k2",hashSet2);
        broadCast.put("k3",hashSet3);
        broadCast.put("k4",hashSet4);
        broadCast.put("k5",hashSet5);

        //allAreas  存放所有的区域
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("杭州");
        allAreas.add("成都");
        allAreas.add("大连");
        allAreas.add("广州");
        allAreas.add("深圳");

        // 存放结果的集合
        ArrayList<String> selects = new ArrayList<>();
        // 存放的临时变量 表示当前区域和未覆盖区域的交际
        HashSet<String> tempSet = new HashSet<>();
        String maxKey=null;
        while (allAreas.size()>0){
            // 下一次进来 将 maxKey 置空
            maxKey=null;
            for (String key :broadCast.keySet()){
                // 下一次for 循环进来的时候 将其置空
                tempSet.clear();
                HashSet<String> areas = broadCast.get(key);
                tempSet.addAll(areas);
                // 两个集合去交集赋值给tempset
                tempSet.retainAll(allAreas);
                //  tempSet.size()> broadCast.get(maxKey).size() 这边体现的贪心算法的策略
                if(tempSet.size()>0 &&
                        (maxKey==null || tempSet.size()> broadCast.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if(maxKey !=null){
                selects.add(maxKey);
                // 将已经覆盖的地区排除
                allAreas.removeAll(broadCast.get(maxKey));
            }


        }
        System.out.println("selects===="+selects);


    }

}
