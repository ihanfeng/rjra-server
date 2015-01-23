package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Wage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IWageProxy extends Serializable {

    public List<Wage> findAllWage();
}
