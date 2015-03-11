package com.hdg.rjra.server.service;


import com.hdg.rjra.server.model.bo.manager.ManagerBo;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface ManagerService {

    public ManagerBo findManagerByNameAndPwd(String name, String pwd);

}
