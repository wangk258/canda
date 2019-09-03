package com.canda.netty.g7.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * @author Wangkun
 * @since 2019/4/22 3:36 PM
 */
public class Ddd {

    public static void main(String[] args) throws IOException {
        List<String> imeis =
            Files.readAllLines(Paths.get("/Users/canda/Downloads/imei/imei.txt"), StandardCharsets.UTF_8);
        Lists.partition(imeis, 5).forEach(list -> {
            System.out.println(Joiner.on(",").join(list.stream().map(i -> i.trim()).collect(Collectors.toList())));
        });
    }

}
