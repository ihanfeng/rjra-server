package com.hdg.rjra.base.error;

/**
 * Created by Rock on 2015/1/2 0002.
 */
public enum  ErrorType {

    UNKNOW_ERROR("UNKNOW_ERROR", -1L, "Unknow Error."),
    PARAM_EXCEPTION("PARAM_EXCEPTION", 100001L, "Request Param Error."),
    UPLOAD_IMAGE_FAIL("UPLOAD_IMAGE_FAIL", 200001L, "upload file fail."),
    MOBILE_ALREADY_EXISTS("MOBILE_ALREADY_EXISTS", 300001L, "The mobile already exists."),
    COMPANY_ALREADY_NOT_EXIST("COMPANY_ALREADY_NOT_EXIST", 300001L, "The company already not exist."),
    USER_ALREADY_NOT_EXIST("USER_ALREADY_NOT_EXIST", 300001L, "The user already not exist.");

    /**
     * 类型
     */
    private String type;

    /**
     * 错误码
     */
    private Long code;

    /**
     * 描述
     */
    private String message;

    /**
     * Creates a new instance of ExceptionType.
     *
     * @param type    type
     * @param code    code
     * @param message message
     * @author Administrator 创建时间 2014年7月2日 下午8:00:15
     */
    private ErrorType(String type, Long code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
    }

    /**
     * 获取type
     *
     * @return the type
     */
    public String getType() {
        return type;
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
    }
}
