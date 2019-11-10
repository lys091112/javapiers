package com.xianyue.events;

/**
 * @author liuhongjun
 * @since 上午11:02 18-12-19
 */
public class RunningJobEvent extends JobEvent {

    public RunningJobEvent(long jobId) {
        super(jobId, JobEventType.RUNNING);
    }
}
