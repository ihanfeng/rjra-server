package com.hdg.rjra.rdb.domain.enumerate;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public enum ResumeMapping implements BaseMapping {

    ResumeId("resume_id"),
    CategoryLevel1Ids("category_level1_ids", " in "),
    CategoryLevel2Ids("category_level2_ids", " in "),
    CategoryLevel3Ids("category_level3_ids", " in "),
    ResumeUserName("resume_user_name"),
    ResumeGender("resume_gender"),
    ResumeExperience("resume_experience"),
    ResumeWage("resume_wage"),
    ResumeWorkStatus("resume_work_status"),
    ResumeWantWorkAreaId("resume_want_work_area_id"),
    ResumeWantWorkCityId("resume_want_work_city_id"),
    ResumeWantWorkProvinceId("resume_want_work_province_id"),
    ResumeMobile("resume_mobile"),
    ResumeQQ("resume_qq"),
    ResumeDesc("resume_desc"),
    ResumeStatus("resume_status");

    private String dbField;

    private String op;

    private ResumeMapping(String dbField) {
        this.dbField = dbField;
        op = " = ";
    }

    private ResumeMapping(String dbField, String op) {
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
