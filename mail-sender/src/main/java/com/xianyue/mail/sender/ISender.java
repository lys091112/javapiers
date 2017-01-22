package com.xianyue.mail.sender;

import com.xianyue.mail.sender.entity.MailEntity;

/**
 * @author Xianyue
 */
public interface ISender {

    public boolean send(MailEntity mailInfo);
}
