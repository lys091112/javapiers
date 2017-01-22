package com.xianyue.mail.sender.impl;

import com.xianyue.mail.sender.ISender;
import com.xianyue.mail.sender.entity.MailEntity;

/**
 * @author Xianyue
 */
public class CommonSender implements ISender {

    @Override
    public boolean send(MailEntity mailInfo) {
        return false;
    }
}
