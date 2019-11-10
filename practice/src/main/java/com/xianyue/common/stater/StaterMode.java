package com.xianyue.common.stater;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuhongjun
 * @note
 * @since 2019-05-28
 */
public class StaterMode<R extends ResultType, N extends NoticeType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaterMode.class);

    private Map<Integer, R> resultMaps;
    private List<N> noticeTypes;
    private ResultMode resultMode;

    public StaterMode(int modeSize, List<N> noticeTypes, List<R> resultTypes) {
        Preconditions.checkNotNull(resultTypes, "result types is null");
        Preconditions.checkNotNull(noticeTypes, "notice types is null");
        if (modeSize <= 0) {
            modeSize = 2;
        }
        this.noticeTypes = noticeTypes;
        this.resultMode = new ResultMode(modeSize);

        resultMaps = new HashMap<>();
        for (R r : resultTypes) {
            resultMaps.put(r.getFlag(), r);
        }
    }

    public long sendMode(R resultType, N noticeType, long originMode) {

        int dis = noticeType.getFlag() * resultMode.steps();
        long oldState = originMode & (resultMode.mask() << dis);

        long newState = resultType.getFlag() << dis;

        // 已经成功了，不再赋值为其他状态
        if (oldState == newState) {
            return originMode;
        }

        // 初始状态不用重置
        return originMode | newState;
    }

    // 结果解析
    public List<Pair<N, R>> parseMode(int mode) {
        return noticeTypes.stream()
            .map(notice -> {
                int state = (mode >> notice.getFlag() * resultMode.steps()) & resultMode.mask();
                return Pair.of(notice, resultMaps.get(state));
            }).collect(Collectors.toList());
    }


}
