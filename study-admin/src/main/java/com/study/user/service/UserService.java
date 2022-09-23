package com.study.user.service;

import com.study.user.dto.UserDTO;
import com.study.user.vo.DeptUserVo;
import com.study.user.vo.UserDeptVo;
import com.study.user.vo.UserVo;

import java.util.List;

public interface UserService {
    List<UserVo> selectAllUser();

    List<UserVo> selectUserById(Long id);

    void insertUserList(List<UserDTO> list);

    List<UserDeptVo> selectUserDeptList(List<Long> ids);

    List<DeptUserVo> selectDeptUserList(List<String> deptIds);
}
