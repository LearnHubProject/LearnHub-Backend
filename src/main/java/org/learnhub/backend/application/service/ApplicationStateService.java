package org.learnhub.backend.application.service;

import jakarta.transaction.Transactional;
import org.learnhub.backend.application.model.ApplicationState;
import org.learnhub.backend.application.model.ApplicationStateEnum;
import org.learnhub.backend.application.repository.ApplicationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class ApplicationStateService {

    @Autowired
    ApplicationStateRepository applicationStateRepository;

    public void updateState(ApplicationStateEnum state){
        ApplicationState applicationState = applicationStateRepository.findById("state").orElse(new ApplicationState(ApplicationStateEnum.SETUP));
        applicationState.setStatus(state);
        applicationStateRepository.save(applicationState);
    }

    public ApplicationStateEnum getState(){
        return applicationStateRepository.findById("state").get().getStatus();
    }

}
