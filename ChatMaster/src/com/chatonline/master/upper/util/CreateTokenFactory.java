package com.chatonline.master.upper.util;

import java.util.Random;

public class CreateTokenFactory {

    private CreateTokenFactory(){

    }

    private static final int length = 16;

    public static String getRandomString(){

        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0; i<length; ++i){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
