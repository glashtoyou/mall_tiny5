package com.example.webtest.Controller;

import com.example.webtest.Dao.Game;
import com.example.webtest.Dao.User;
import com.example.webtest.Vo.AttendVo;

import com.example.webtest.Vo.HistoryVo;
import com.example.webtest.Vo.PasswordVo;
import com.example.webtest.Vo.UserVo;
import com.example.webtest.service.ApplyService;
import com.example.webtest.service.GameService;
import com.example.webtest.service.HistoryService;
import com.example.webtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@RestController
public class Data {
    @Autowired
    ApplyService applyService;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @Autowired
    HistoryService historyService;

    @GetMapping("/user/getAllGames")
    public List<Game> getAllGames(){
        return applyService.getAllGames();
    }

    @PostMapping("/user/updatePassword")
    public Boolean updatePassword(@RequestBody PasswordVo passwordVo, HttpSession session){
        User user=userService.getById((Serializable) session.getAttribute("uid"));
        System.out.println(passwordVo.getOldPassword() +""+passwordVo.getNewPassword());
        if(userService.passwordEncoder().matches(passwordVo.getOldPassword(),user.getPassword())){
               user.setPassword(userService.passwordEncoder().encode(passwordVo.getNewPassword()));
               userService.updateById(user);
               return true;
        }else{
            return false;
        }
    }

    @GetMapping("/user/attendedGamesPro")
    public List<Game> listAttendedGamesPro(HttpSession session){
        return applyService.listAttendedGames((Integer) session.getAttribute("uid"));
    }

    @GetMapping("/user/getGamesBySearch/{search}")
    public List<Game> getGamesBySearch(@PathVariable String search){
        return applyService.getGamesBySearch(search);
    }

    @GetMapping("/user/usernameIsExist/{username}")
    public Boolean usernameIsExist(@PathVariable String username,HttpSession session) {
        return applyService.usernameIsExist(username, (Integer) session.getAttribute("uid"));
    }

    @GetMapping("/user/gnameIsExist/{gname}")
    public Boolean gnameIsExist(@PathVariable String gname){
        return applyService.gnameIsExist(gname);
    }

    @GetMapping("/user/attend/usernameIsCurrentUser/{username}")
    public Boolean usernameIsCurrentUser(@PathVariable String username,HttpSession session){
        return userService.getById((Serializable) session.getAttribute("uid")).getUsername().equals(username);
    }

    @GetMapping("/admin/user_manage/getListUserVo")
    public List<UserVo> getListUserVo(){
        return userService.getListUserVo();
    }

    @PostMapping("/admin/user_manage/receiveListUserVo")
    public void receiveListUserVo(@RequestBody  List<UserVo> userVoList){
        userService.deleteBatchUser();
        //System.out.println(userVoList.toString());
        userService.saveBatchUserVo(userVoList);

    }

    @GetMapping("/admin/game_manage/getListGame")
    public List<Game> getListGame(){
       return  applyService.getAllGames();
    }


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",fallbackPatterns ={"yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm" })
    @PostMapping("/admin/game_manage/receiveListGame")
    public void receiveListGame(@RequestBody List<Game> gameList){
        gameService.removeAllGame();
        gameService.saveOrUpdateBatch(gameList);
    }


    @GetMapping("/admin/history_manage/getListHistoryVo")
    public List<HistoryVo> getListHistoryVo(){
        return historyService.getListHistoryVo();
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",fallbackPatterns ={"yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd'T'HH:mm" })
    @PostMapping("/admin/history_manage/receiveListHistoryVo")
    public void receiveListHistoryVo(@RequestBody List<HistoryVo> historyVos){
        historyService.removeAllHistoryVo();
        historyService.saveListHistoryVo(historyVos);
    }


}
