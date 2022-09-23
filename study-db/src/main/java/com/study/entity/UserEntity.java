package com.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("user")
public class UserEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField(value = "user_name")
    private String user_name;
    @TableField(value = "age")
    private Integer age;
    @TableField(value = "gender")
    private String gender;
}
