package com.example.forum.tools;

import lombok.Data;

@Data
public class Article {
    /**
     * 文章的aid
     * 作者
     * 作者的uid
     * 标题
     * 内容
     * 发表日期
     */
    private int aid;
    private String author;
    private String uid;
    private String theme;
    private String articleText;
    private String time;
}
