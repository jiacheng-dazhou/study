package com.study.entity;

import lombok.Data;

import java.util.List;

@Data
public class DeptUserEntity extends DeptEntity{
    private List<UserEntity> userList;
}
