package com.example.webtest.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("role")
public class Role implements GrantedAuthority {
    @TableId(type= IdType.AUTO)
    private Integer rid;
    private String rname;
    private String rdesc;

    public Role(String rname,String rdesc){
        this.rname=rname;
        this.rdesc=rdesc;
    }

    @Override
    public String getAuthority() {
        return rname;
    }
}
