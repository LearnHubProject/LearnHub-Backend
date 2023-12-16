package org.learnhub.backend.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "application_state")
public class ApplicationState {

    @Id
    String state = "state";

    ApplicationStateEnum status;

    public ApplicationState() {
    }

    public ApplicationState(ApplicationStateEnum status) {
        this.status = status;
    }

    public ApplicationStateEnum getStatus() {
        return status;
    }

    public void setStatus(ApplicationStateEnum status) {
        this.status = status;
    }
}
