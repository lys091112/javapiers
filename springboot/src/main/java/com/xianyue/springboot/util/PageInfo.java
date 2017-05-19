package com.xianyue.springboot.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Xianyue
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PageInfo {
    private int totalPage; //总页数
    private int totalCount; //总数量
    private int pageSize = 10; //每页大小
    private int pageIndex;  //当前页数


    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.count();
    }

    public int startIndex() {
        return (this.pageIndex - 1) * this.pageSize;
    }

    public void count() {
        int plus = (this.totalCount % this.pageSize) == 0 ? 0 : 1;
        this.totalPage = this.totalCount / this.pageSize + plus;
        if (totalPage <= 0) {
            this.totalPage = 1;
        }

        if (this.totalPage < this.pageIndex) {
            this.pageIndex = this.totalPage;
        }
        if (this.pageIndex < 1) {
            this.pageIndex = 1;
        }
    }
}
