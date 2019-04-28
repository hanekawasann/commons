package com.hanekawasan.commons.lang.file.parser.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author yukms 763803382@qq.com 2019/4/28 17:52
 */
@Data
public class JavaFile implements Serializable {
    private static final long serialVersionUID = -7690451527593213598L;
    /** 声明 */
    private JavaFileNote statement;
    /** package */
    private String pkg;
    /** import */
    private List<JavaFileImport> im = new ArrayList<>();
    /** 类注释 */
    private JavaFileNote note;
    /** 类注解 */
    private List<String> annotation = new ArrayList<>();
    /** 类关键字 */
    private List<JavaFileKeyword> keywords = new ArrayList<>();
    /** 类名 */
    private String name;
    /** 字段 */
    private List<JavaFileField> fields = new ArrayList<>();
    /** 方法 */
    private List<JavaFileMethod> methods = new ArrayList<>();
}
