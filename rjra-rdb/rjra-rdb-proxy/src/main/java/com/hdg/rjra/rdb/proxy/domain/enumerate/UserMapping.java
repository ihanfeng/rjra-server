package com.hdg.rjra.rdb.proxy.domain.enumerate;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public enum UserMapping implements BaseMapping {

    UserId("user_id"),
    ResumeId("resume_id"),
    CompanyId("company_id"),
    UserMobile("user_mobile"),
    UserNickName("user_nickname", " like "),
    UserGender("user_gender"),
    UserStatus("user_status"),
    UserLoginType("user_login_type"),
    UserDesc("user_desc", " like ");

    private String dbField;

    private String op;

    private UserMapping(String dbField) {
        this.dbField = dbField;
        op = " = ";
    }

    private UserMapping(String dbField, String op) {
        this.dbField = dbField;
        this.op = op;
    }

    public String getDbField() {
        return dbField;
    }

    public String getOp() {

        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
