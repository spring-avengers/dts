package com.bkjk.platform.dts.server.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bkjk.platform.dts.domain.APIResult;
import com.bkjk.platform.dts.server.entity.IcbcAccount;
import com.bkjk.platform.dts.server.mapper.IcbcAccountMapper;
import com.bkjk.platform.dts.spi.ICBCSpi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class Controller implements ICBCSpi {
    @Autowired
    IcbcAccountMapper icbcAccountMapper;

    @Transactional
    @Override
    public APIResult moneyOut( String account,Double amount) {
        IcbcAccount accountDO = icbcAccountMapper.selectOne(Wrappers.<IcbcAccount>query().eq("account",account));
        Double currentAmount = accountDO.getAmount();
        accountDO.setAmount(accountDO.getAmount()-amount);
        icbcAccountMapper.updateById(accountDO);
        return APIResult.success(String.format("账户%s操作前余额:%s,操作后余额:%s",account,currentAmount,accountDO.getAmount()),null);
    }
}
