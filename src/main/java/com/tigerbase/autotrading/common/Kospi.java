package com.tigerbase.autotrading.common;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;


@Component
public class Kospi {
    public List<String[]> kospiCode ;

    public Kospi() throws IOException {
        kospiCode= new ArrayList<>();
        InputStream inputStream = new ClassPathResource("/static/info/kospi_code.txt").getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line="";

        for (int i=1; (line = bufferedReader.readLine())!=null; i++){
            this.kospiCode.add(line.split("  "));

        }

    }
}
