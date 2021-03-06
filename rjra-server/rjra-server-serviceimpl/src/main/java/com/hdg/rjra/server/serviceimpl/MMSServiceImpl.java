package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.properties.CustomizedPropertyConfigurer;
import com.hdg.common.utils.HttpRequestUtils;
import com.hdg.rjra.server.service.MMSService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2015/3/26.
 */
@Service
public class MMSServiceImpl implements MMSService {
    @Override
    public String sendMessage(String[] mobiles, String msg) throws UnsupportedEncodingException {

        String account = CustomizedPropertyConfigurer.getContextPropertyForString("mms.account");
        String pswd = CustomizedPropertyConfigurer.getContextPropertyForString("mms.pswd");
        String product = CustomizedPropertyConfigurer.getContextPropertyForString("mms.product");
        String ip = CustomizedPropertyConfigurer.getContextPropertyForString("mms.ip");
        String port = CustomizedPropertyConfigurer.getContextPropertyForString("mms.port");
        String needstatus = CustomizedPropertyConfigurer.getContextPropertyForString("mms.needstatus");
        StringBuffer request = new StringBuffer();
        StringBuffer param = new StringBuffer();
        //"http://222.73.117.158/msg/HttpSendSM?account=111111&pswd=123456&mobile=18900000000,13800138000&msg=test&needstatus=true&product=99999";
        request.append("http://");
        request.append(ip);
        request.append(":");
        request.append(port);
        request.append("/msg/HttpSendSM");
        param.append("account=");
        param.append(account);
        param.append("&pswd=");
        param.append(pswd);
        param.append("&mobile=");
        for (int i = 0; i < mobiles.length; i++) {
            param.append(mobiles[i]);
            param.append(",");
        }
        param.append("&msg=");
        param.append(URLEncoder.encode(msg.toString(),"UTF-8"));
        param.append("&needstatus=");
        param.append(needstatus);
        param.append("&product=");
        param.append(product);
        String requestJson = HttpRequestUtils.sendPost(request.toString(), param.toString());
        String[] result = requestJson.split(",");
        return result[1];
    }
}
