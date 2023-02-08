package com.example.webtest.Dao;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="userinfo")
public class User implements UserDetails {
    @TableId(type=IdType.AUTO)
    //@TableField("uid")
    private Integer uid;

    @TableField("uname")
    private String username;

    @TableField("upassword")
    private String password;

    @TableField("usex")
    private String usex;

    @TableField("uemail")
    private String uemail;

    @TableField("uphone")
    private String uphone;

    @TableField(exist = false)
    private List<Role>roles=new ArrayList<>();

    public  User(String username,String password,String usex,String uemail,String uphone){
        this.username=username;
        this.password=password;
        this.usex=usex;
        this.uemail=uemail;
        this.uphone=uphone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
