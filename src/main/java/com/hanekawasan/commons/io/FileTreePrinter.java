package com.hanekawasan.commons.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 灵感来源于多本技术书籍的文件目录说明，类名来源于mvn dependency:tree，这也是默认格式，除了默认格式，也支持完全的客制化。
 *
 * @author yukms 2019/1/03.
 */
public final class FileTreePrinter {
    /** 根文件 */
    private File root;
    /** 文件排序策略 */
    private Comparator<File> comparator = FileTreePrinter::filePriority;
    /** 文件过滤策略 */
    private FilenameFilter fileNameFilter = FileTreePrinter::showAll;
    /** 树打印策略 */
    private BiFunction<Boolean[], File, String> treePrinter = TreePrinter.create();
    /** 文件打印策略 */
    private Function<File, String> filePrinter = file -> file.getName() + "\n";
    /** 输出流 */
    private PrintStream out = System.out;

    /**
     * 通过路径创建FileTreePrinter
     *
     * @param path 文件绝对路径
     * @return FileTreePrinter
     */
    public static FileTreePrinter path(String path) {
        Objects.requireNonNull(path, "path");
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("No such file : " + path);
        }
        return new FileTreePrinter(file);
    }

    /**
     * 打印
     */
    public void print() {
        print(new Boolean[] { Boolean.FALSE }, root);
    }

    /**
     * 设置文件排序规则，默认按照文件优先于文件夹排序。
     *
     * @param comparator 不能为null
     * @return FileTreePrinter
     * @see Comparator
     */
    public FileTreePrinter comparator(Comparator<File> comparator) {
        Objects.requireNonNull(comparator, "comparator");
        this.comparator = comparator;
        return this;
    }

    /**
     * 设置文件过滤规则，默认不过滤。
     *
     * @param fileNameFilter 不能为null
     * @return FileTreePrinter
     * @see FilenameFilter
     */
    public FileTreePrinter fileNameFilter(FilenameFilter fileNameFilter) {
        Objects.requireNonNull(fileNameFilter, "fileNameFilter");
        this.fileNameFilter = fileNameFilter;
        return this;
    }

    /**
     * 设置树打印策略。
     *
     * @param treePrinter 不能为null
     * @return FileTreePrinter
     * @see BiFunction
     */
    public FileTreePrinter treePrinter(BiFunction<Boolean[], File, String> treePrinter) {
        Objects.requireNonNull(treePrinter, "treePrinter");
        this.treePrinter = treePrinter;
        return this;
    }

    /**
     * 设置文件打印策略，默认打印文件名。
     *
     * @param filePrinter 不能为null
     * @return FileTreePrinter
     * @see Function
     */
    public FileTreePrinter filePrinter(Function<File, String> filePrinter) {
        Objects.requireNonNull(filePrinter, "filePrinter");
        this.filePrinter = filePrinter;
        return this;
    }

    /**
     * 设置输出流，默认打印输出流。
     *
     * @param out 不能为null
     * @return FileTreePrinter
     */
    public FileTreePrinter out(PrintStream out) {
        Objects.requireNonNull(out, "out");
        this.out = out;
        return this;
    }

    private void print(Boolean[] levels, File file) {
        out.print(treePrinter.apply(levels, file) + filePrinter.apply(file));
        int levelLength = levels.length;
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles(fileNameFilter);
            Arrays.sort(subFiles, comparator);
            Boolean[] newLevel = new Boolean[levelLength + 1];
            System.arraycopy(levels, 0, newLevel, 0, levelLength);
            newLevel[levelLength] = Boolean.TRUE;
            for (int i = 0; i < subFiles.length; i++) {
                if (i == subFiles.length - 1) {
                    newLevel[levelLength] = Boolean.FALSE;
                }
                print(newLevel, subFiles[i]);
            }
        }
    }

    private static int filePriority(File file1, File file2) {
        boolean file1Directory = file1.isFile();
        boolean file2Directory = file2.isFile();
        if (file1Directory == file2Directory) {
            return 0;
        }
        return file1Directory ? -1 : 1;
    }

    private static boolean showAll(File dir, String name) {
        return Boolean.TRUE;
    }

    private FileTreePrinter(File root) {
        this.root = root;
    }
}
