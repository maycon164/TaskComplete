package com.mayk.TaskComplete.core.service.project.validation.validators;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.model.builder.ProjectBuilder;
import com.mayk.TaskComplete.core.services.project.validations.ProjectValidationsMessages;
import com.mayk.TaskComplete.core.services.project.validations.ValidateAddTeamMemberDTO;
import com.mayk.TaskComplete.core.services.project.validations.validators.UserIsAlreadyATeamMemberInProject;
import com.mayk.TaskComplete.core.validators.NotificationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserAlreadyTeamMemberValidationTest {

    @Test
    void givenValidateAddTeamMemberDTO_thenAddError() {
        NotificationError notificationError = new NotificationError();

        User user = new User(1L, "teste", "teste@email.com");

        Project project = ProjectBuilder.builder()
                .projectOwner(user)
                .teamMembers(List.of(
                        new User(2L, "joao", "joao@email.com")
                ))
                .build();

        ValidateAddTeamMemberDTO dto = new ValidateAddTeamMemberDTO(
                user,
                project,
                "joao@email.com"
        );

        UserIsAlreadyATeamMemberInProject validator = new UserIsAlreadyATeamMemberInProject();

        validator.validate(notificationError, dto);

        Assertions.assertEquals(ProjectValidationsMessages.USER_ALREADY_PROJECT_TEAM_MEMBER, notificationError.getErrors().get(0));
    }

    @Test
    void givenAddTeamMemberValidationDTO_thenNoError() {
        NotificationError notificationError = new NotificationError();

        User user = new User(1L, "teste", "teste@email.com");

        Project project = ProjectBuilder.builder()
                .projectOwner(user)
                .teamMembers(List.of(
                        new User(2L, "pedrinho", "pedrinho@email.com")
                ))
                .build();

        ValidateAddTeamMemberDTO dto = new ValidateAddTeamMemberDTO(
                user,
                project,
                "joao@email.com"
        );

        UserIsAlreadyATeamMemberInProject validator = new UserIsAlreadyATeamMemberInProject();

        validator.validate(notificationError, dto);

        Assertions.assertFalse(notificationError.hasErrors());

    }
}
