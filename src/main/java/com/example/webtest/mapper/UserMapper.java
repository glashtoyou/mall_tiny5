package com.example.webtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webtest.Dao.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
