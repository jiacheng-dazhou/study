package com.study.entity;

import lombok.Data;

@Data
public class UserDeptEntity extends UserEntity{
    private DeptEntity deptEntity;
}
