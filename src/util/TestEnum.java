package util;

/**
 * @description: 测试枚举类
 * @author: Daniel
 * @create: 2019-04-01-12-16
 **/
public class TestEnum {
    public static void main(String[] args) {
        // 直接引用
        Day day = Day.MONDAY;
    }
}
// 定义枚举类型
enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}