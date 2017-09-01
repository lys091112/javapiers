package com.xianyue.basictype.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import lombok.Data;

public class JsonMain {

  public static void main(String[] args) {
    filterSomeKeys();
    supperClassConvert();
  }

  private static void filterSomeKeys() {
    String json =
        "{\"acceptanceStatus\":false,\"alertObject\":{\"agentId\":1,\"agentName\":\"Java:mock_system1"
            + "(mock1.oneapm.me)\",\"metricId\":195,\"metricName\":\"mock2.oneapm.me:8080/login_service\","
            + "\"systemId\":1,\"systemName\":\"mock_app1\",\"tierId\":1,\"tierName\":\"mock_system1\"},"
            + "\"alertObjectStatus\":\"严重\",\"alertPolicy\":\"mock_app1_报警\",\"alertRule\":\"内部远程调用\","
            + "\"appName\":\"mock_app1\",\"createTime\":1490234014511,"
            + "\"eventId\":\"35a2611e-e8a7-4bc8-8d85-7b34267eb073\",\"eventType\":\"严重报警持续事件\","
            + "\"infoSource\":\"ONEAPM\",\"nodeType\":\"remoteCall\",\"policyId\":1,"
            + "\"realtimeIndex\":[\"每分钟平均响应时间:0.01s\"],\"ruleContent\":[\"严重 : 过去 5 分钟，每分钟平均响应时间 的 平均值 小于 5s\",\"警告"
            + " : 过去 5 分钟，每分钟平均响应时间 的 平均值 小于 3s\"],\"ruleId\":\"ONEAPM_ALERT_AI_9_1490232871585_remoteCall_3_1\","
            + "\"systemId\":1,\"time\":1490234014511,\"url\":\"http://10.128.32.121:8099/api/v1/data/alert\","
            + "\"webhookName\":\"俊成第三方\"}";

    WebhookInfo webhookInfo = JSON.parseObject(json, WebhookInfo.class);

    //JSON过滤器
    PropertyFilter filter =
        new PropertyFilter() {
          @Override
          public boolean apply(Object object, String name, Object value) {
            return !name.endsWith("Name");
          }
        };
    webhookInfo.setRealtimeIndex(null);
    System.out.println(JSON.toJSONString(webhookInfo, filter));
  }

  private static void supperClassConvert() {
    String s = "{\"name\":\"Google\",\"age\":\"12\"}";
    Exter exter = JSON.parseObject(s, Inner.class);
    System.out.println(exter);
  }

  public static class Exter {}

  @Data
  static class Inner extends Exter {
    private String name;
    private String age;
  }

  @Data
  static class Inner2 extends Exter {
    private String grade;
    private String sex;
  }
}
