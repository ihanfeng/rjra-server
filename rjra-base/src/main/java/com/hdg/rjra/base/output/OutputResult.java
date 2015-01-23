package com.hdg.rjra.base.output;

import com.hdg.rjra.base.utils.JsonUtils;

/**
 * 
 * Title: OutputResult.java    
 * Description: OutputResult
 * @author Sisi       
 * 创建时间 2014年7月14日 下午7:21:41
 */
public class OutputResult {
    /**
     * success/fail
     */
    private String flag;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 错误码
     */
    private Long errorCode;

    /**
     * 消息体
     */
    private Object data;

    /**  
     * 获取flag
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**          
     * 给 flag 赋值
     * @param flag flag 
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**  
     * 获取msg
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**          
     * 给 msg 赋值
     * @param msg msg 
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**  
     * 获取errorCode
     * @return the errorCode
     */
    public Long getErrorCode() {
        return errorCode;
    }

    /**          
     * 给 errorCode 赋值
     * @param errorCode errorCode 
     */
    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    /**  
     * 获取data
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**          
     * 给 data 赋值
     * @param data data 
     */
    public void setData(Object data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return JsonUtils.objectToJson(this);
    }

}
