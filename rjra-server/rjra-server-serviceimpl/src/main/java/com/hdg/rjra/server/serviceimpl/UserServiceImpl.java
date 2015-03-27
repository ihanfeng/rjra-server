package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.base.enumerate.UserStatus;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.User;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.resume.ResumeBo;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.ResumeService;
import com.hdg.rjra.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    IUserProxy userProxy;

    @Autowired
    ResumeService resumeService;

    @Autowired
    CompanyService companyService;

    @Autowired
    FileService fileService;

    @Override
    public UserBo saveUser(String mobile, String pwd) {
        Long resumeId = resumeService.createResume(mobile);
        Long companyId = companyService.createCompany();
        Long userId = userProxy.saveUser(resumeId, companyId, mobile, pwd);
        User user = userProxy.findUserByUserId(userId);
        return getUserBo(user);
    }

    @Override
    public Integer updateUser(UserBo userBo) {
        User user = new User();
        ConversionUtils.conversion(userBo, user);
        return userProxy.updateUser(user);
    }

    @Override
    public UserBo findUserByUserId(Long userId) {
        User user = userProxy.findUserByUserId(userId);
        return getUserBo(user);
    }

    @Override
    public UserBo findUserByResumeId(Long resumeId) {
        User user = userProxy.findUserByResumeId(resumeId);
        return getSimpleUserBo(user);
    }

    private UserBo getSimpleUserBo(User user) {
        if (null == user) {
            return null;
        }
        UserBo userBo = new UserBo();
        Long hadeImage = user.getUserHeadImageFile();
        if (null != hadeImage) {
            AccountFileBo userImageInfo = fileService.findAccountFileById(hadeImage);
            userBo.setUserHeadImageFileDetail(userImageInfo);
        }
        ConversionUtils.conversion(user, userBo);
        return userBo;
    }

    private UserBo getUserBo(User user) {
        if (null == user) {
            return null;
        }
        UserBo userBo = new UserBo();
        Long hadeImage = user.getUserHeadImageFile();
        if (null != hadeImage) {
            AccountFileBo userImageInfo = fileService.findAccountFileById(hadeImage);
            userBo.setUserHeadImageFileDetail(userImageInfo);
        }
        Long resumeID = user.getResumeId();
        if (null != resumeID) {
            ResumeBo resumeBo = resumeService.findResumeByResumeId(resumeID);
            userBo.setResumeDetail(resumeBo);
        }
        Long companyId = user.getCompanyId();
        if (null != companyId) {
            CompanyBo companyBo = companyService.findCompanyByCompanyId(companyId);
            userBo.setCompanyDetail(companyBo);
        }
        ConversionUtils.conversion(user, userBo);
        return userBo;
    }

    @Override
    public Pager<UserBo> findAllUserPager(Integer firstResult, Integer sizeNo) {
        Pager<User> userPager = userProxy.findAllUserPager(new Integer[]{UserStatus.Active.getCode(), UserStatus.Pause.getCode()}, firstResult, sizeNo);
        Pager<UserBo> userBoPager = new Pager<UserBo>();
        List<UserBo> userBoList = new ArrayList<UserBo>();
        for (User user : userPager.getResultList()) {
            userBoList.add(getUserBo(user));
        }
        userBoPager.setResultList(userBoList);
        userBoPager.setTotalSize(userPager.getTotalSize());
        return userBoPager;
    }

    @Override
    public Integer updateUserHead(Long userId, Long fileId) {
        return userProxy.updateUserHead(userId, fileId);
    }

    @Override
    public Pager<UserBo> findNearUserPager(Double lng, Double lat, Integer raidus, Integer firstResult, Integer sizeNo) {
        Pager<User> userPager = userProxy.findNearUserPager(lng, lat, raidus, new Integer[]{UserStatus.Active.getCode()}, firstResult, sizeNo);
        Pager<UserBo> userBoPager = new Pager<UserBo>();
        List<UserBo> userBoList = new ArrayList<UserBo>();
        for (User user : userPager.getResultList()) {
            userBoList.add(getUserBo(user));
        }
        userBoPager.setResultList(userBoList);
        userBoPager.setTotalSize(userPager.getTotalSize());
        return userBoPager;
    }

    @Override
    public UserBo findUserByMobileAndPwd(String mobile, String pwd) {
        User user = userProxy.findUserByMobileAndPwd(mobile, pwd);
        return getUserBo(user);
    }

    @Override
    public Integer updateUserPwd(Long userId, String pwd) {
        return userProxy.updateUserPwd(userId, pwd);
    }

    @Override
    public Integer updateUserLocation(Long userId, Double lng, Double lat) {
        return userProxy.updateUserLocation(userId, lng, lat);
    }

    @Override
    public Integer findUserExistsByMobile(String mobile) {
        return userProxy.findUserExistsByMobile(mobile);
    }
}
