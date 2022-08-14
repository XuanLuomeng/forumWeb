package com.example.forum.tools;

import lombok.Data;

@Data
public class Article {
    /**
     * 作者
     * 标题
     * 内容
     * 发表日期
     */
    private String author;
    private String theme;
    private String articleText;
    private String time;
}
