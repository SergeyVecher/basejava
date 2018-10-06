package com.javawebinar.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainFile {
    public static void printFile(File file, int count) {
        List<File> listPrint = Arrays.asList(Objects.requireNonNull(file.listFiles()));
        listPrint.forEach(x -> {
            for (int i = 0; i <= count; i++) {
                System.out.print("\t");
            }
            if (x.isDirectory()) {
                System.out.println(x.getName());
                printFile(x, count + 1);
            } else {
                System.out.println(x.getName());
            }
        });
    }

    public static void main(String[] args) {
        String filePath = ".\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("D:\\java\\project\\basejava\\src");
        printFile(dir, 0);
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
