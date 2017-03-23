package com.xianyue.basictype.oop;

/**
 * @author Xianyue
 * 对象的转型问题
 */
public class Test {

    public static void main(String[] args) {
        WebhookService service = new WebhookService();
        WebhookAction action = new WebhookAction("xxx", "http://xianyue.com", 3);
        action.print();
        service.addWebhook(action);

        IAction action2 = new WebhookAction("222", "http://two.com", 2);
        action2.print();
        action2.getActionId();
//        action2.getName(); //没有这个方法
        service.addWebhook(action2);
    }

}
