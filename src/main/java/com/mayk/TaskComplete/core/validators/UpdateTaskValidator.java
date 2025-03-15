package com.mayk.TaskComplete.core.validators;

import java.util.ArrayList;
import java.util.List;

public class UpdateTaskValidator {

    public NotificationError validate(UpdateTaskValidatorDTO taskValidatorDTO) {
        List<String> errors = new ArrayList<>();

        if(taskValidatorDTO.task() == null) {
            errors.add("Task does not exist");
        }else {
            if(taskValidatorDTO.task().assignTo() == null) {
                errors.add("Task has no user assigned");
            }

            if(taskValidatorDTO.task().taskStatus().equals(taskValidatorDTO.taskStatus())) {
                errors.add("Update tasks to same status has no effect");
            }
        }

        return new NotificationError(errors);
    }



}
