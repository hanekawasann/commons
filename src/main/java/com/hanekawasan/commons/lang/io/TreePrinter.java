package com.hanekawasan.commons.lang.io;

import java.io.File;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yukms 2019/1/03.
 */
public final class TreePrinter implements BiFunction<Boolean[], File, String> {
    /** 竖枝干 */
    private String verticalBranch = "|";
    /** 空竖枝干 */
    private String blockVerticalBranch = StringUtils.EMPTY;
    /** 枝干层级 */
    private String branchLevel = "  ";
    /** 横枝干 */
    private String crossBranch = "--";
    /** 节点 */
    private String node = "+";
    /** 最后一个节点 */
    private String lastNode = "\\";

    /**
     * 创建树打印器
     *
     * @return 默认的TreePrinter
     */
    public static TreePrinter create() {
        return new TreePrinter();
    }

    /**
     * 返回枝干
     *
     * @param levels 从树根（root）开始，每一个元素对应树的每一层级。
     *               如果对应层级是最后一个分支，那么值为false，否则值为true
     * @param file   文件
     * @return 枝干
     */
    @Override
    public String apply(Boolean[] levels, File file) {
        StringBuilder sb = new StringBuilder();
        int length = levels.length;
        for (int i = 0; i < length; i++) {
            Boolean aBoolean = levels[i];
            if (i == length - 1) {
                sb.append(aBoolean ? node : lastNode).append(crossBranch);
            } else {
                sb.append(aBoolean ? verticalBranch : blockVerticalBranch).append(branchLevel);
            }
        }
        return sb.toString();
    }

    /**
     * 设置竖枝干
     *
     * @param verticalBranch 竖枝干
     * @return TreePrinter
     */
    public TreePrinter verticalBranch(String verticalBranch) {
        this.verticalBranch = verticalBranch;
        Stream.generate(() -> " ").limit(verticalBranch.length()).forEach(s -> this.blockVerticalBranch += s);
        return this;
    }

    /**
     * 设置枝干层级
     *
     * @param branchLevel 枝干层级
     * @return TreePrinter
     */
    public TreePrinter branchLevel(String branchLevel) {
        this.branchLevel = branchLevel;
        return this;
    }

    /**
     * 设置横枝干
     *
     * @param crossBranch 横枝干
     * @return TreePrinter
     */
    public TreePrinter crossBranch(String crossBranch) {
        this.crossBranch = crossBranch;
        return this;
    }

    /**
     * 设置节点
     *
     * @param node 节点
     * @return TreePrinter
     */
    public TreePrinter node(String node) {
        this.node = node;
        return this;
    }

    /**
     * 设置最后一个节点
     *
     * @param lastNode 最后一个节点
     * @return TreePrinter
     */
    public TreePrinter lastNode(String lastNode) {
        this.lastNode = lastNode;
        return this;
    }

    private TreePrinter() {
    }
}
