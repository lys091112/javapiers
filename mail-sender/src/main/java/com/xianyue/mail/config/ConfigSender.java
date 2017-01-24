package com.xianyue.mail.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xianyue
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigSender {
    private String collector;
    private String defaultSender;
    private String handler;
    private String senders;
}
