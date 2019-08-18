import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description: 如何正确地从ArrayList中删除一个元素
 * @author: Daniel
 * @create: 2019-03-04-20-30
 **/
public class RemoveFromArrayList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(5);
        list.add(3);
        // 测试foreach循环时添加的
        list.add(7);
        list.add(6);

        System.out.println(list); // [1, 2, 3, 3, 5, 3, 6]

        /** 这是错误的删除方式**/
        // 为甚么？在for循环中，当删除前一个3后，索引会指向下一个3，还做了i++操作，其实是底层将后面的部分移动到前面，所以会跳过接下来的那个3
//        for(int i=0; i<list.size(); i++) {
//            if(list.get(i).equals(3))
//                list.remove(i); // 存在遗漏的3未删除
//        }
//        System.out.println(list); // [1, 2, 3, 5, 6]

        /**会抛ConcurrentModificationException异常的代码**/
        for(Integer i : list) { // foreach循环其实是相当于把元素的遍历托管给了Iterator，如果要对list进行增删操作（结构性变化，不是修改元素值），都必须经过Iterator，直接对list进行删除，Iterator会抛异常
            if(i.equals(3)) {
                System.out.println("不能这么干，我要抛异常了"); // 不是绝对的，可以安全删除倒数第二个7，删除其他的都会抛异常，这又是为什么呢？
                list.remove(i); // 因为删除倒数第二个元素的时候，cursor指向最后一个元素，而此时删掉了倒数第二个元素后，cursor和size()正好相等了，所以hasNext()返回false，遍历结束，这样就成功的删除了倒数第二个元素了。
//                list.set(2,10); // 替换，不是结构性变化，不会抛异常
            }
        }

        System.out.println(list);

        /**这是正确的删除方式**/
//        Iterator<Integer> it = list.iterator(); // 迭代器每运行一次next()，指针向后移动一次，一个个遍历
//        while(it.hasNext()) {
//            if(it.next().equals(3))
//                it.remove(); // 会删除每一个3
//        }
//        System.out.println(list); // [1, 2, 5, 6]
    }
}
