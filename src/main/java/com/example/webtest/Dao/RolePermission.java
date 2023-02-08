package com.example.webtest.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("rolepermission")
public class RolePermission {
    @TableId(type= IdType.AUTO)
    private Integer rpid;
    private Integer rid;
    private Integer pid;

    public RolePermission(Integer rid,Integer pid){
        this.rid=rid;
        this.pid=pid;
    }
}
