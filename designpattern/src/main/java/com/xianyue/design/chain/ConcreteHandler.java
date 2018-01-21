package com.xianyue.design.chain;

public class ConcreteHandler extends Handler {

    @Override
    public void handle(HandleParam param) {
        if (isFit(param)) {
            System.out.println("concrete handle!");

        } else {
            if (null != next) {
                next.handle(param);
            } else {
                System.out.println("can't find handle");
            }
        }
    }

    @Override
    public boolean isFit(HandleParam param) {
        return param.getAge() > 0 && param.getAge() <= 30;
    }
}
