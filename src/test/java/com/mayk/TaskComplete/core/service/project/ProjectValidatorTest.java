package com.mayk.TaskComplete.core.service.project;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.model.builder.ProjectBuilder;
import com.mayk.TaskComplete.core.services.project.validations.AddTeamMemberValidator;
import com.mayk.TaskComplete.core.services.project.validations.ProjectValidator;
import com.mayk.TaskComplete.core.services.project.validations.ValidateAddTeamMemberDTO;

import com.mayk.TaskComplete.core.validators.NotificationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ProjectValidatorTest {

    @Test
    void givenDTO_thenValidateAddTeamMember_thenNoErrors() {
        User user = new User(1L, "name", "teste@email.com");
        Project project = ProjectBuilder.builder()
                .projectOwner(user)
                .build();
        String email = "invited@email.com";

        ValidateAddTeamMemberDTO validateAddTeamMemberDTO = new ValidateAddTeamMemberDTO(
                user,
                project,
                email
        );

        AddTeamMemberValidator validator = Mockito.mock();
        ProjectValidator projectValidator = new ProjectValidator(List.of(validator));

        projectValidator.validateAddTeamMember(validateAddTeamMemberDTO);

        Mockito.verify(validator).validate(Mockito.any(NotificationError.class), Mockito.eq(validateAddTeamMemberDTO));
    }

    @Test
    void givenDTO_thenValidateAddTeamMember_thenReturnError() {
        User user = new User(1L, "name", "teste@email.com");
        Project project = ProjectBuilder.builder()
                .projectOwner(user)
                .build();
        String email = "invited@email.com";

        ValidateAddTeamMemberDTO validateAddTeamMemberDTO = new ValidateAddTeamMemberDTO(
                user,
                project,
                email
        );

        AddTeamMemberValidator validator = (NotificationError notification, ValidateAddTeamMemberDTO dto) -> {
            if("teste".equals("teste")) {
                notification.addError("Teste");
            }
        };

        ProjectValidator projectValidator = new ProjectValidator(List.of(validator));

        NotificationError notificationError = projectValidator.validateAddTeamMember(validateAddTeamMemberDTO);

        Assertions.assertTrue(notificationError.hasErrors());
        Assertions.assertEquals(List.of("Teste"), notificationError.getErrors());
    }

}
