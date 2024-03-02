package org.learnhub.backend.misc;

public class Result<T> {
    T result;
    String errorMessage;

    public void setResult(T result) {
        this.result = result;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccessful(){
        return (result != null);
    }
}
