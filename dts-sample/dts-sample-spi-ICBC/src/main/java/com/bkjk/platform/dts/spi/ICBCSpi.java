package com.bkjk.platform.dts.spi;

import com.bkjk.platform.dts.domain.APIResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "dts-icbc")
@RequestMapping("/icbc")
public interface ICBCSpi {
    @RequestMapping(method = RequestMethod.GET, value = "/moneyOut/{account}/{amount}")
    @ApiOperation(value = "转出", httpMethod = "GET", response = APIResult.class, notes = "转出")
    APIResult moneyOut(@PathVariable("account") String account,@PathVariable("amount") Double amount);

}