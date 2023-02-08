package com.example.webtest.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webtest.Dao.Game;
import com.example.webtest.Dao.History;
import com.example.webtest.Dao.User;
import com.example.webtest.Vo.HistoryVo;
import com.example.webtest.mapper.GameMapper;
import com.example.webtest.mapper.HistoryMapper;
import com.example.webtest.service.ApplyService;
import com.example.webtest.service.GameService;
import com.example.webtest.service.HistoryService;
import com.example.webtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History>implements HistoryService {

    @Autowired
    HistoryMapper historyMapper;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @Autowired
    GameMapper gameMapper;

    @Autowired
    ApplyService applyService;

    @Override
    public List<HistoryVo> getListHistoryVo() {
        List<History>histories=historyMapper.selectList(null);
        List<HistoryVo>historyVos=new ArrayList<>();
        for(History history:histories){
            User user=userService.getById(history.getUid()) ;
            String gname=gameService.getById(history.getGid()).getGname();
            HistoryVo historyVo=new HistoryVo(user.getUsername(),gname,user.getUemail(),user.getUphone(),history.getTime());
            historyVos.add(historyVo);
        }
        return historyVos;
    }

    @Override
    public void saveListHistoryVo(List<HistoryVo> historyVos) {
        List<History>histories=new ArrayList<>();
        Integer hid=1;
        for(HistoryVo historyVo:historyVos){
            History history=new History(hid++,applyService.getIdByName(historyVo.getUname()),getGidByGname(historyVo.getGname()),historyVo.getHtime());
            histories.add(history);
        }
        saveOrUpdateBatch(histories);


    }

    public Integer getGidByGname(String gname){
        QueryWrapper<Game> wrapper=new QueryWrapper<>();
        wrapper.eq("gname",gname);
        return gameMapper.selectOne(wrapper).getGid();
    }

    @Override
    public void removeAllHistoryVo() {
        historyMapper.delete(null);
    }
}
