package com.hanekawasan.commons.lang.file.parser.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author yukms 763803382@qq.com 2019/4/28 18:15
 */
@Data
public class JavaFileField implements Serializable {
    private static final long serialVersionUID = 4562568412571769106L;
    /** 注释 */
    private JavaFileNote statement;
    /** 关键字 */
    private List<JavaFileKeyword> keywords = new ArrayList<>();
    /** 字段类型 */
    private String clazz;
    /** 字段名 */
    private String name;
    /** 参数值 */
    private String val;
}
