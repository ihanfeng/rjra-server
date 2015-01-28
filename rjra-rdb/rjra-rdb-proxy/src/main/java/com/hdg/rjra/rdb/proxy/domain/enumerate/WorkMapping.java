package com.hdg.rjra.rdb.proxy.domain.enumerate;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public enum WorkMapping implements BaseMapping {

    WorkId("work_id"),
    UserId("user_id"),
    CompanyId("company_id"),
    CategoryLeve1Id("category_leve1_id"),
    CategoryLeve2Id("category_leve2_id"),
    CategoryLeve3Id("category_leve3_id"),
    WorkAreaId("work_area_id"),
    WorkCityId("work_city_id"),
    WorkProvinceId("work_province_id"),
    WorkAddress("work_address"),
    WorkNeedPerson("work_need_person"),
    WorkWageId("work_wage_id"),
    WorkExperienceId("work_experience_id"),
    WorkWelfareIds("work_welfare_ids", " in "),
    WorkDesc("work_desc", " like "),
    WorkStatus("work_status");

    private String dbField;

    private String op;

    private WorkMapping(String dbField) {
        this.dbField = dbField;
        op = " = ";
    }

    private WorkMapping(String dbField, String op) {
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
