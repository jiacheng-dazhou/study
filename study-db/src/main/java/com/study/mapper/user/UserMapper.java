package com.study.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.entity.UserDeptEntity;
import com.study.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    void insertUserList(@Param("userList") List<UserEntity> userList);

    void insertUser(@Param("userEntity") UserEntity userEntity);

    List<UserDeptEntity> selectUserDeptList(@Param("idList") List<Long> idList);
}
