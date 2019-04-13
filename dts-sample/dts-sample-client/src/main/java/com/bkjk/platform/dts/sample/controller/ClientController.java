package com.bkjk.platform.dts.sample.controller;

import com.bkjk.platform.dts.domain.APIResult;
import com.bkjk.platform.dts.sample.service.Service;
import com.bkjk.platform.dts.spi.CCBSpi;
import com.bkjk.platform.dts.spi.ICBCSpi;
import com.bkjk.platfrom.dts.core.client.DtsTransaction;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClientController {
    @Autowired
    private CCBSpi ccbSpi;
    @Autowired
    private ICBCSpi icbcSpi;

    @Autowired
    private Service service;
    @DtsTransaction
    @RequestMapping(method = RequestMethod.GET, value = "/transferMoney/{accountFrom}/{accountTo}/{amount}")
    @ApiOperation(value = "ICBC账户转账到CCB账户分布式事务成功案列", httpMethod = "GET", response = APIResult.class, notes = "ICBC账户转账到CCB账户")
    public APIResult transferMoneySucceed(@PathVariable("accountFrom") String accountFrom,@PathVariable("accountTo") String accountTo, @PathVariable("amount") Double amount){
        APIResult icbcResult = icbcSpi.moneyOut(accountFrom,amount);
        APIResult ccbResult = ccbSpi.moneyIn(accountTo,amount);
        return APIResult.success(icbcResult.getResult().getMessage()+ccbResult.getResult().getMessage(),null);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transferMoneyRollback/{accountFrom}/{accountTo}/{amount}")
    @ApiOperation(value = "ICBC账户转账到CCB账户全局回滚案列", httpMethod = "GET", response = APIResult.class, notes = "ICBC账户转账到CCB账户全局回滚案列")
    public APIResult transferMoneyRollback(@PathVariable("accountFrom") String accountFrom,@PathVariable("accountTo") String accountTo, @PathVariable("amount") Double amount){
        try {
            service.transferMoneyRollback(accountFrom, accountTo, amount);
        }catch (Throwable e){
            //ignore
        }
        return APIResult.success("CCB转入失败,ICBC和CCB账户金额没有变化");
    }
}
