package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.WorkExperience;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IWorkExperienceProxy extends Serializable {

    public List<WorkExperience> findAllWorkExperience();
}
