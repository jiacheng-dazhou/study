package com.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dept")
public class DeptEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField(value ="dept_id")
    private String dept_id;
    @TableField(value = "dept_name")
    private String dept_name;
}
