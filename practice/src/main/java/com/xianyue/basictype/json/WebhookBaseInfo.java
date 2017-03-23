package com.xianyue.basictype.json;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WebhookBaseInfo {
    private String infoSource; // 消息来源
    private String eventId;// 告警事件编号
    private Long createTime;// 产生时间
    private String eventType;// 事件类型
    private String appName;// 应用名称
    private String alertPolicy;// 归属告警策略
    private String alertRule;// 告警规则
    private List<String> ruleContent;// 告警条件内容
    private String nodeType;// 规则类型
    private Boolean acceptanceStatus;// 受理状态
    private String acceptancePerson;// 受理人
    private String alertObjectStatus;// 告警对象状态
    private List<String> realtimeIndex;// 实时指标
}
