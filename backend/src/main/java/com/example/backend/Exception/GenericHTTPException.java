package com.example.backend.Exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class GenericHTTPException extends AbstractThrowableProblem {
    private static final long serialVersionUID = 1L;

    public GenericHTTPException(Status status) {
        this(status.getReasonPhrase(), status.getReasonPhrase(), status);
    }

    public GenericHTTPException(String message, Status status) {
        this(status.getReasonPhrase(), message, status);
    }

    public GenericHTTPException(String title, String message, Status status) {
        super(URI.create("/problem-with-message"), title, status, null, null, null, getAlertParameters(message));
    }

    private static Map<String, Object> getAlertParameters(String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", errorKey);
        return parameters;
    }
}
