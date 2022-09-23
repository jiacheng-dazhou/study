package com.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stock")
public class StockEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField(value = "product_code")
    private String product_code;
    @TableField(value = "warehouse")
    private String warehouse;
    @TableField(value = "count")
    private Integer count;
    @TableField(value = "version")
    private Integer version;
}
