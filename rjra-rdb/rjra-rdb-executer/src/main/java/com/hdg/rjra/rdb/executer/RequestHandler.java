package com.hdg.rjra.rdb.executer;

import com.hdg.rjra.rdb.handler.Executer;
import com.hdg.rjra.rdb.handler.IRequestHandler;
import com.hdg.rjra.rdb.model.thrift.ResponseModel;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2014/10/20.
 */
public class RequestHandler implements IRequestHandler {
    private static Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    Map<String, Map<String, Executer>> map;

    public Map<String, Map<String, Executer>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<String, Executer>> map) {
        logger.info("xxxxxxxx   ExecuteMap      xxxxxxxx");
        logger.info(map.toString());
        logger.info("xxxxxxxx   ExecuteMap      xxxxxxxx");
        this.map = map;
    }

    @Override
    public ResponseModel handler(String module, String executer, List<Object> params) {
        ResponseModel responseModel = new ResponseModel();
        Map<String, Executer> executeMap = map.get(module);
        if (executeMap != null) {
            Executer executer1 = executeMap.get(executer);
            if (executer1 != null) {
                logger.debug("找到execute >>>> "+ executer1.getClass().getName());
                logger.debug("params >>>> "+ params);
                if (params != null) {
                    responseModel = executer1.executeMethod(params.toArray());
                } else {
                    responseModel = executer1.executeMethod(null);
                }
                responseModel.setExecuter(executer);
            } else {
                responseModel.setResultType(ResultType.BUSINESS_ERROR);
                responseModel.setMessage("没有找到 execute");
            }
        } else {
            responseModel.setResultType(ResultType.BUSINESS_ERROR);
            responseModel.setMessage("没有找到 module");
        }
        return responseModel;
    }
}
