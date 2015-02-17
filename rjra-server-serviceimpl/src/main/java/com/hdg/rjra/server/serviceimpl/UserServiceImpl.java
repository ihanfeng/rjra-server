package com.hdg.rjra.server.serviceimpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.base.enumerate.UserStatus;
import com.hdg.rjra.rdb.domain.Pager;
import com.hdg.rjra.rdb.domain.User;
import com.hdg.rjra.rdb.service.IUserRdbService;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.ResumeService;
import com.hdg.rjra.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    IUserRdbService userRdbService;

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
        Long userId = userRdbService.saveUser(resumeId, companyId, mobile, pwd);
        User user = userRdbService.findUserByUserId(userId);
        return getUserBo(user);
    }

    @Override
    public Integer updateUser(UserBo userBo) {
        User user = new User();
        ConversionUtils.conversion(userBo, user);
        return userRdbService.updateUser(user);
    }

    @Override
    public UserBo findUserByUserId(Long userId) {
        User user = userRdbService.findUserByUserId(userId);
        return getUserBo(user);
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
//        Long resumeID = user.getResumeId();
//        if(null != resumeID)
//        {
//            ResumeBo resumeBo = resumeService.findResumeByResumeId(resumeID);
//            userBo.setResumeDetail(resumeBo);
//        }
//        Long companyId = user.getCompanyId();
//        if(null != companyId)
//        {
//            CompanyBo companyBo = companyService.findCompanyByCompanyId(companyId);
//            userBo.setCompanyDetail(companyBo);
//        }
        ConversionUtils.conversion(user, userBo);
        return userBo;
    }

    @Override
    public Pager<UserBo> findAllUserPager(Integer firstResult, Integer sizeNo) {
        Pager<User> userPager = userRdbService.findAllUserPager(new Integer[]{UserStatus.Active.getCode(), UserStatus.Pause.getCode()}, firstResult, sizeNo);
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
        return userRdbService.updateUserHead(userId, fileId);
    }

    @Override
    public Pager<UserBo> findNearUserPager(Double lng, Double lat, Integer raidus, Integer firstResult, Integer sizeNo) {
        Pager<User> userPager = userRdbService.findNearUserPager(lng, lat, raidus, new Integer[]{UserStatus.Active.getCode()}, firstResult, sizeNo);
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
        User user = userRdbService.findUserByMobileAndPwd(mobile, pwd);
        return getUserBo(user);
    }

    @Override
    public Integer updateUserLocation(Long userId, Double lng, Double lat) {
        return userRdbService.updateUserLocation(userId, lng, lat);
    }

    @Override
    public Integer findUserExistsByMobile(String mobile) {
        return userRdbService.findUserExistsByMobile(mobile);
    }
}
