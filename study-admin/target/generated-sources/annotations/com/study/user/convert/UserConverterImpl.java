package com.study.user.convert;

import com.study.entity.DeptEntity;
import com.study.entity.DeptUserEntity;
import com.study.entity.UserDeptEntity;
import com.study.entity.UserEntity;
import com.study.user.dto.UserDTO;
import com.study.user.vo.DeptUserVo;
import com.study.user.vo.DeptVo;
import com.study.user.vo.UserDeptVo;
import com.study.user.vo.UserVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-19T17:23:02+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public List<UserVo> toConvertList(List<UserEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<UserVo> list1 = new ArrayList<UserVo>( list.size() );
        for ( UserEntity userEntity : list ) {
            list1.add( userEntityToUserVo( userEntity ) );
        }

        return list1;
    }

    @Override
    public List<UserEntity> toUserEntityList(List<UserDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<UserEntity> list1 = new ArrayList<UserEntity>( list.size() );
        for ( UserDTO userDTO : list ) {
            list1.add( userDTOToUserEntity( userDTO ) );
        }

        return list1;
    }

    @Override
    public List<UserDeptVo> toUserDeptList(List<UserDeptEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<UserDeptVo> list1 = new ArrayList<UserDeptVo>( list.size() );
        for ( UserDeptEntity userDeptEntity : list ) {
            list1.add( userDeptEntityToUserDeptVo( userDeptEntity ) );
        }

        return list1;
    }

    @Override
    public List<DeptUserVo> toDeptUserList(List<DeptUserEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<DeptUserVo> list1 = new ArrayList<DeptUserVo>( list.size() );
        for ( DeptUserEntity deptUserEntity : list ) {
            list1.add( deptUserEntityToDeptUserVo( deptUserEntity ) );
        }

        return list1;
    }

    protected UserVo userEntityToUserVo(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserVo userVo = new UserVo();

        userVo.setUser_name( userEntity.getUser_name() );
        userVo.setAge( userEntity.getAge() );
        if ( userEntity.getGender() != null ) {
            userVo.setGender( Integer.parseInt( userEntity.getGender() ) );
        }

        return userVo;
    }

    protected UserEntity userDTOToUserEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUser_name( userDTO.getUser_name() );
        userEntity.setAge( userDTO.getAge() );
        userEntity.setGender( userDTO.getGender() );

        return userEntity;
    }

    protected DeptVo deptEntityToDeptVo(DeptEntity deptEntity) {
        if ( deptEntity == null ) {
            return null;
        }

        DeptVo deptVo = new DeptVo();

        deptVo.setDept_id( deptEntity.getDept_id() );
        deptVo.setDept_name( deptEntity.getDept_name() );

        return deptVo;
    }

    protected UserDeptVo userDeptEntityToUserDeptVo(UserDeptEntity userDeptEntity) {
        if ( userDeptEntity == null ) {
            return null;
        }

        UserDeptVo userDeptVo = new UserDeptVo();

        userDeptVo.setUser_name( userDeptEntity.getUser_name() );
        userDeptVo.setAge( userDeptEntity.getAge() );
        if ( userDeptEntity.getGender() != null ) {
            userDeptVo.setGender( Integer.parseInt( userDeptEntity.getGender() ) );
        }
        userDeptVo.setDeptEntity( deptEntityToDeptVo( userDeptEntity.getDeptEntity() ) );

        return userDeptVo;
    }

    protected DeptUserVo deptUserEntityToDeptUserVo(DeptUserEntity deptUserEntity) {
        if ( deptUserEntity == null ) {
            return null;
        }

        DeptUserVo deptUserVo = new DeptUserVo();

        deptUserVo.setDept_id( deptUserEntity.getDept_id() );
        deptUserVo.setDept_name( deptUserEntity.getDept_name() );
        deptUserVo.setUserList( toConvertList( deptUserEntity.getUserList() ) );

        return deptUserVo;
    }
}
