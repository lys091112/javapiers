package com.xianyue.mail.sender.impl;

import com.xianyue.mail.sender.IMailSender;
import com.xianyue.mail.sender.entity.MailEntity;

/**
 * @author Xianyue
 */
public class CommonSender implements IMailSender{

    @Override
    public boolean send(MailEntity mailInfo) {
        return false;
    }
}
