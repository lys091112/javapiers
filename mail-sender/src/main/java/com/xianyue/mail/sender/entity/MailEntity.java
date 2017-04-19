package com.xianyue.mail.sender.entity;

import com.xianyue.mail.sender.IMailEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xianyue
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailEntity implements IMailEntity {
    private String         from;       // 发件人
    private List<String>   to;         // 收件人
    private String         subject;    // 邮件主题
    private String         content;    // 邮件内容
    private MailAttachment attachment; // 附件
    private String         template;   // 邮件所用策略,默认default
}
