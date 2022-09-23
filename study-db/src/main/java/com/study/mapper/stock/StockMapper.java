package com.study.mapper.stock;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.entity.StockEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StockMapper extends BaseMapper<StockEntity> {
    StockEntity selectByCondition(@Param("product_code") String product_code);
}
