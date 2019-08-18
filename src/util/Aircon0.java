package util;

/**
 * @description: 状态机
 * 空调Aircon简单的模型
 * 遥控器有两个按钮：power电源键和cool制冷键
 * 空调的运行呈现3个状态：停止/Off、仅送风/FanOnly、制冷/Cool
 * 初始状态为 Off
 * @author: Daniel
 * @create: 2019-04-16-17-32
 **/
public class Aircon0 {
    // Off、FanOnly、Cool
    private int state = 0; // 初始状态为 Off

    // 获取状态
    public int getState() {
        return state;
    }

    // 两个动作
    public void power() { // 按power键
        if (state == 0) { // Off
            state = 1; // FanOnly
            System.out.println("start FanOnly");
        } else if (state == 1) { // FanOnly
            state = 0;
            System.out.println("stop FanOnly");
        } else { // Cool
            state = 0;
            System.out.println("stop Cool");
        }
    }

    public void cool() { // 按制冷键
        if (state == 0) {
            System.out.println("nothing");
        } else if (state == 1) {
            state = 2;
            System.out.println("start Cool");
        } else {
            state = 1;
            System.out.println("stop Cool");
        }
    }
}
