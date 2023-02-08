package com.example.webtest.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webtest.Dao.Permission;
import com.example.webtest.mapper.PermissionMapper;
import com.example.webtest.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>implements PermissionService {
}
