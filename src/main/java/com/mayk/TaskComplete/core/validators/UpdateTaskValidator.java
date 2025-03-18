package com.mayk.TaskComplete.core.validators;

public class UpdateTaskValidator {

    public NotificationError validate(UpdateTaskValidatorDTO taskValidatorDTO) {
        NotificationError notificationError = new NotificationError();

        if(taskValidatorDTO.task() == null) {
            notificationError.addError("Task does not exist");
        }else {
            if(taskValidatorDTO.task().assignTo() == null) {
                notificationError.addError("Task has no user assigned");
            }

            if(taskValidatorDTO.task().taskStatus().equals(taskValidatorDTO.taskStatus())) {
                notificationError.addError("Update tasks to same status has no effect");
            }
        }

        return notificationError;
    }

}
