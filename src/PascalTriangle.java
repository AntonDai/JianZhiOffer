import java.util.ArrayList;

/**
 * @description: 帕斯卡三角形
 * @author: Daniel
 * @create: 2019-03-10-22-56
 **/
public class PascalTriangle {
    public static ArrayList<ArrayList<Integer>> generate(int row) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<row; i++) {
            list.add(0,1); // 在指定位置插入元素，如果当前位置有元素，就后移
            for(int j=1; j<list.size()-1; j++) // 从第三行开始
                list.set(j,list.get(j) + list.get(j+1)); // set 是替换
            result.add(new ArrayList<>(list));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
