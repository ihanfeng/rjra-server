package com.hdg.rjra.server.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2015/3/24.
 */
public interface MMSService extends Serializable {
    public String sendMessage(String[] mobiles, String msg) throws UnsupportedEncodingException;
}
