package com.example.webtest.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webtest.Dao.Role;
import com.example.webtest.Dao.User;
import com.example.webtest.Dao.UserRole;
import com.example.webtest.Vo.UserVo;
import com.example.webtest.mapper.RoleMapper;
import com.example.webtest.mapper.UserMapper;
import com.example.webtest.mapper.UserRoleMapper;
import com.example.webtest.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User>wrapper=new QueryWrapper<>();
        wrapper.eq("uname",username);
        User user=userMapper.selectOne(wrapper);
        if(user==null) return null;


        QueryWrapper<UserRole>userRoleQueryWrapper=new QueryWrapper<>();
        userRoleQueryWrapper.eq("uid",user.getUid());
        List<UserRole>userRoles=userRoleMapper.selectList(userRoleQueryWrapper);
        List<Integer>roleIds=new ArrayList<>();
        userRoles.forEach(userRole ->roleIds.add(userRole.getRid()) );

        // QueryWrapper<Role>roleQueryWrapper=new QueryWrapper<>();
        //wrapper.eq("");
        List<Role>roles=roleMapper.selectBatchIds(roleIds);

        user.setRoles(roles);
        return user;

    }

    public boolean save(User user) {//未加密
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userMapper.insert(user);
        QueryWrapper<User>wrapper=new QueryWrapper<>();
        wrapper.eq("uname",user.getUsername());
        User user1=userMapper.selectOne(wrapper);
        userRoleMapper.insert(new UserRole(user1.getUid(),2));
        return true;
    }


    public int saveBatchUserVo(List<UserVo> userVoList){//密码已加密
        Integer integer=1,uid=1;
        for(UserVo userVo:userVoList){
            User user=new User(uid++,userVo.getUsername(),userVo.getPassword(),userVo.getSex(),
                    userVo.getEmail(),userVo.getPhone(),null);
            if(userVo.getRole().equals("ADMIN")) userRoleMapper.insert(new UserRole(integer++,1,userVo.getId()));
            userRoleMapper.insert(new UserRole(integer++,2,userVo.getId()));
            userMapper.insert(user);
            //integer++;
        }
        return integer-1;
    }

    public int deleteBatchUser(){//将userinfo,userrole清空
        userRoleMapper.delete(null);
        return userMapper.delete(null)+1;

    }

    public List<UserVo> getListUserVo(){//将User转为UserVo
        List<User>users=userMapper.selectList(null);
        List<UserVo>userVoList=new ArrayList<>();
        for(User user:users){
            QueryWrapper<UserRole>wrapper=new QueryWrapper<>();
            wrapper.eq("uid",user.getUid());
            UserVo userVo=new UserVo(user.getUid(),user.getUsername(),user.getPassword(),user.getUsex(),
                    user.getUemail(),user.getUphone(),ridToRoleName(userRoleMapper.selectList(wrapper)));
            userVoList.add(userVo);
        }
        return userVoList;

    }

    public String ridToRoleName(List<UserRole> rid){
        if(rid.size()>1) return "ADMIN";
        return "USER";
    }



    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
