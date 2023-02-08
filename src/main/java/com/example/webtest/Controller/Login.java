package com.example.webtest.Controller;

/*import com.example.webtest.Dao.User;
import com.example.webtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//receive json information must to using @RestController ,if only  the return information just is a String ,you can
//use Controller
@RestController
public class Login {


    @RequestMapping (value = "/loginPro",method=RequestMethod.POST)
    public void login(String username, String password){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=(User)authentication.getDetails();
        System.out.println("hello world");
        System.out.println(username+"" +password);
        System.out.println(user.getUsername()+"second"+user.getPassword());
        //session.setAttribute("uid",user.getUid());


    }

    /*@PostMapping(value = "/login")
    public String login(@RequestBody User user){
        System.out.println(user.toString());
        List<User>users=new ArrayList<>();
        for(int i=0;i<10;i++){
            User user1=new User();
           // user1.setId(String.valueOf(i));
            user1.setPassword(String.valueOf(i)+"p");
            users.add(user1);
        }
        return "login";
    }






}*/
