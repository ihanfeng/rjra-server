package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.base.output.OutputResult;
import com.hdg.rjra.base.utils.JsonUtils;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.web.controller.param.file.FileParam;
import com.hdg.rjra.server.web.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Rock
 */
@RequestMapping("/file")
@Controller
public class FileController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    @RequestMapping(value = "findAccountFileByIds")
    @ResponseBody
    public ResponseEntity<String> findAccountFileByIds(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        List<AccountFileBo> data = null;
        try {
            FileParam fileParam = JsonUtils.jsonToObject(param, FileParam.class);
            data = fileService.findAccountFileByIds(fileParam.getFileIds());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAccountFileByIds->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
