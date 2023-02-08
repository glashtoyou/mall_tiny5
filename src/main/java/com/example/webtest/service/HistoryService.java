package com.example.webtest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webtest.Dao.History;
import com.example.webtest.Vo.HistoryVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HistoryService extends IService<History> {

    public List<HistoryVo> getListHistoryVo();


    public void saveListHistoryVo(List<HistoryVo> historyVos);

    public void removeAllHistoryVo();
}
