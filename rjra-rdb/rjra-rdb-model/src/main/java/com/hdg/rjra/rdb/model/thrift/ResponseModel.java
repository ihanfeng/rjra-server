package com.hdg.rjra.rdb.model.thrift;

/**
 * Created by Rock on 2014/10/20.
 */
public class ResponseModel {
    private ResultType resultType;
    private String message;
    private Object result;
    private String executer;

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getExecuter() {
        return executer;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }
}
