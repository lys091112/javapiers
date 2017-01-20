package com.xianyue.mail.sender;

import com.xianyue.mail.sender.entity.MailEntity;

/**
 * @author Xianyue
 */
public interface IMailSender {

    public boolean send(MailEntity mailInfo);
}
