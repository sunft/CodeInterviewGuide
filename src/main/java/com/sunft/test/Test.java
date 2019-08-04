package com.sunft.test;

/**
 * 这是第一个编程的解法，第二题可以在action中传个接口的具体实现，action方法中调用即可
 */

/**
 * 状态接口
 */
interface State{

    void action();

}

/**
 * 代表红色的状态
 */
class RedState implements State{

    @Override
    public void action() {
        System.out.println("做红灯该做的事情");
    }
}

/**
 * 代表绿色的状态
 */
class GreenState implements State {

    @Override
    public void action() {
        System.out.println("做绿灯该做的事情");
    }
}

/**
 * 代表黄色的状态
 */
class YellowState implements State {

    @Override
    public void action() {
        System.out.println("做黄灯该做的事情");
    }
}

class A{
    private State state;

    public void setState(State state) {
        this.state = state;
        this.state.action();
    }

}

public class Test {

    public static void main(String[] args) {
        A a = new A();
        a.setState(new RedState());//设置状态位红色
        a.setState(new GreenState());//将状态修改为绿色
    }

}
