package com.xianyue.basictype.jvm.oop;

/**
 * @author Xianyue
 */
public class WebhookAction implements IAction {

    private String name;
    private String url;
    private int    actionId;

    public WebhookAction(String name, String url, int actionId) {
        this.name = name;
        this.url = url;
        this.actionId = actionId;
    }

    @Override
    public int getActionId() {
        return actionId;
    }

    @Override
    public void print() {
        System.out.printf("name is [%s], url is [%s], actionId is [%d] \n\n", name, url, actionId);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
