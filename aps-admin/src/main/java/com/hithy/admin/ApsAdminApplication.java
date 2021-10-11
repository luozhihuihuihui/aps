package com.hithy.admin;

import com.hithy.auth.EnableAuth;
import com.hithy.basedao.EnableDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDao
@EnableAuth
@SpringBootApplication
public class ApsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApsAdminApplication.class, args);
    }

}
