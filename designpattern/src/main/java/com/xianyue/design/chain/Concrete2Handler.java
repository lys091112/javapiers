package com.xianyue.design.chain;

public class Concrete2Handler extends Handler {

    @Override
    public void handle(HandleParam param) {
        if (isFit(param)) {
            System.out.println(" concrete2 handle");
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
        return param.getAge() > 30;
    }
}
