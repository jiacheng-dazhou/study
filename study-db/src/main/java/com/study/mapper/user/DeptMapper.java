package com.study.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.entity.DeptEntity;
import com.study.entity.DeptUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapper<DeptEntity> {
    List<DeptUserEntity> selectDeptUserList(@Param("deptIds") List<String> deptIds);
}
