package com.mayk.TaskComplete.core.services.task.validations;

import com.mayk.TaskComplete.core.services.task.validations.dto.AddNewTaskValidatorDTO;
import com.mayk.TaskComplete.core.services.task.validations.dto.UpdateTaskValidatorDTO;
import com.mayk.TaskComplete.core.validators.NotificationError;

public class TaskValidator {

    public NotificationError validateAddNewTask(AddNewTaskValidatorDTO dto) {
        return new NotificationError();
    }

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
