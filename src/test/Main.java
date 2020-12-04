package test;

import sys.set.AnsweringData;
import sys.set.BinaryOperationFiles;
import sys.set.RecFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<String>();
        Files.readAllLines(Paths.get("D:/哈哈/软件构造/最终交付/class.txt"), Charset.forName("GBK")).forEach(s -> {
            if (s.contains(("ct.jar")) && !list.contains(s.split("ct_jar")[1])) {
                list.add(s.split("ct.jar")[1]);
            }
        });
        System.out.println(list);
    }
}
