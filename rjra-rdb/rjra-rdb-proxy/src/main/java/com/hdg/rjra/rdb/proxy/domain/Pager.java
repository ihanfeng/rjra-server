package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 专门用户分页
 * Created by Rock on 2014/10/22.
 */
public class Pager<T> implements Serializable {
    /**  描述   (@author: Rock) */
	    
	private static final long serialVersionUID = 6845233056252406745L;
	/**
     * 记录数totalSize
     */
    private Long totalSize;
    /**
     * 查询结果
     */
    private List<T> resultList;

    /**
     * 获取totalSize
     *
     * @return the totalSize
     */
    public Long getTotalSize() {
        return totalSize;
    }

    /**
     * 给 totalSize 赋值
     *
     * @param totalSize totalSize
     */
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * 获取resultList
     *
     * @return the resultList
     */
    public List<T> getResultList() {
        return resultList;
    }

    /**
     * 给 resultList 赋值
     *
     * @param resultList resultList
     */
    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
}
