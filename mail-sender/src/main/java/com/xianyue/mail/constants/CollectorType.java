package com.xianyue.mail.constants;

/**
 * @author Xianyue
 * 
 *         data collection method. 1) memory queue. 2) kafka topic
 */
public enum CollectorType {
    MEMORY("memory"), KAFKA("kafka");
    private String type;

    private CollectorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
