package com.epsi.blockchainsmokers.workshopi4.exception;

import java.util.HashMap;
import java.util.Map;

public class WorkshopException extends Exception {

    private static final long serialVersionUID = 1L;

    private Map<String, String> messages = new HashMap<>();

    public Map<String, String> getMessages() {
        return messages;
    }

    public void addMessage(String code, String message) {
        messages.put(code, message);
    }

    public boolean mustBeThrown() {
        return !messages.isEmpty();
    }
}
