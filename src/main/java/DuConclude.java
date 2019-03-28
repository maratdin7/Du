import javafx.util.Pair;

import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.lang.Math.ceil;


class DuConclude {

    private Double osn = 1024.0;

    private Pair<Double, String> h(long size) {
        int i = 0;
        Double sizeInDouble = size * 1.0;
        while (sizeInDouble > osn) {
            i++;
            sizeInDouble = sizeInDouble / osn;
        }
        String base = unit(i);
        return new Pair<>(sizeInDouble, base);
    }

    private String unit(int i) {
        String base = "";
        switch (i) {
            case 0:
                base = "B";
                break;
            case 1:
                base = "KB";
                break;
            case 2:
                base = "MB";
                break;
            case 3:
                base = "GB";
                break;
            case 4:
                base = "TB";
                break;
        }
        return base;
    }

    long c(ArrayList<Path> files) throws IOException {
        long sum = 0;
        for (Path file : files) {
            if (existsFiles(file)) sum += sizeDirectory(file);
            else sum += sizeFile(file);
        }
        return sum;
    }

    void si() {
        osn = 1000.0;
    }

    long size(Path file) throws IOException {
        if (existsFiles(file)) return sizeDirectory(file);
        else return sizeFile(file);
    }

    private long sizeFile(Path file) throws IOException {
        if (existsFiles(file)) System.out.println("This is not file " + name(file));
        return Files.size(file);
    }

    private long sizeDirectory(Path directory) throws IOException {
        if (!existsFiles(directory)) System.out.println("This is not directory " + name(directory));
        FileVisitorTree inDirectory = new FileVisitorTree();
        Files.walkFileTree(directory, inDirectory);
        return inDirectory.sum;
    }

    private boolean existsFiles(Path file) {
        if (!Files.exists(file))
            throw new IllegalArgumentException(name(file));
        return Files.isDirectory(file);
    }

    String name(Path file) {
        return file.getFileName().toString();
    }

    void printer(Bean bean, String fileName, long size) {
        if (bean.h) {
            Pair ans = h(size);
            System.out.println(fileName + " - " + rou((Double) ans.getKey()) + " " + ans.getValue());
        } else {
            System.out.println(fileName + " - " + rou(size / osn));
        }
    }

    private double rou(Double size) {
        return ceil(size * 100) / 100;

    }

    private class FileVisitorTree extends SimpleFileVisitor<Path> {
        long sum = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (!Files.isDirectory(file)) sum += sizeFile(file);
            return FileVisitResult.CONTINUE;
        }
    }
}
