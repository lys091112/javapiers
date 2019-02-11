package com.xianyue.basictype.jvm.oop;

/**
 * @author Xianyue
 * 对象的向上转型和向下转型问题
 * 1、一个基类的引用类型变量可以“指向”其子类的对象。
 * 2、一个基类的引用不可以访问其子类对象新增加的成员（属性和方法）。
 * 3、可以使用 引用变量 instanceof 类名 来判断该引用型变量所“指向”的对象是否属于该类或该类的子类。
 * 4、子类的对象可以当做基类的对象来使用称作向上转型（upcasting）,反之成为向下转型（downcasting）
 */
public class WebhookService {

    public void addWebhook(IAction action) {
        WebhookAction webhookAction = (WebhookAction) action;
        System.out.printf("actionId %d, name %s, url %s \n", webhookAction.getActionId(), webhookAction.getName(), webhookAction.getUrl());
    }
}
