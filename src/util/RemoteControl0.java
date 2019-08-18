package util;

/**
 * @description: 状态机
 * @author: Daniel
 * @create: 2019-04-16-17-45
 **/
public class RemoteControl0 {
    public static void main(String[] args) {
        Aircon0 ac = new Aircon0();
        System.out.println("Current state : " + ac.getState());
        ac.cool();
        ac.power();
        ac.cool();
        ac.cool();
        ac.power();
        ac.power();
        ac.power();
    }
}
