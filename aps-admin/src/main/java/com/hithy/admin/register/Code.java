package com.hithy.admin.register;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Code {


    /**
     * 生成验证码
     * @return
     */
    public String generate() {
        Random random = new Random();
        StringBuilder stringBuilder=new StringBuilder();
        for (int p=0;p<6;p++){
            stringBuilder.append(random.nextInt(10));
        }
        String code = stringBuilder.toString();
        return code;
    }
}
