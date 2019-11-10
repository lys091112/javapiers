package com.xianyue.common.stater.inform;

import com.xianyue.common.stater.NoticeType;

/**
 * @author liuhongjun
 * @note
 * @since 2019-06-14
 */
public enum InformNoticeEvent implements NoticeType {

    // 租房应检
    BE_EXAMINED(0, "beExamined", "被抽检"),
    EXAMINED_LAST_CALL(1, "examinedLastCall", "最后24小时应检提醒"),
    EXAMINED_TIMEOUT(2, "examinedTimeOut", "应检超时"),
    EXAMINED_SUCCESS(3, "examinedSuccess", "应检成功"),
    EXAMINED_FAILED(4, "examinedFailed", "应检失败");

    private int code;
    private String key;
    private String message;

    InformNoticeEvent(int code, String key, String message) {
        this.code = code;
        this.key = key;
        this.message = message;
    }

    @Override
    public int getFlag() {
        return code;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }}
