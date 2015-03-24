package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.domain.Welfare;
import com.hdg.rjra.server.service.WelfareService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class WelfareServiceImpl implements WelfareService {

    @Autowired
    WelfareService welfareService;

    @Override
    public List<Welfare> findAllWelfare() {
        return welfareService.findAllWelfare();
    }
}
