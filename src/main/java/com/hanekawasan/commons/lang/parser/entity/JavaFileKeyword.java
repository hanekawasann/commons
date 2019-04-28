package com.hanekawasan.commons.lang.parser.entity;

import com.hanekawasan.commons.lang.exception.NoSuchEnumMappingException;
import lombok.Getter;

/**
 * @author yukms 763803382@qq.com 2019/4/28 18:15
 */
@Getter
public enum JavaFileKeyword {
    ABSTRACT("abstract"),
    CLASS("class"),
    DEFAULT("default"),
    ENUM("enum"),
    EXTENDS("extends"),
    FINAL("final"),
    IMPLEMENTS("implements"),
    IMPORT("import"),
    INTERFACE("interface"),
    NATIVE("native"),
    PACKAGE("package"),
    PRIVATE("private"),
    PROTECTED("protected"),
    PUBLIC("public"),
    STATIC("static"),
    SUPER("super"),
    SYNCHRONIZED("synchronized"),
    THIS("this"),
    THROWS("throws"),
    TRANSIENT("transient"),
    VOID("void"),
    VOLATILE("volatile");

    private String code;

    JavaFileKeyword(String code) {
        this.code = code;
    }

    public static JavaFileKeyword of(String code) {
        for (JavaFileKeyword keyword : values()) {
            if (keyword.getCode().equals(code)) {
                return keyword;
            }
        }
        throw new NoSuchEnumMappingException();
    }

}
