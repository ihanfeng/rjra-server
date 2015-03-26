package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.properties.CustomizedPropertyConfigurer;
import com.hdg.rjra.server.service.MMSService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/3/26.
 */
@Service
public class MMSServiceImpl implements MMSService {
    @Override
    public void sendMessage() {
        String cd = CustomizedPropertyConfigurer.getContextPropertyForString("upload_file_path");
    }
}
