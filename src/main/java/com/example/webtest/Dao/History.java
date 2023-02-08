package com.example.webtest.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("applyhistory")
public class History {

    @TableId(type= IdType.AUTO)
    private Integer hid;
    private Integer  uid;
    private  Integer gid;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    private Timestamp time;


    public History(Integer uid, Integer gid, Timestamp time) {
        this.uid = uid;
        this.gid = gid;
        this.time = time;
    }
}
