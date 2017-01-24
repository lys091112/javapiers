package com.xianyue.mail.handler;

import com.xianyue.mail.constants.MailConstants;
import com.xianyue.mail.sender.ISender;
import com.xianyue.mail.sender.entity.MailEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Xianyue
 */
public abstract class AbstractHandler implements IHandler {
    private static Logger        logger = LoggerFactory.getLogger(AbstractHandler.class);
    private Map<String, ISender> senders;

    AbstractHandler(Map<String, ISender> senders) {
        this.senders = senders;
    }

    @Override
    public void handle(List<MailEntity> entities) {
        beforeSend(entities);
        if (entities.isEmpty()) {
            return;
        }
        send(entities);
        afterSend(entities);
    }

    abstract void beforeSend(List<MailEntity> entities);

    abstract void afterSend(List<MailEntity> entities);

    private void send(List<MailEntity> entities) {
        Optional<ISender> sender;
        for (MailEntity e : entities) {
            sender = getMailSender(e.getTemplate());
            if (!sender.isPresent()) {
                logger.error("can't find a mailSender. template is {}", e.getTemplate());
                continue;
            }
            sender.get().send(e);
        }
    }

    /**
     * 如果不指定发送的template策略，那么使用default策略的sender发送
     */
    private Optional<ISender> getMailSender(String template) {
        if (StringUtils.isEmpty(template)) {
            ISender sender = this.senders.get(MailConstants.DEFAULT);
            if (sender == null) {
                throw new IllegalArgumentException("there must be a default mailsender");
            }
            return Optional.of(sender);
        }
        return Optional.of(senders.get(template));
    }



}
