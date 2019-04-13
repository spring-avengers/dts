package com.bkjk.platform.dts.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "icbc_account")
@Data
public class IcbcAccount {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String account;

    private Double amount;
}
