package com.example.webtest.Config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.webtest.Dao.User;
import com.example.webtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    /*@Override
    public void configure(WebSecurity webSecurity)throws Exception{
        //webSecurity.ignoring().antMatchers("/resources/**",);
    }*/



    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 释放静态资源，指定拦截规则，指定自定义的认证和退出页面，csrf配置等
        http.authorizeRequests()
                .antMatchers(
                        "/about","/login/**","/login","/error", "/register"  ,"/user/usernameIsExist/**","/registerPro",                             //排除不需spring security验证的页面
                        "/js/**","/css/**","/jQuery/**","/img/**","/icon/**","/file/**","/resources/**","/static/**").permitAll()
                .antMatchers("/login","loginpage").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginPro")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = null;
                        try {
                            out = resp.getWriter();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        out.write("登录失败");
                        out.flush();
                        out.close();
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        System.out.println(authentication.getName() + " 登录成功");
                        Object obj=authentication.getPrincipal();
                        String jsonString = JSON.toJSONString(obj);
                        User user = JSON.parseObject(jsonString,new TypeReference<User>(){});
                      //  System.out.println(user.toString());
                        request.getSession().setAttribute("uid",user.getUid());

                        //重定向
                        response.sendRedirect("/user/index");
                    }
                })
                .permitAll()
                .and()




                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                       // PrintWriter out = resp.getWriter();
                       // out.write("退出登录");
                        //out.flush();
                        //out.close();
                        req.getSession().invalidate();
                        resp.sendRedirect("/login");


                    }
                })
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable();//跨域;




    }
}
