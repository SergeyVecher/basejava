package com.javawebinar.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainFile {
    public static void printFile(File file) {
        List<File> listPrint = Arrays.asList(Objects.requireNonNull(file.listFiles()));
        listPrint.forEach(x -> {
            if (x.isDirectory()) {
                printFile(x);
                System.out.println(x.getName());
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

        File dir = new File("C:\\java\\basejava");
        printFile(dir);
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
