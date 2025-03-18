package com.mayk.TaskComplete.core.validators.shared;

import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.services.project.validations.AddTeamMemberValidator;
import com.mayk.TaskComplete.core.services.project.validations.ValidateAddTeamMemberDTO;
import com.mayk.TaskComplete.core.validators.NotificationError;

import java.util.List;

public class UserIsTeamMemberOfProject implements AddTeamMemberValidator {

    @Override
    public void validate(NotificationError notificationError, ValidateAddTeamMemberDTO validateAddTeamMemberDTO) {
        validate(notificationError, validateAddTeamMemberDTO.project().teamMembers(), validateAddTeamMemberDTO.projectOwner());
    }

    private void validate(NotificationError notificationError, List<User> teamMembers, User user) {
        if(teamMembers.stream().noneMatch(member -> member.equals(user))) {
            notificationError.addError(SharedErroMessages.USER_IS_NOT_A_TEAM_MEMBER);
        }

    }
}
