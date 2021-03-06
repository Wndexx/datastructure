package com.wndexx.greedyalgorithm;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * 贪心算法
 *
 * @author wndexx 2022-04-14 19:35
 */
public class GreedyAlgorithm {
    /**
     * 贪心算法
     */
    @Test
    public void test1() {

        // 1. 创建广播电台，放入到 Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        // 2. 将各个电台放入到 broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        broadcasts.put("K1", hashSet1);

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        broadcasts.put("K2", hashSet2);

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        broadcasts.put("K3", hashSet3);

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        broadcasts.put("K4", hashSet4);

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K5", hashSet5);

        // 3. allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        Set<Map.Entry<String, HashSet<String>>> entrySet = broadcasts.entrySet();
        for (Map.Entry<String, HashSet<String>> entry : entrySet) {
            HashSet<String> value = entry.getValue();
            allAreas.addAll(value);
        }

        // 4. 创建 ArrayList，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        // 5. 定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        // 6. 定义一个 maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的 key
        String maxKey = null;
        // 如果 maxKey 不为 null，则会加入到 selects
        while (allAreas.size() != 0) { // 如果 allAreas 不为 0，则表示还没有覆盖到所有的地区 （前提是提供的电台一定能够覆盖所有的地区）
            // 每进行一次 while ，需要
            maxKey = null;
            // 遍历 broadcasts，取出对应的 key
            for (String key : broadcasts.keySet()) {
                // 每进行一次 for
                tempSet.clear();
                // 当前这个 key 能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                // 求出 tempSet 和 allAreas 集合的交集，交集会赋给 tempSet
                tempSet.retainAll(allAreas);
                // 如果当前这个集合包含的未覆盖地区的数量，比 maxKey 指向的集合的地区还多
                // 就需要重置 maxKey
                // maxKeyAreas 存放的是 maxKey 指向的广播所覆盖的未覆盖区域
                HashSet<String> maxKeyAreas = null;
                if (maxKey != null) {
                    maxKeyAreas = broadcasts.get(maxKey);
                    maxKeyAreas.retainAll(allAreas);
                }
                // tempSet.size() > maxKeyAreas.size() 体现出贪心算法的特点，每次都选择最优的
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > maxKeyAreas.size())) {
                    maxKey = key;
                }
            }
            // maxKey != null ，就应该将 maxKey 加入到 selects
            if (maxKey != null) {
                selects.add(maxKey);
                // 将 maxKey 指向的广播电台覆盖的地区从 allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果 " + selects);
    }

    @Test
    public void test2() {
        HashSet<String> hashSet1 = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();

        hashSet1.add("1");
        hashSet1.add("2");
        hashSet1.add("100");

        hashSet2.add("1");
        hashSet2.add("2");
        hashSet2.add("200");

        hashSet1.retainAll(hashSet2);
        System.out.println("hashSet1 = " + hashSet1); // [1, 2]
    }
}
