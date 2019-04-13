package com.bkjk.platform.dts.spi;

import com.bkjk.platform.dts.domain.APIResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "dts-ccb")
@RequestMapping("/ccb")
public interface CCBSpi {
    @RequestMapping(method = RequestMethod.GET, value = "/moneyIn/{account}/{amount}")
    @ApiOperation(value = "转入", httpMethod = "GET", response = APIResult.class, notes = "转入")
    APIResult moneyIn(@PathVariable("account") String account, @PathVariable("amount") Double amount);


    @RequestMapping(method = RequestMethod.GET, value = "/moneyInError/{account}/{amount}")
    @ApiOperation(value = "转入出错", httpMethod = "GET", response = APIResult.class, notes = "转入出错")
    APIResult moneyInError(@PathVariable("account") String account, @PathVariable("amount") Double amount);
}