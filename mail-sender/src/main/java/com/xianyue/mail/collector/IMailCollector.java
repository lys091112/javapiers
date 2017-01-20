package com.xianyue.mail.collector;

import com.xianyue.mail.sender.IMailSender;
import com.xianyue.mail.sender.entity.MailEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Xianyue
 */
public interface IMailCollector {
    public int coresNumber();
    public List<MailEntity> fetchMails();
}
