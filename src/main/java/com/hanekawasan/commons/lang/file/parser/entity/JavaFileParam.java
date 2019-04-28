package com.hanekawasan.commons.lang.file.parser.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author yukms 763803382@qq.com 2019/4/28 18:45
 */
@Data
public class JavaFileParam implements Serializable {
    private static final long serialVersionUID = 6741544773249660863L;
    /** 参数注解 */
    private List<String> annotation = new ArrayList<>();
    /** 参数类型 */
    private String clazz;
    /** 参数名 */
    private String name;
}
