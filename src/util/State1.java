package util;

public enum State1 {
    OFF {
        @Override
        void exit(Aircon1 ac) {
            super.exit(ac);
            startFan();
        }

        @Override
        void power(Aircon1 ac) {
            this.exit(ac);
            ac.state = FANONLY;
            ac.state.entry(ac);
        }

        @Override
        void cool(Aircon1 ac) {
            System.out.println("nothing");
        }
    },
    FANONLY {
        @Override
        void power(Aircon1 ac) {
            super.exit(ac);
            stopFan();
            ac.state = OFF;
            ac.state.entry(ac);
        }

        @Override
        void cool(Aircon1 ac) {
            super.exit(ac);
            ac.state = COOL;
            ac.state.entry(ac);
        }
    },
    COOL {
        @Override
        void power(Aircon1 ac) {
            this.exit(ac);
            ac.state = OFF;
            ac.state.entry(ac);
        }

        @Override
        void cool(Aircon1 ac) {
            this.exit(ac);
            ac.state = FANONLY;
            ac.state.entry(ac);
        }

        @Override
        void entry(Aircon1 ac) {
            startCool();
            super.entry(ac);
        }

        @Override
        void exit(Aircon1 ac) {
            super.exit(ac);
            stopCool();
        }
    };

    // 状态模式提取的接口
    abstract void power(Aircon1 ac);
    abstract void cool(Aircon1 ac);
    // 状态机的各种动作
    void entry(Aircon1 ac) { // 进入下一个状态
        System.out.println("->" + ac.state.name());
    }
    void exit(Aircon1 ac) { // 从当前状态退出
        System.out.print(ac.state.name() + "->");
    }
    void startCool() {
        System.out.print("start Cool");
    }
    void stopCool() {
        System.out.print("stop Cool");
    }
    void startFan() {
        System.out.print("start FanOnly");
    }
    void stopFan() {
        System.out.print("stop FanOnly");
    }
}
