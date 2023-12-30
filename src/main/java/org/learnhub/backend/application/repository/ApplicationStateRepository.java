package org.learnhub.backend.application.repository;

import org.learnhub.backend.application.model.ApplicationState;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationStateRepository extends CrudRepository<ApplicationState, String> {

}
