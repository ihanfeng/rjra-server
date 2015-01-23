package com.hdg.rjra.base.exception;


import com.hdg.rjra.base.error.ResponseError;

/**
 * Created by Sisi on 14-9-12.
 */
public class ProxyException extends AbstractCustomException{

    /**
     * responseError
     */
    private ResponseError responseError;

    /**
     * ProxyException
     */
    public ProxyException() {
    }

    /**
     * ProxyException
     * @param responseError responseError
     */
    public ProxyException(ResponseError responseError) {
        this.responseError = responseError;
    }

    /**
     * ProxyException
     * @param msg msg
     */
    public ProxyException(String msg) {
        super(msg);
    }

    /**
     * ProxyException
     * @param msg msg
     * @param responseError responseError
     */
    public ProxyException(String msg, ResponseError responseError) {
        super(msg);
        this.responseError = responseError;
    }

    /**
     * ProxyException
     * @param msg msg
     * @param cause cause
     */
    public ProxyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * ProxyException
     * @param msg msg
     * @param responseError responseError
     * @param cause cause
     */
    public ProxyException(String msg, ResponseError responseError, Throwable cause) {
        super(msg, cause);
        this.responseError = responseError;
    }

    /**
     * ProxyException
     * @param cause cause
     */
    public ProxyException(Throwable cause) {
        super(cause);
    }

    /**
     * ProxyException
     * @param responseError responseError
     * @param cause cause
     */
    public ProxyException(ResponseError responseError, Throwable cause) {
        super(cause);
        this.responseError = responseError;
    }

    public ResponseError getResponseError() {
        return responseError;
    }

    public void setResponseError(ResponseError responseError) {
        this.responseError = responseError;
    }
}
