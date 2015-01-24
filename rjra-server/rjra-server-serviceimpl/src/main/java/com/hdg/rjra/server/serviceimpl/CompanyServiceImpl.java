package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.base.enumerate.CompanyStatus;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.ICompanyProxy;
import com.hdg.rjra.rdb.proxy.domain.Company;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    ICompanyProxy companyProxy;

    @Autowired
    FileService fileService;

    @Override
    public Long createCompany() {
        return companyProxy.createCompany();
    }

    @Override
    public Integer updateCompany(CompanyBo companyBo) {
        Company company = new Company();
        ConversionUtils.conversion(companyBo, company);
        return companyProxy.updateCompany(company);
    }

    @Override
    public CompanyBo findCompanyByCompanyId(Long companyId) {
        Company company = companyProxy.findCompanyByCompanyId(companyId);
        CompanyBo companyBo = getCompanyBo(company);
        return companyBo;
    }

    private CompanyBo getCompanyBo(Company company) {
        if (company == null) {
            return null;
        }
        CompanyBo companyBo = new CompanyBo();
        Long logoImage = company.getCompanyLogoImageFile();
        Long bizlicenseImageFile = company.getCompanyBizlicenseImageFile();
        Long idCardImageFile = company.getCompanyUserIdCardImageFile();
        Long facadeImageFile = company.getCompanyFacadeImageFile();
        if (logoImage != null) {
            AccountFileBo companyImageInfo = fileService.findAccountFileById(logoImage);
            companyBo.setCompanyLogoImageFileDetail(companyImageInfo);
        }
        if (bizlicenseImageFile != null) {
            AccountFileBo companyImageInfo = fileService.findAccountFileById(bizlicenseImageFile);
            companyBo.setCompanyBizlicenseImageFileDetail(companyImageInfo);
        }
        if (idCardImageFile != null) {
            AccountFileBo companyImageInfo = fileService.findAccountFileById(idCardImageFile);
            companyBo.setCompanyUserIdCardImageFileDetail(companyImageInfo);
        }
        if (facadeImageFile != null) {
            AccountFileBo companyImageInfo = fileService.findAccountFileById(facadeImageFile);
            companyBo.setCompanyFacadeImageFileDetail(companyImageInfo);
        }
        ConversionUtils.conversion(company, companyBo);
        return companyBo;
    }

    @Override
    public Pager<CompanyBo> findAllCompanyPager(Integer firstResult, Integer sizeNo) {
        Pager<Company> companyPager = companyProxy.findAllCompanyPager(new Integer[]{CompanyStatus.Active.getCode(), CompanyStatus.Pause.getCode()}, firstResult, sizeNo);
        Pager<CompanyBo> companyBoPager = new Pager<CompanyBo>();
        List<CompanyBo> companyBoList = new ArrayList<CompanyBo>();
        for (Company company : companyPager.getResultList()) {
            CompanyBo companyBo = getCompanyBo(company);
            companyBoList.add(companyBo);
        }
        companyBoPager.setResultList(companyBoList);
        companyBoPager.setTotalSize(companyPager.getTotalSize());
        return companyBoPager;
    }

    @Override
    public Integer updateCompanyBizlicense(Long companyId, Long fileId) {
        return companyProxy.updateCompanyBizlicense(companyId, fileId);
    }

    @Override
    public Integer updateCompanyLogo(Long companyId, Long fileId) {
        return companyProxy.updateCompanyLogo(companyId, fileId);
    }

    @Override
    public Integer updateCompanyUserIdCard(Long companyId, Long fileId) {
        return companyProxy.updateCompanyUserIdCard(companyId, fileId);
    }

    @Override
    public Integer updateCompanyFacade(Long companyId, Long fileId) {
        return companyProxy.updateCompanyFacade(companyId, fileId);
    }
}
