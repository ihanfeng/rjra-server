package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.AccountFile;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IAccountFileProxy extends Serializable {

    public List<AccountFile> findAccountFileByIds(Long[] ids, Integer[] status);

    public AccountFile findAccountFileById(Long fileId);

    public Long saveAccountFile(AccountFile file);
}
