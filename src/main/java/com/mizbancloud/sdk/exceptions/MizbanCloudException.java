package com.mizbancloud.sdk.exceptions;

import java.util.List;
import java.util.Map;

/**
 * Exception thrown for MizbanCloud API errors.
 */
public class MizbanCloudException extends Exception {
    private final int statusCode;
    private final Map<String, String> fields;
    private final List<String> invalidFields;
    private final List<String> missingFields;
    private final Object response;

    public MizbanCloudException(String message) {
        this(message, 0, null, null, null, null);
    }

    public MizbanCloudException(String message, int statusCode) {
        this(message, statusCode, null, null, null, null);
    }

    public MizbanCloudException(String message, int statusCode, Map<String, String> fields,
                                 List<String> invalidFields, List<String> missingFields, Object response) {
        super(message);
        this.statusCode = statusCode;
        this.fields = fields;
        this.invalidFields = invalidFields;
        this.missingFields = missingFields;
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public List<String> getInvalidFields() {
        return invalidFields;
    }

    public List<String> getMissingFields() {
        return missingFields;
    }

    public Object getResponse() {
        return response;
    }
}
