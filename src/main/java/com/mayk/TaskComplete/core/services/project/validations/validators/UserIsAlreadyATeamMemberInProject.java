package com.mayk.TaskComplete.core.services.project.validations.validators;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.services.project.validations.AddTeamMemberValidator;
import com.mayk.TaskComplete.core.services.project.validations.ProjectValidationsMsg;
import com.mayk.TaskComplete.core.services.project.validations.ValidateAddTeamMemberDTO;
import com.mayk.TaskComplete.core.validators.NotificationError;

public class UserIsAlreadyATeamMemberInProject implements AddTeamMemberValidator {

    @Override
    public void validate(NotificationError notificationError, ValidateAddTeamMemberDTO validateAddTeamMemberDTO) {
        Project project = validateAddTeamMemberDTO.project();
        String email = validateAddTeamMemberDTO.invitedEmail();

        if(project.teamMembers().stream().anyMatch(member -> member.email().equals(email))) {
            notificationError.addError(ProjectValidationsMsg.USER_ALREADY_PROJECT_TEAM_MEMBER);
        }

    }

}
