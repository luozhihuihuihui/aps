package com.hithy.es.util;

import com.hithy.es.entity.ApsProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Start implements CommandLineRunner {
    @Autowired
    private EsTools esTools;

    @Override
    public void run(String... args) throws Exception {
        esTools.createNotExist(ApsProject.class);
        System.out.println("end");
    }
}
