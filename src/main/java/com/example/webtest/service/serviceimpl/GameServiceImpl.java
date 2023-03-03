package com.example.webtest.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webtest.Dao.Game;
import com.example.webtest.mapper.GameMapper;
import com.example.webtest.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService{

    @Autowired
    GameMapper gameMapper;
    @Override
    public void removeAllGame() {
        gameMapper.delete(null);
    }
}
