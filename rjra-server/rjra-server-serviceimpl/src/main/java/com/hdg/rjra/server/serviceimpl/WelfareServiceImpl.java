package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.rdb.proxy.daoproxy.IWelfareProxy;
import com.hdg.rjra.rdb.proxy.domain.Welfare;
import com.hdg.rjra.server.service.WelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
@Service
public class WelfareServiceImpl implements WelfareService {

    @Autowired
    IWelfareProxy welfareProxy;

    @Override
    public List<Welfare> findAllWelfare() {
        return welfareProxy.findAllWelfare();
    }
}
