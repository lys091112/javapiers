package com.xianyue.events;

/**
 * @author liuhongjun
 * @since 上午11:04 18-12-19
 */
public class TaskEvent extends AbstractEvent<TaskEventType> {

    private long jobId;

    public TaskEvent(long jobId, TaskEventType eventType) {
        super(eventType);
        this.jobId = jobId;
    }
}
