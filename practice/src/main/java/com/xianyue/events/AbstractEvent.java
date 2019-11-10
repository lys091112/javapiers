package com.xianyue.events;

/**
 * @author liuhongjun
 * @since 上午10:55 18-12-19
 */
public abstract class AbstractEvent<TYPE extends Enum<TYPE>> implements Event<TYPE> {


    private final TYPE eventType;

    private long timestamp;

    public AbstractEvent(TYPE eventType) {
        this.eventType = eventType;
        this.timestamp = -1L;
    }

    public AbstractEvent(TYPE eventType, long timestamp) {
        this.eventType = eventType;
        this.timestamp = timestamp;
    }

    @Override
    public TYPE getEventType() {
        return eventType;
    }

    @Override
    public long getTimeStamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "AbstractEvent{" +
            "eventType=" + eventType +
            ", timestamp=" + timestamp +
            '}';
    }
}
