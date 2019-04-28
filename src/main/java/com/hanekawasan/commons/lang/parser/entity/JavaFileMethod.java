package com.hanekawasan.commons.lang.parser.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author yukms 763803382@qq.com 2019/4/28 18:11
 */
@Data
public class JavaFileMethod implements Serializable {
    private static final long serialVersionUID = -2961801399717982856L;
    /** 方法注释 */
    private JavaFileNote note;
    /** 方法注解 */
    private List<String> annotation = new ArrayList<>();
    /** 方法关键字 */
    private List<JavaFileKeyword> keywords = new ArrayList<>();
    /** 返回值 */
    private String returnVal;
    /** 方法名 */
    private String name;
    /** 方法参数 */
    private List<JavaFileParam> params = new ArrayList<>();
    /** 异常 */
    private List<String> exceptions = new ArrayList<>();
}
