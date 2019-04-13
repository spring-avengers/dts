package com.bkjk.platform.dts.sample.service;

import com.bkjk.platform.dts.domain.APIResult;
import com.bkjk.platform.dts.spi.CCBSpi;
import com.bkjk.platform.dts.spi.ICBCSpi;
import com.bkjk.platfrom.dts.core.client.DtsTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private CCBSpi ccbSpi;
    @Autowired
    private ICBCSpi icbcSpi;
    @DtsTransaction
    public void transferMoneyRollback(String accountFrom, String accountTo, Double amount){
        APIResult icbcResult = icbcSpi.moneyOut(accountFrom,amount);
        assert icbcResult.getResult().getCode() == 200;
        boolean hasErrors = false;
        //throw exception to rollback
        ccbSpi.moneyInError(accountTo, amount);
    }
}
