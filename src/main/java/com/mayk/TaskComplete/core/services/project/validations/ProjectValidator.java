package com.mayk.TaskComplete.core.services.project.validations;

import com.mayk.TaskComplete.core.validators.NotificationError;

import java.util.List;

public class ProjectValidator {

    private final List<AddTeamMemberValidator> addTeamMemberValidations;

    public ProjectValidator(List<AddTeamMemberValidator> addTeamMemberValidations) {
        this.addTeamMemberValidations = addTeamMemberValidations;
    }

    public NotificationError validateAddTeamMember(ValidateAddTeamMemberDTO validateAddTeamMemberDTO) {
        NotificationError notificationError =  new NotificationError();

        addTeamMemberValidations.forEach(validator -> validator.validate(notificationError, validateAddTeamMemberDTO));

        return notificationError;
    }

}
