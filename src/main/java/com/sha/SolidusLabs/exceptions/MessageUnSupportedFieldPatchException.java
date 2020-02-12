package com.sha.SolidusLabs.exceptions;

import java.util.Set;

public class MessageUnSupportedFieldPatchException extends RuntimeException {

    public MessageUnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}
