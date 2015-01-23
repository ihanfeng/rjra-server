package com.hdg.rjra.server.web.utils;

import com.hdg.rjra.base.constants.CommonConstants;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.base.output.OutputResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Title: ResponseUtils.java Description: ResponseUtils
 * 
 * @author Sisi 创建时间 2014年7月8日 下午6:26:02
 */
public class ResponseUtils {

    /**
     * 
     * @description 将Json字符串返回到客户端，并设定Header为UTF-8
     * @author Sisi 创建时间 2014年7月8日 下午6:25:13
     * @param outputJson
     *            待返回的Json字符串
     * @return ResponseEntity<String>
     */
    public static ResponseEntity<String> returnJsonWithUTF8(String outputJson) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(outputJson, headers, HttpStatus.OK);
    }

    /**
     * 
     * @description 构造响应结果
     * @author Sisi       
     * 创建时间 2014年7月10日 下午7:35:06     
     * @param errorType errorType
     * @param data data
     * @return OutputResult
     */
    public static OutputResult bulidOutputResult(ErrorType errorType, Object data) {
        OutputResult outputResult = new OutputResult();
        
        if (null != errorType) {
            outputResult.setFlag(CommonConstants.FAIL);
            outputResult.setErrorCode(errorType.getCode());
            outputResult.setMsg(errorType.getMessage());
        } else {
            outputResult.setFlag(CommonConstants.SUCCESS);
        }
        outputResult.setData(data);

        return outputResult;
    }
}
