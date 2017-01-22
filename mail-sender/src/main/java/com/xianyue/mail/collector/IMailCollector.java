package com.xianyue.mail.collector;

import com.xianyue.mail.sender.entity.MailEntity;

import java.util.List;

/**
 * @author Xianyue
 */
public interface IMailCollector {
    public int coresNumber();
    public List<MailEntity> fetchMails();
}
