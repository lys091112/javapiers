package com.xianyue.mail.handler;

import com.xianyue.mail.sender.entity.MailEntity;

import java.util.List;

/**
 * @author Xianyue
 */
public interface IHandler {
    public void handle(List<MailEntity> entities);
}
