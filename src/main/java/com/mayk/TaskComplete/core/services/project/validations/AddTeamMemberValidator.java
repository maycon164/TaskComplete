package com.mayk.TaskComplete.core.services.project.validations;

import com.mayk.TaskComplete.core.validators.NotificationError;

public interface AddTeamMemberValidator {

    void validate(NotificationError notificationError, ValidateAddTeamMemberDTO validateAddTeamMemberDTO);

}
