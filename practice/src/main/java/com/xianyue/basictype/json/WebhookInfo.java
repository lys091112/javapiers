package com.xianyue.basictype.json;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WebhookInfo extends WebhookBaseInfo {
    @JSONField(serialize = false)
    private String url;
    private String webhookName; //webhook的名称
    @JSONField(serialize = false)
    private Long systemId;  //系统id
    @JSONField(serialize = false)
    private String ruleId; //规则id
    @JSONField(serialize = false)
    private Long policyId; //策略id
    @JSONField(serialize = false)
    private Long time; //产生时间
}
