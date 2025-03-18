package com.mayk.TaskComplete.core.service.project.validation.validators;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.model.builder.ProjectBuilder;
import com.mayk.TaskComplete.core.services.project.validations.ProjectValidationsMsg;
import com.mayk.TaskComplete.core.services.project.validations.ValidateAddTeamMemberDTO;
import com.mayk.TaskComplete.core.services.project.validations.validators.UserCanAddTeamMemberToProject;
import com.mayk.TaskComplete.core.validators.NotificationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserCanAddTeamMemberValidatorTest {

    @Test
    void givenValidateAddTeamMemberDTO_thenAddError() {
        NotificationError notificationError = new NotificationError();

        User user = new User(1L, "teste", "teste@email.com");

        Project project = ProjectBuilder.builder()
                .projectOwner(new User(2L, "joao", "joao@email.com"))
                .build();

        String email = "pedrinho@email.com";

        ValidateAddTeamMemberDTO dto = new ValidateAddTeamMemberDTO(
                user,
                project,
                email
        );

        UserCanAddTeamMemberToProject validator = new UserCanAddTeamMemberToProject();

        validator.validate(notificationError, dto);

        Assertions.assertTrue(notificationError.hasErrors());
        Assertions.assertEquals(ProjectValidationsMsg.USER_IS_NOT_THE_OWNER_OF_PROJECT, notificationError.getErrors().get(0));
    }

    @Test
    void givenValidateAddTeamMemberDTO_thenNoError() {
        NotificationError notificationError = new NotificationError();
        User user = new User(1L, "teste", "teste@email.com");

        Project project = ProjectBuilder.builder()
                .projectOwner(user)
                .build();

        String email = "pedrinho@email.com";

        ValidateAddTeamMemberDTO dto = new ValidateAddTeamMemberDTO(
                user,
                project,
                email
        );

        UserCanAddTeamMemberToProject validator = new UserCanAddTeamMemberToProject();

        validator.validate(notificationError, dto);

        Assertions.assertFalse(notificationError.hasErrors());
    }

}
