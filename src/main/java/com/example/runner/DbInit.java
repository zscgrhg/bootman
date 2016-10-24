package com.example.runner;

import com.example.domain.Manager;
import com.example.service.inferface.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by THINK on 2016/4/21.
 */
@Component
public class DbInit implements CommandLineRunner {


    @Autowired
    ManagerService managerService;

    @Override
    public void run(String... args) throws Exception {

        try {
            for (int i = 0; i < 10; i++) {
                Manager manager = new Manager();
                manager.setName("first blood "+i);
                manager.setPassword("1234");
                managerService.insert(manager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
