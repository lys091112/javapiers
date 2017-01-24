package com.xianyue.mail.constants;

/**
 * @author Xianyue
 *         <p>
 *         data collection method. 1) memory queue. 2) kafka topic
 */
public enum CollectorType {
    NONE("none"), MEMORY("memory"), KAFKA("kafka");
    private String type;

    private CollectorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     * get collectorType enum by type
     */
    public static CollectorType collectorType(String type) {
        CollectorType[] types = CollectorType.values();
        for (CollectorType c : types) {
            if (type.equals(c.getType())) {
                return c;
            }
        }
        return NONE;
    }

    /**
     * whether collector contain this type
     */
    public static boolean contains(String type) {
        CollectorType[] types = CollectorType.values();
        for (CollectorType c : types) {
            if (type.equals(c.getType())) {
                return true;
            }
        }
        return false;
    }
}
