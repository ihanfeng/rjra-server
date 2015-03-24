package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.domain.Wage;
import com.hdg.rjra.server.service.WageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class WageServiceImpl implements WageService {

    @Autowired
    WageService wageService;

    @Override
    public List<Wage> findAllWage() {
        return wageService.findAllWage();
    }
}
