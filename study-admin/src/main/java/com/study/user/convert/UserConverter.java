package com.study.user.convert;

import com.study.entity.DeptUserEntity;
import com.study.entity.UserDeptEntity;
import com.study.entity.UserEntity;
import com.study.user.dto.UserDTO;
import com.study.user.vo.DeptUserVo;
import com.study.user.vo.UserDeptVo;
import com.study.user.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConverter {
   UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

   List<UserVo> toConvertList(List<UserEntity> list);

    List<UserEntity> toUserEntityList(List<UserDTO> list);

    List<UserDeptVo> toUserDeptList(List<UserDeptEntity> list);

    List<DeptUserVo> toDeptUserList(List<DeptUserEntity> list);
}
