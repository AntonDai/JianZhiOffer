package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 状态机
 * @author: Daniel
 * @create: 2019-04-16-18-19
 **/
public class RemoteControl1 {
    public static void main(String[] args) {
        Aircon1 ac = new Aircon1();
        System.out.println("current state : " + ac.state.name());
        ac.cool();
        ac.power();
        ac.cool();
        ac.power();
        ac.cool();
        ac.power();
        ac.power();
        ac.power();
        System.out.println(DateFormat.getDateInstance().format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
    }
}
