package com.xianyue.mail.sender;

import com.xianyue.mail.sender.entity.MailEntity;

import java.util.Properties;

/**
 * @author Xianyue
 */
public interface ISender {
    public boolean init(Properties config);
    public boolean send(MailEntity mailInfo);
}
