package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.Education;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IEducationRdbService extends Serializable {

    public List<Education> findAllEducation();
}
