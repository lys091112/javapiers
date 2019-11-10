package com.xianyue.events;


/**
 * @author liuhongjun
 * @since 上午11:05 18-12-19
 */
public class StartTaskEvent extends TaskEvent {

    public StartTaskEvent(long jobId) {
        super(jobId, TaskEventType.START);
    }
}
