package org.learnhub.backend.application;

import org.learnhub.backend.application.model.ApplicationStateEnum;
import org.learnhub.backend.application.service.ApplicationStateService;
import org.learnhub.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class Setup implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(Setup.class);
    @Autowired
    ApplicationStateService applicationStateService;

    @Autowired
    UserService userService;

    @Value("${DOMAIN}")
    String emailDomain;

    @Value("${setup.default-pass}")
    String defaultPassword;

    @Autowired
    Environment environment;

    @Override
    public void run(String... args) {

        System.out.println(Arrays.toString(Arrays.stream(environment.getActiveProfiles()).toArray()));

        ApplicationStateEnum state = applicationStateService.getState();

        if(!state.equals(ApplicationStateEnum.SETUP)){
            logger.atDebug().log("Application is already set up, skipping setup.");
            return;
        }
        logger.atInfo().log("Seems like it's the initial startup of the application. Creating admin account...");

        String adminUsername = "admin@"+emailDomain;
        String adminPassword = defaultPassword;

        userService.createUser(adminUsername, adminPassword, "ADMIN");

        String adminDetails = "\n\n\n" +
                String.format("Admin Login: %s\n", adminUsername) +
                String.format("Admin Password: %s\n", adminPassword) +
                "DON'T FORGET TO CHANGE THE PASSWORD!!!"+
                "\n\n\n";

        logger.atInfo().log(adminDetails);

        applicationStateService.updateState(ApplicationStateEnum.READY);

    }

}
