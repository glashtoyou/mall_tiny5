package com.example.webtest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webtest.Dao.User;
import com.example.webtest.Vo.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService  extends IService<User> , UserDetailsService {
    public BCryptPasswordEncoder passwordEncoder();
    public List<UserVo> getListUserVo();
    public int deleteBatchUser();
    public int saveBatchUserVo(List<UserVo> userVoList);
}
