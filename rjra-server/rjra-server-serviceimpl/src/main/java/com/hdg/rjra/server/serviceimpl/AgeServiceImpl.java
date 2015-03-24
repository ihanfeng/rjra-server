package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.daoproxy.IAgeProxy;
import com.hdg.rjra.rdb.proxy.domain.Age;
import com.hdg.rjra.server.service.AgeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class AgeServiceImpl implements AgeService {

    @Autowired
    IAgeProxy ageProxy;

    @Override
    public List<Age> findAllAge() {
        return ageProxy.findAllAge();
    }
}
