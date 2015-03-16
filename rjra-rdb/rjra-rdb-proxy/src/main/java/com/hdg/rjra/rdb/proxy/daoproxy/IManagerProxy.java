package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Area;
import com.hdg.rjra.rdb.proxy.domain.Manager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IManagerProxy extends Serializable {

    public Manager findManagerByNameAndPwd(String name, String pwd);

    public Integer updateManagerPwd(Long managerId, String pwd);

}
