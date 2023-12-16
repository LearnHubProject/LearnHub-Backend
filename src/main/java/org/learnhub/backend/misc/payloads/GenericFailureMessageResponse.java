package org.learnhub.backend.misc.payloads;

public class GenericFailureMessageResponse implements ResponsePayload {
    String message;

    public GenericFailureMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
