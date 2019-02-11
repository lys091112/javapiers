package com.xianyue.events;

/**
 * @author liuhongjun
 * @since 上午10:53 18-12-19
 * <p>
 * copy from hadoop
 * <p>
 * 抽象事件类，所有类型的事件，都可以通过定义eventType,并继承AbstractEvent 来定义一类事件
 */
public interface Event<TYPE extends Enum<TYPE>> {

    TYPE getEventType();

    long getTimeStamp();

    String toString();

}
