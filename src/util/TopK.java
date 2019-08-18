package util;

import java.io.*;
import java.util.*;

/**
 * @description: Java解决topK问题
 * 搜索引擎会通过日志文件把用户每次检索使用的所有检索串都记录下来，每个查询串的长度为1-255字节。
 * 假设目前有一千万个记录（这些查询串的重复度比较高，虽然总数是1千万，但如果除去重复后，不超过3百万个。
 * 一个查询串的重复度越高，说明查询它的用户越多，也就是越热门），请你统计最热门的10个查询串，要求使用的内存不能超过1G。
 * @author: Daniel
 * @create: 2019-03-26-22-04
 **/
public class TopK {

    // 将每行封装成一个记录，便于处理
    static class Record {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    // hash表统计次数
    private static void insert(Record record, Map<String,Integer> map) {
        String url = record.getKey();
        if(map.containsKey(url))
            map.put(url,map.get(url) + 1);
        else
            map.put(url,1);
    }

    // 创建一个最小堆
    private static List<String> getTopKRecord(Map<String,Integer> map, int k) {
        ArrayList<String> list = new ArrayList<>();
        PriorityQueue<String> minHeap = new PriorityQueue<>(k, (o1, o2) -> map.get(o1) - map.get(o2));
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, Integer> e = it.next();
            // 先构建堆
            if(minHeap.size() < 3)
                minHeap.offer(e.getKey());
            else if(map.get(minHeap.peek()) < map.get(e.getKey())){ // 比堆顶元素大，就删除堆顶元素，将新元素压入堆
                minHeap.poll(); // 删除堆顶
                minHeap.offer(e.getKey());
            }
        }
        list.addAll(minHeap);
        return list;
    }

    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        File file = new File("D:/file/log.txt");
        BufferedReader reader = null;
        int k = 3; // 测试用例记录较少，这里指定为3
        Record record = new Record();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tmp = null;
            while((tmp = reader.readLine()) != null) {
                record.setKey(tmp);
                insert(record,map);
            }
            Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry<String, Integer> e = it.next();
                System.out.println(e.getKey() + " = " + e.getValue());
            }
            List<String> result = getTopKRecord(map, k);
            for(String e : result) {
                System.out.println(e);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
