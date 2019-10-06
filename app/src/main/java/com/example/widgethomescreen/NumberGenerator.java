package com.example.widgethomescreen;

import java.util.Random;

public class NumberGenerator {

    // ketika membuat suatu method static maka tidak perlu menginialisasi class terlebih dahulu
    public static int Generate(int max){
        Random random= new Random();
        return random.nextInt(max);
    }
}
