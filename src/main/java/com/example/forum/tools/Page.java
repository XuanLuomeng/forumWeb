package com.example.forum.tools;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    /**
     * 总文章数
     */
    private int totalCount;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页码
     */
    private int currentPage;
    /**
     * 每页显示条数
     */
    private int pageSize;
    /**
     * 每页显示的数据集合
     */
    private List<T> list;
}
