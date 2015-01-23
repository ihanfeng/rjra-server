package com.hdg.rjra.rdb.executer.recruitmentinfo;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindRecruitmentInfoByRecruitmentInfoId extends AbstractExecuter {

    static String sql = "select * from rec_info where info_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long infoId = (Long) params[0];
        Object[] objects = new Object[]{infoId};
        List list = getJdbcTemplate().query(sql, objects, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
