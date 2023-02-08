package com.example.webtest.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webtest.Dao.Game;
import com.example.webtest.Dao.History;
import com.example.webtest.Dao.User;
import com.example.webtest.mapper.GameMapper;
import com.example.webtest.mapper.HistoryMapper;
import com.example.webtest.mapper.UserMapper;
import com.example.webtest.service.ApplyService;
import com.example.webtest.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    GameMapper gameMapper;

    @Autowired
    HistoryMapper historyMapper;

    @Autowired
    UserService userService;

    @Override
    public Integer getIdByName(String username) {
        QueryWrapper<User>wrapper=new QueryWrapper<>();
        wrapper.eq("uname",username);
        User user=userMapper.selectOne(wrapper);
        if(user==null)
        return null;
        return user.getUid();
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null)==null?null:userMapper.selectList(null);
    }

    @Override
    public List<Game> getAllGames() {
        return gameMapper.selectList(null)==null?null:gameMapper.selectList(null);
    }

    @Override
    public List<User> getUsersByGame(Integer gid) {
        QueryWrapper<History>wrapper=new QueryWrapper<>();
        wrapper.eq("gid",gid);
        List<History>list=historyMapper.selectList(wrapper);
        List<Integer>Ids=new ArrayList<>();
        list.forEach(history -> Ids.add(history.getUid()));
        return userMapper.selectBatchIds(Ids);

    }

    @Override
    public List<Game> getGamesBySearch(String search) {
        QueryWrapper<Game>wrapper=new QueryWrapper<>();
        wrapper.like("gname",search);
        return gameMapper.selectList(wrapper);
    }

    @Override
    public boolean inMatch(Integer uid, Integer gid) {
       try{
           historyMapper.insert(new History(uid,gid,new Timestamp(System.currentTimeMillis())));
       }catch (Exception exception){
           return false;
       }
       return true;
    }

    @Override
    public Game getGameById(Integer gid) {
        return gameMapper.selectById(gid);
    }

    @Override
    public List<Game> listAttendedGames(Integer uid) {
        QueryWrapper<History>wrapper=new QueryWrapper<>();
        wrapper.eq("uid",uid);
        List<History>list=historyMapper.selectList(wrapper);
        List<Integer>integerList=new ArrayList<>();
        list.forEach(history -> integerList.add(history.getGid()));
        if(integerList.isEmpty()){ return null;}
        return gameMapper.selectBatchIds(integerList);

    }

    @Override
    public Boolean usernameIsExist(String username, Integer uid) {
        QueryWrapper<User>wrapper=new QueryWrapper<>();
        wrapper.eq("uname",username);
        User user=userMapper.selectOne(wrapper);//尝试获取是否有该用户名
        if(uid==null){
            //没有登录，用户名不为空
            return user != null;//用户不存在
        }////没有登录用户

       User user1=userService.getById(uid);//登录用户
        if(user==null){
            return false;
        }
        else return !user.getUsername().equals(user1.getUsername());//用户数据是当前登录用户

    }

    @Override
    public Boolean gnameIsExist(String gname) {
        QueryWrapper<Game>wrapper=new QueryWrapper<>();
        wrapper.eq("gname",gname);
        return gameMapper.exists(wrapper);

    }

    @Override
    public Boolean attendGameByUsernameAndActivity(String username, String gname) {
        QueryWrapper<User>wrapper=new QueryWrapper<>();
        wrapper.eq("uname",username);
        Integer uid=userMapper.selectOne(wrapper).getUid();
        QueryWrapper<Game>wrapper1=new QueryWrapper<>();
        wrapper1.eq("gname",gname);
        Integer gid=gameMapper.selectOne(wrapper1).getGid();
        return inMatch(uid,gid);
    }


}
