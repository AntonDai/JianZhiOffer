package util;

/**
 * @description: 测试switch和default
 * @author: Daniel
 * @create: 2019-04-17-11-15
 **/
public class TestSwitch {
    public static void main(String[] args) {
        int i = 0;
        for(int j = 0; j < 3; j++) {
            switch(j) {
                default: break;
                case 1 :
                    i++;
                    break;
                case 2 :
                    i += 2;
                    break;
            }
        }
        System.out.println(i);
    }
}
