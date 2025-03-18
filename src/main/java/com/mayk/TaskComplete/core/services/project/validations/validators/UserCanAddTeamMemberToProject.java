package com.mayk.TaskComplete.core.services.project.validations.validators;

import com.mayk.TaskComplete.core.services.project.validations.AddTeamMemberValidator;
import com.mayk.TaskComplete.core.services.project.validations.ProjectValidationsMsg;
import com.mayk.TaskComplete.core.services.project.validations.ValidateAddTeamMemberDTO;
import com.mayk.TaskComplete.core.validators.NotificationError;

public class UserCanAddTeamMemberToProject implements AddTeamMemberValidator {

    @Override
    public void validate(NotificationError notificationError, ValidateAddTeamMemberDTO validateAddTeamMemberDTO) {

        if(!validateAddTeamMemberDTO.projectOwner().equals(validateAddTeamMemberDTO.project().projectOwner())) {
            notificationError.addError(ProjectValidationsMsg.USER_IS_NOT_THE_OWNER_OF_PROJECT);
        }

    }

}
