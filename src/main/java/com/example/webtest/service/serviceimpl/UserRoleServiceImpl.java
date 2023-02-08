package com.example.webtest.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webtest.Dao.UserRole;
import com.example.webtest.mapper.UserRoleMapper;
import com.example.webtest.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>implements UserRoleService {
}
