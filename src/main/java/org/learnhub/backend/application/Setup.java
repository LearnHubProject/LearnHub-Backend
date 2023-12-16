package org.learnhub.backend.application;

import jakarta.transaction.Transactional;
import org.learnhub.backend.application.model.ApplicationState;
import org.learnhub.backend.application.model.ApplicationStateEnum;
import org.learnhub.backend.application.repository.ApplicationStateRepository;
import org.learnhub.backend.application.service.ApplicationStateService;
import org.learnhub.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
public class Setup implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(Setup.class);
    @Autowired
    ApplicationStateService applicationStateService;

    @Autowired
    UserService userService;

    @Value("${DOMAIN}")
    String emailDomain;

    @Override
    public void run(String... args) {
        ApplicationStateEnum state = applicationStateService.getState();

        if(!state.equals(ApplicationStateEnum.SETUP)){
            logger.atDebug().log("Application is already set up, skipping setup.");
            return;
        }
        logger.atInfo().log("Seems like it's the initial startup of the application. Creating admin account...");

        String adminUsername = "admin@"+emailDomain;
        String adminPassword = UUID.randomUUID().toString();

        userService.createUser(adminUsername, adminPassword, "ADMIN");

        String adminDetails = "\n\n\n" +
                String.format("Admin Login: %s\n", adminUsername) +
                String.format("Admin Password: %s", adminPassword) +
                "\n\n\n";

        logger.atInfo().log(adminDetails);

        applicationStateService.updateState(ApplicationStateEnum.READY);

    }

}
