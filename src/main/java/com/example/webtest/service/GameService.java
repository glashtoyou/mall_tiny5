package com.example.webtest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webtest.Dao.Game;

public interface GameService extends IService<Game> {
    public void removeAllGame();
}
