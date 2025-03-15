package com.mayk.TaskComplete.core.validators;

import java.util.List;

public record NotificationError(
        List<String> errors
) {

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
