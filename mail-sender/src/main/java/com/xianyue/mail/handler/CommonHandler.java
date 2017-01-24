package com.xianyue.mail.handler;

import com.xianyue.mail.sender.ISender;
import com.xianyue.mail.sender.entity.MailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author Xianyue
 */
public class CommonHandler extends AbstractHandler {
    private static final Logger logger = LoggerFactory.getLogger(CommonHandler.class);

    public CommonHandler(Map<String, ISender> senders) {
        super(senders);
    }

    @Override
    void beforeSend(List<MailEntity> entities) {

    }

    @Override
    void afterSend(List<MailEntity> entities) {

    }

}
