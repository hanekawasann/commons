package com.hanekawasan.io;

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
 * @author yukms 2019/1/03.
 */
public final class FileTreePrinter {
    private File root;
    private Comparator<File> comparator = FileTreePrinter::filePriority;
    private FilenameFilter fileNameFilter = FileTreePrinter::showAll;
    private BiFunction<Boolean[], File, String> treePrinter = TreePrinter.create();
    private Function<File, String> filePrinter = file -> file.getName() + "\n";
    private PrintStream out = System.out;

    public static FileTreePrinter path(String path) {
        Objects.requireNonNull(path, "path");
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("No such file : " + path);
        }
        return new FileTreePrinter(file);
    }

    public void print() {
        print(new Boolean[] { Boolean.FALSE }, root);
    }

    public FileTreePrinter comparator(Comparator<File> comparator) {
        Objects.requireNonNull(comparator, "comparator");
        this.comparator = comparator;
        return this;
    }

    public FileTreePrinter fileNameFilter(FilenameFilter fileNameFilter) {
        Objects.requireNonNull(fileNameFilter, "fileNameFilter");
        this.fileNameFilter = fileNameFilter;
        return this;
    }

    public FileTreePrinter treePrinter(BiFunction<Boolean[], File, String> treePrinter) {
        Objects.requireNonNull(treePrinter, "treePrinter");
        this.treePrinter = treePrinter;
        return this;
    }

    public FileTreePrinter filePrinter(Function<File, String> filePrinter) {
        Objects.requireNonNull(filePrinter, "filePrinter");
        this.filePrinter = filePrinter;
        return this;
    }

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
