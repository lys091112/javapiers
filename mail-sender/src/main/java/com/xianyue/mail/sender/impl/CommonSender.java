package com.xianyue.mail.sender.impl;

import com.xianyue.mail.sender.IMailEntity;
import com.xianyue.mail.sender.ISender;
import com.xianyue.mail.sender.entity.MailEntity;

import java.util.Properties;

/**
 * @author Xianyue
 */
public class CommonSender implements ISender {

    @Override
    public boolean init(Properties config) {
        return false;
    }

    @Override
    public boolean send(IMailEntity mailInfo) {
        return false;
    }
}
