package com.study.user.vo;

import lombok.Data;

import java.util.List;

@Data
public class DeptUserVo extends DeptVo{
    private List<UserVo> userList;
}
