package org.learnhub.backend.application.repository;

import org.learnhub.backend.application.model.ApplicationState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationStateRepository extends CrudRepository<ApplicationState, String> {

}
