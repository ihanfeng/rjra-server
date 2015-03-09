package com.hdg.rjra.base.error;

import com.hdg.common.error.ResponseError;

/**
 * Created by Rock on 2015/1/2 0002.
 */
public enum  ErrorType {

    DEFFAULT,
    UNKNOW_ERROR(-1L, "Unknow Error."),
    PARAM_EXCEPTION(100001L, "Request Param Error."),
    UPLOAD_IMAGE_FAIL(200001L, "upload file fail."),
    MOBILE_ALREADY_EXISTS(300001L, "The mobile already exists."),
    COMPANY_ALREADY_NOT_EXIST(300002L, "The company already not exist."),
    USER_ALREADY_NOT_EXIST(300003L, "The user already not exist."),
    MANAGER_ALREADY_NOT_EXIST(300004L, "The manager already not exist."),
    USER_MOBILE_OR_PWD_IS_ERROR(400000L, "The user mobile or pwd is error."),
    MANAGER_MOBILE_OR_PWD_IS_ERROR(400001L, "The manager mobile or pwd is error."),
    INVALID_TOKEN(500000L, "This token is invalid");

    /**
     * 错误码
     */
    private Long code;

    /**
     * 描述
     */
    private String message;

    private ResponseError responseError = new ResponseError();;

    /**
     * Creates a new instance of ExceptionType.
     *
     * @param code    code
     * @param message message
     * @author Administrator 创建时间 2014年7月2日 下午8:00:15
     */
    private ErrorType(Long code, String message) {
        this.code = code;
        this.message = message;
        responseError.setCode(code);
        responseError.setMessage(message);
    }
    /**
     * Creates a new instance of ExceptionType.
     *
     * @author Administrator 创建时间 2014年7月2日 下午8:00:15
     */
    private ErrorType() {
    }

    /**
     * 获取code
     *
     * @return the code
     */
    public Long getCode() {
        return code;
    }

    /**
     * 获取message
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * setMessage
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
        responseError.setMessage(message);
    }

    public ResponseError getResponseError() {
        return responseError;
    }
}
