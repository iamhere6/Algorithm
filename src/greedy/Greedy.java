package greedy;

import javax.swing.text.html.HTMLDocument;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *贪心算法
 * 算法介绍：
 *      （1）对问题进行求解，每一步都力求完美，导致结果是完美的
 *      （2）得到的结果不一定是最优（有时候是最优），但都是相对接近
 * 应用场景-集合覆盖：
 *      存在以下需要付费的广播台，以及广播信号可以覆盖的地区，如何选择最少的广播台，让所有的地区可以接收到信号
 *      广播台               覆盖地区
 *      k1                   "北京","上海","天津"
 *      k2                   "广州","北京","深圳"
 *      k3                   "成都","上海","杭州"
 *      k4                   "上海","天津"
 *      k5                   "杭州","大连"
 * 思路分析：
 *      （1）穷举法   效率低
 *      （2）贪心算法  效率高
 */
public class Greedy {
    public static void main(String[] args) {
        // 创建广播电台  Map<电台,覆盖地区>
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        // 创建覆盖地区  HashSet<String>
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        // 初始化广播电台
        broadcasts.put("k1", hashSet1);
        broadcasts.put("k2", hashSet2);
        broadcasts.put("k3", hashSet3);
        broadcasts.put("k4", hashSet4);
        broadcasts.put("k5", hashSet5);
        // 创建HashSet<String>  存放所有地区
        HashSet<String> allAreas = new HashSet<String>();
        for (HashSet<String> hashSet : broadcasts.values()) {
            for (String hS : hashSet) {
                allAreas.add(hS); // HashSet 不能添加重复的元素
            }
        }
        // [成都, 上海, 广州, 天津, 大连, 杭州, 北京, 深圳]

        // 创建arrayList  存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        // 定义一个临时集合 存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();


        while (allAreas.size() != 0){
            // 创建一个HashSet<String>   存放Key
            HashSet<String> areasKey = new HashSet<String>();
            for (String key : broadcasts.keySet()){
                areasKey.add(key);
                tempSet.addAll(areasKey);
                tempSet.retainAll(allAreas); // 求交集
            }
        }
        //System.out.println(allAreas);


    }
}
