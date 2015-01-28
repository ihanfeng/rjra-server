package com.hdg.rjra.rdb.proxy.domain.enumerate;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public enum WorkMapping implements BaseMapping {

    WorkId("work_id"),
    UserId("user_id"),
    CompanyId("company_id"),
    CompanyName("company_name"),
    CategoryLevel1Id("category_level1_id"),
    CategoryLevel2Id("category_level2_id"),
    CategoryLevel3Id("category_level3_id"),
    WorkAreaId("work_area_id"),
    WorkCityId("work_city_id"),
    WorkProvinceId("work_province_id"),
    WorkAddress("work_address", " like "),
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
