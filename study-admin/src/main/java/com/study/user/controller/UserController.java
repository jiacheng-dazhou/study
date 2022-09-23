package com.study.user.controller;

import com.study.annotation.Log;
import com.study.user.dto.UserDTO;
import com.study.user.service.UserService;
import com.study.user.vo.DeptUserVo;
import com.study.user.vo.UserDeptVo;
import com.study.user.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/selectAllUser")
    public List<UserVo> selectAllUser(){
        return userService.selectAllUser();
    }

    @GetMapping("/selectUserById")
    public List<UserVo> selectUserById(Long id){
        return userService.selectUserById(id);
    }

    @PostMapping("/insertUserList")
    public void insertUserList(@RequestBody List<UserDTO> list){
        userService.insertUserList(list);
    }

    @PostMapping("/selectUserDeptList")
    public List<UserDeptVo> selectUserDeptList(@RequestBody List<Long> ids){
        return userService.selectUserDeptList(ids);
    }

    @PostMapping("/selectDeptUserList")
    @Log
    public List<DeptUserVo> selectDeptUserList(@RequestBody List<String> deptIds){
        return userService.selectDeptUserList(deptIds);
    }
}
