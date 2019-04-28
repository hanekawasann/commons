package com.hanekawasan.commons.lang.file.parser.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author yukms 763803382@qq.com 2019/4/28 18:56
 */
@Data
public class JavaFileImport implements Serializable {
    private static final long serialVersionUID = 5217775890633182809L;
    /** 类型 */
    private JavaFileImportType type;
    /** 值 */
    private String val;
}
