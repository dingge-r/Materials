package com.example.materials;

import com.example.materials.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateTest {

    @Test
    public void DateT(){
        String name = "http://39.106.188.22:8081/materials/word/daa656e2-a382-43a7-a257-9ce0be810ffb.doc";
        name = name.substring(name.indexOf("/materials"));
        System.out.println(name);
        /*System.out.println("string: "+DateUtils.dateByString());
        System.out.println("date: " + new Date());*/
    }
}
