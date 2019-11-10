package com.xianyue.events;

/**
 * @author liuhongjun
 * @since 上午10:58 18-12-19
 */
public class JobEvent extends AbstractEvent<JobEventType> {

    private long jobId;

    public JobEvent(long jobId,JobEventType eventType) {
        super(eventType);
        this.jobId = jobId;
    }
}
