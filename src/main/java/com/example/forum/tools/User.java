package com.example.forum.tools;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String userid;
    private String password;
    private String username;
    private String email;
    private String sex;
    private String telephone;
    private String regdate;
}
