package com.hdg.rjra.rdb.executer.rdbsql;

import com.alibaba.fastjson.JSON;
import com.hdg.rjra.rdb.handler.Executer;
import com.hdg.rjra.rdb.model.thrift.ResponseModel;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.proxy.utils.DaoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by Rock on 2014/10/21.
 */
public class FindListBySqlExecute implements Executer {
    private static Logger logger = LoggerFactory.getLogger(FindListBySqlExecute.class);

    private JdbcTemplate jdbcTemplate = DaoUtils.getInstance();

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public ResponseModel executeMethod(Object[] params) {
        logger.debug(">>>>>>>>>>>>>>  FindListBySqlExecute executeMethod >>>>>>>>>>>>>>");
        logger.debug("params >>>>>>>>>>>>>>" + JSON.toJSONString(params));
        ResponseModel responseModel = new ResponseModel();
        String sql = params[0].toString();
        List<Object> args = null;
        if (params.length > 1) {
            args = (List<Object>) params[1];
        }
        List<?> list = null;
        if (args == null) {
            logger.debug("SQL >>>>>>>>>>>>>>" + sql);
            logger.debug("args IS NULL");
            list = jdbcTemplate.queryForList(sql);
        } else {
            logger.debug("SQL >>>>>>>>>>>>>>" + sql);
            logger.debug("args  >>>>>>>>>>>>>> " + JSON.toJSONString(args));
            Object[] s = args.toArray();
            list = jdbcTemplate.queryForList(sql, s);
        }
        logger.debug("result >>>>> " + JSON.toJSONString(list));
        responseModel.setResult(list);
        responseModel.setResultType(ResultType.SUCCESS);
        return responseModel;
    }
}
