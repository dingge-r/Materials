package com.example.materials;

import com.example.materials.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateTest {

    @Test
    public void DateT(){
        System.out.println("string: "+DateUtils.dateByString());
        System.out.println("date: " + new Date());
    }
}
