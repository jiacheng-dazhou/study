package com.study.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.entity.DeptUserEntity;
import com.study.entity.UserDeptEntity;
import com.study.entity.UserEntity;
import com.study.mapper.user.DeptMapper;
import com.study.mapper.user.UserMapper;
import com.study.user.convert.UserConverter;
import com.study.user.dto.UserDTO;
import com.study.user.service.UserService;
import com.study.user.vo.DeptUserVo;
import com.study.user.vo.UserDeptVo;
import com.study.user.vo.UserVo;
import com.study.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserConverter userConverter;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private DeptMapper deptMapper;
    @Override
    public List<UserVo> selectAllUser() {
        List<UserEntity> userList = userMapper.selectList(new LambdaQueryWrapper<UserEntity>());
        return userConverter.toConvertList(userList);
    }

    @Override
    public List<UserVo> selectUserById(Long id) {
        List<UserEntity> userList = userMapper.selectList(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getId,id));
        return userConverter.toConvertList(userList);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
        public void insertUserList(List<UserDTO> list) {
            List<UserEntity> userList = userConverter.toUserEntityList(list);
        if (!CollectionUtils.isEmpty(userList)) {
            userMapper.insertUser(userList.get(0));
            userMapper.insertUserList(userList);
        }
    }

    @Override
    public List<UserDeptVo> selectUserDeptList(List<Long> ids) {
        List<UserDeptEntity> list = userMapper.selectUserDeptList(ids);
        return userConverter.toUserDeptList(list);
    }

    @Override
    public List<DeptUserVo> selectDeptUserList(List<String> deptIds) {
        List<DeptUserEntity> list = deptMapper.selectDeptUserList(deptIds);
        return userConverter.toDeptUserList(list);
    }
}
