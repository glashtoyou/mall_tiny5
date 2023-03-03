package com.example.webtest.Controller;

import com.example.webtest.Dao.Game;
import com.example.webtest.Dao.User;

import com.example.webtest.Vo.AttendVo;
import com.example.webtest.service.ApplyService;
import com.example.webtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.PanelUI;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.Serializable;
import java.util.List;


@Controller
public class Redirect {

   /* @GetMapping("/redirect/**")
    public String redirect(HttpSession session){

        Object id=session.getAttribute("uid");
        String jsonString = JSON.toJSONString(id);
        Integer uid = JSON.parseObject(jsonString,new TypeReference<Integer>(){});
        System.out.println(uid.toString()+"second");
        return "user -info";
    }*/
    @Autowired
    ApplyService applyService;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user/index")
    public String index(HttpSession session){
        Object id=session.getAttribute("uid");
        Boolean isManagement=false;
        session.setAttribute("isManagement",isManagement);
        String jsonString = JSON.toJSONString(id);
        Integer uid = JSON.parseObject(jsonString,new TypeReference<Integer>(){});
       // System.out.println(uid.toString());
        return "index";
    }

    @GetMapping("/user/userinfo")
    public  String  showUser(HttpSession session, Model model){
        User user=userService.getById((Serializable) session.getAttribute("uid"));
        model.addAttribute("user",user);
        return "user-info";

    }



    @GetMapping("/admin/index")
    public String adminIndex(){
        return "tab";
    }



    @GetMapping("/user/article/{gid}")
    public String getArticleById(@PathVariable Integer gid, Model model,HttpSession session){
        model.addAttribute("game",applyService.getGameById(gid));
        if(session.getAttribute("gid")!=null) session.setAttribute("gid",null);
        session.setAttribute("gid",gid);
       // System.out.println(applyService.getGameById(gid).toString());
        return "article";
    }

    @GetMapping("/user/updateUser")
    public String updateUser(){
        return "userInfoUpdate";
    }

    @PostMapping("/user/updateUser")
    public String updateUserinfo( User user,HttpSession session){
        User user1=userService.getById((Serializable) session.getAttribute("uid"));
        user1.setUsername(user.getUsername());
        user1.setUsex(user.getUsex());
        user1.setUemail(user.getUemail());
        user1.setUphone(user.getUphone());
        System.out.println(user.toString());
        System.out.println(user1.toString());
        userService.updateById(user1);
        return "user-info";

    }

    @GetMapping("/user/attendedGames")
    public String listAttendedGames(){
        return "attendedGames";
    }

    @GetMapping("/user/attend")
    public String toAttend(){
        return "attend";
    }


    @PostMapping("/user/attendPro")
    public String AttendPro(AttendVo attendVo){
        applyService.attendGameByUsernameAndActivity(attendVo.getUsername(),attendVo.getActivity());
        return "redirect:/user/index";

    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/registerPro")
    public String registerPro(User user){
        System.out.println(user.toString());
        userService.save(user);
        return "redirect:/login";

    }

    @GetMapping("/admin/user_manage")
    public String userManage(HttpSession session){
        Boolean isManagement=true;
        session.setAttribute("isManagement",isManagement);
        return "user-manage";
    }

    @GetMapping("/admin/game_manage")
    public String gameManage(){return "game-manage";}

    @GetMapping("/admin/history_manage")
    public String historyManage(HttpSession session){
        Boolean isManagement=true;
        session.setAttribute("isManagement",isManagement);
        return "history-manage";}
    //public <T> T getData(String key, TypeReference<T> typeReference) {
     //   Object data = get(key);	//默认是map
     //   String jsonString = JSON.toJSONString(data);
     //   T t = JSON.parseObject(jsonString, typeReference);
     //   return t;
    //}



}
