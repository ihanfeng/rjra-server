package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.WorkExperience;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IWorkExperienceRdbService extends Serializable {

    public List<WorkExperience> findAllWorkExperience();
}
