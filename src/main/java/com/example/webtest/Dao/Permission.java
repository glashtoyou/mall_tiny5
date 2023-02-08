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
@TableName("permission")
public class Permission {
    @TableId(type= IdType.AUTO)
    private Integer pid;
    private String pname;
    private String paddress;

    public Permission(String pname,String paddress){
        this.pname=pname;
        this.paddress=paddress;
    }

}
