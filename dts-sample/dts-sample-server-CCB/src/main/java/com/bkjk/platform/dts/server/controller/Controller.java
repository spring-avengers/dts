package com.bkjk.platform.dts.server.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bkjk.platform.dts.domain.APIResult;
import com.bkjk.platform.dts.server.mapper.CCBAccountMapper;
import com.bkjk.platform.dts.server.entity.CcbAccount;
import com.bkjk.platform.dts.spi.CCBSpi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class Controller implements CCBSpi {
    @Autowired
    CCBAccountMapper ccbAccountMapper;

    @Transactional
    @Override
    public APIResult moneyIn(String account, Double amount) {
        CcbAccount accountDO = ccbAccountMapper.selectOne(Wrappers.<CcbAccount>query().eq("account",account));
        Double currentAmount = accountDO.getAmount();
        accountDO.setAmount(accountDO.getAmount()+amount);
        ccbAccountMapper.updateById(accountDO);
        return APIResult.success(String.format("账户%s操作前余额:%s,操作后余额:%s",account,currentAmount,accountDO.getAmount()),null);
    }

    @Transactional
    @Override
    public APIResult moneyInError(String account, Double amount) {
        CcbAccount accountDO = ccbAccountMapper.selectOne(Wrappers.<CcbAccount>query().eq("account",account));
        accountDO.setAmount(accountDO.getAmount()+amount);
        ccbAccountMapper.updateById(accountDO);
        throw new RuntimeException();
    }
}
