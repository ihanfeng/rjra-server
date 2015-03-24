package com.hdg.rjra.customer.web.controller;

import com.hdg.common.output.OutputResult;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel1;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel2;
import com.hdg.rjra.rdb.proxy.domain.CategoryLevel3;
import com.hdg.rjra.server.model.param.category.CategoryParam;
import com.hdg.rjra.server.service.CategoryService;
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
 * Created by Administrator on 2015/3/23.
 */
@RequestMapping("/category")
@Controller
public class CategoryController {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "findCategoryLevel1ByCategoryLevel1Id")
    @ResponseBody
    public ResponseEntity<String> findCategoryLevel1ByCategoryLevel1Id(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        CategoryLevel1 data = null;
        try {
            CategoryParam categoryParam = JsonUtils.jsonToObject(param, CategoryParam.class);
            data = categoryService.findCategoryLevel1ByCategoryLevel1Id(categoryParam.getCategoryLevel1Id());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findCategoryLevel1ByCategoryLevel1Id->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findAllCategoryLevel1")
    @ResponseBody
    public ResponseEntity<String> findAllCategoryLevel1(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<CategoryLevel1> data = null;
        try {
            data = categoryService.findAllCategoryLevel1();
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllCategoryLevel1->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findCategoryLevel2ByCategoryLevel2Id")
    @ResponseBody
    public ResponseEntity<String> findCategoryLevel2ByCategoryLevel2Id(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        CategoryLevel2 data = null;
        try {
            CategoryParam categoryParam = JsonUtils.jsonToObject(param, CategoryParam.class);
            data = categoryService.findCategoryLevel2ByCategoryLevel2Id(categoryParam.getCategoryLevel2Id());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findCategoryLevel2ByCategoryLevel2Id->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findCategoryLevel2ByCategoryLevel1Id")
    @ResponseBody
    public ResponseEntity<String> findCategoryLevel2ByCategoryLevel1Id(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<CategoryLevel2> data = null;
        try {
            CategoryParam categoryParam = JsonUtils.jsonToObject(param, CategoryParam.class);
            data = categoryService.findCategoryLevel2ByCategoryLevel1Id(categoryParam.getCategoryLevel1Id());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findCategoryLevel2ByCategoryLevel1Id->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findCategoryLevel3ByCategoryLevel3Id")
    @ResponseBody
    public ResponseEntity<String> findCategoryLevel3ByCategoryLevel3Id(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        CategoryLevel3 data = null;
        try {
            CategoryParam categoryParam = JsonUtils.jsonToObject(param, CategoryParam.class);
            data = categoryService.findCategoryLevel3ByCategoryLevel3Id(categoryParam.getCategoryLevel3Id());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findCategoryLevel3ByCategoryLevel3Id->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findCategoryLevel3ByCategoryLevel2Id")
    @ResponseBody
    public ResponseEntity<String> findCategoryLevel3ByCategoryLevel2Id(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<CategoryLevel3> data = null;
        try {
            CategoryParam categoryParam = JsonUtils.jsonToObject(param, CategoryParam.class);
            data = categoryService.findCategoryLevel3ByCategoryLevel2Id(categoryParam.getCategoryLevel2Id());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findCategoryLevel3ByCategoryLevel2Id->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
