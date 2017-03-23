package com.xianyue.basictype.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 */
public class JsonMain {

    public static void main(String[] args) {
        filterSomeKeys();
    }

    private static void filterSomeKeys() {
        String json = "{\"acceptanceStatus\":false,\"alertObject\":{\"agentId\":1,\"agentName\":\"Java:mock_system1" +
                "(mock1.oneapm.me)\",\"metricId\":195,\"metricName\":\"mock2.oneapm.me:8080/login_service\"," +
                "\"systemId\":1,\"systemName\":\"mock_app1\",\"tierId\":1,\"tierName\":\"mock_system1\"}," +
                "\"alertObjectStatus\":\"严重\",\"alertPolicy\":\"mock_app1_报警\",\"alertRule\":\"内部远程调用\"," +
                "\"appName\":\"mock_app1\",\"createTime\":1490234014511," +
                "\"eventId\":\"35a2611e-e8a7-4bc8-8d85-7b34267eb073\",\"eventType\":\"严重报警持续事件\"," +
                "\"infoSource\":\"ONEAPM\",\"nodeType\":\"remoteCall\",\"policyId\":1," +
                "\"realtimeIndex\":[\"每分钟平均响应时间:0.01s\"],\"ruleContent\":[\"严重 : 过去 5 分钟，每分钟平均响应时间 的 平均值 小于 5s\",\"警告" +
                " : 过去 5 分钟，每分钟平均响应时间 的 平均值 小于 3s\"],\"ruleId\":\"ONEAPM_ALERT_AI_9_1490232871585_remoteCall_3_1\"," +
                "\"systemId\":1,\"time\":1490234014511,\"url\":\"http://10.128.32.121:8099/api/v1/data/alert\"," +
                "\"webhookName\":\"俊成第三方\"}";

        WebhookInfo webhookInfo = JSON.parseObject(json, WebhookInfo.class);
        System.out.println(JSON.toJSONString(webhookInfo));

        //如果不是用JsonField屏蔽，那么即便转成父类，在程序中子类的方法不能调用，但是在转json时，子类的成员还是被转进json中
//        WebhookBaseInfo baseInfo = (WebhookBaseInfo) webhookInfo;

        System.out.println("------------------------------");
        //JSON过滤器
        PropertyFilter filter = new PropertyFilter() {
            @Override
            public boolean apply(Object object, String name, Object value) {
                return !name.endsWith("Name");
            }
        };
        System.out.println(JSON.toJSONString(webhookInfo, filter));
    }
}
