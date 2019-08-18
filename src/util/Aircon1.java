package util;

/**
 * @description: 状态机
 * @author: Daniel
 * @create: 2019-04-16-18-17
 **/
public class Aircon1 {
    State1 state = State1.OFF;
    // 两个action
    public void power() {
        state.power(this);
    }
    public void cool() {
        state.cool(this);
    }
}
