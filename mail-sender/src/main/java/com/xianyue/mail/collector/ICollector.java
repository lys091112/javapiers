package com.xianyue.mail.collector;

import com.xianyue.mail.handler.IHandler;
import com.xianyue.mail.sender.entity.MailEntity;

import java.util.List;

/**
 * @author Xianyue
 */
public interface ICollector {
    public List<Runnable> collectors(IHandler handler);
}
