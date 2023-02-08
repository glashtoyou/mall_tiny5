package com.example.webtest.service;

import com.example.webtest.Dao.Game;
import com.example.webtest.Dao.User;

import java.util.List;

public interface ApplyService {

    public Integer getIdByName(String username);

    public List<User> getAllUsers();

    public List<Game> getAllGames();

    public List<User> getUsersByGame(Integer gid);//game id contest

    public List<Game> getGamesBySearch(String search);//search content compare

    public boolean  inMatch(Integer uid,Integer gid);//enter the competition

    public Game getGameById(Integer gid);

    public List<Game> listAttendedGames(Integer uid);

    public Boolean usernameIsExist(String username,Integer uid);

    public Boolean gnameIsExist(String gname);

    public Boolean attendGameByUsernameAndActivity(String username,String gname);

}
