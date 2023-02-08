package com.example.webtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webtest.Dao.Game;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameMapper extends BaseMapper<Game> {
}
