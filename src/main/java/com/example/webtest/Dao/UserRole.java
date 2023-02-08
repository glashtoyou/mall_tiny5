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
@TableName("userrole")
public class UserRole {
    @TableId(type= IdType.AUTO)
    private Integer urid;
    private Integer rid;
    private Integer uid;

    public UserRole(Integer uid,Integer rid){
        this.uid=uid;
        this.rid=rid;
    }
}
