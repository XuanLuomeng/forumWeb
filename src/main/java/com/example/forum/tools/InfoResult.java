package com.example.forum.tools;


import lombok.Data;

import java.io.Serializable;

/**
 * 用于封装后端给前端返回的结果数据对象
 */
@Data
public class InfoResult implements Serializable {
    /**
     * data:返回的结果数据对象
     * flag:返回结果是否正确
     * errorMsg:发生异常的提示信息
     */
    private Object data;
    private boolean flag;
    private String errorMsg;
}
