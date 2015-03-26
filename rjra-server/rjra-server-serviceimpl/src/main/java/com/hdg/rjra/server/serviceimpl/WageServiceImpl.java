package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.daoproxy.IWageProxy;
import com.hdg.rjra.rdb.proxy.domain.Wage;
import com.hdg.rjra.server.service.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
@Service
public class WageServiceImpl implements WageService {

    @Autowired
    IWageProxy wageProxy;

    @Override
    public List<Wage> findAllWage() {
        return wageProxy.findAllWage();
    }
}
