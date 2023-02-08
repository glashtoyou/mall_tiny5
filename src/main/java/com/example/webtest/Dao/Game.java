package com.example.webtest.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("gameinfo")
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @TableId(type=IdType.AUTO)
    private Integer gid;

    private String gname;

    private String gcontent;

    private String gdesc;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",fallbackPatterns ={"yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm" })
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Timestamp gstart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",fallbackPatterns ={"yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm" })
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    private Timestamp gend;

    public Game(String gname,String gcontent,String gdesc,Timestamp gstart,Timestamp gend){
        this.gname=gname;
        this.gcontent=gcontent;
        this.gdesc=gdesc;
        this.gstart=gstart;
        this.gend=gend;
    }


}
