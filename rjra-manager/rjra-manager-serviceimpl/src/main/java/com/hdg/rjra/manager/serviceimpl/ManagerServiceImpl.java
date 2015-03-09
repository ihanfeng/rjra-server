package com.hdg.rjra.manager.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.manager.model.bo.manager.ManagerBo;
import com.hdg.rjra.manager.service.ManagerService;
import com.hdg.rjra.rdb.proxy.daoproxy.IManagerProxy;
import com.hdg.rjra.rdb.proxy.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    IManagerProxy managerProxy;

    private ManagerBo getManagerBo(Manager manager) {
        if (null == manager) {
            return null;
        }
        ManagerBo managerBo = new ManagerBo();
        ConversionUtils.conversion(manager, managerBo);
        return managerBo;
    }

    @Override
    public ManagerBo findManagerByNameAndPwd(String name, String pwd) {
        Manager manager = managerProxy.findManagerByNameAndPwd(name, pwd);
        return getManagerBo(manager);
    }
}
