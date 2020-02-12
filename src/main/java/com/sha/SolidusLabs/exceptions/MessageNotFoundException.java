package com.sha.SolidusLabs.exceptions;

public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(Long id) {
        super("Message id not found : " + id);
    }

}
