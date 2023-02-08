package com.example.webtest.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserVo {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private String email;
    private String phone;
    private String role;


}
