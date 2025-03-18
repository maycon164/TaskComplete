package com.mayk.TaskComplete.core.service;

import com.mayk.TaskComplete.core.dto.SendInvitationToProjectDTO;
import com.mayk.TaskComplete.core.exception.AddTeamMemberException;
import com.mayk.TaskComplete.core.exception.ProjectNotFoundException;
import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.model.builder.ProjectBuilder;
import com.mayk.TaskComplete.core.ports.EmailServicePort;
import com.mayk.TaskComplete.core.ports.repository.ProjectRepository;
import com.mayk.TaskComplete.core.services.project.ProjectService;
import com.mayk.TaskComplete.core.dto.ProjectDTO;
import com.mayk.TaskComplete.core.services.project.validations.ProjectValidator;
import com.mayk.TaskComplete.core.validators.NotificationError;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

@RequiredArgsConstructor
public class ProjectServiceTest {

    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private EmailServicePort emailService;

    @Mock
    private ProjectValidator projectValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectService(projectRepository, emailService, projectValidator);
    }

    @Test
    void givenUserAndProjectDTO_thenAddNewProject() {
        User user = Mockito.mock();

        ProjectDTO projectDTO = new ProjectDTO("projeto01", "descricao01");

        projectService.addNewProject(user, projectDTO);

        Mockito.verify(projectRepository).saveProject(Mockito.any(Project.class));
    }

    @Test
    void givenUserAndProject_thenAddTeamMemberToProject() {
        User user = new User(1L, "teste", "teste@email.com");
        Long projectId = 1L;
        String email = "usuario@email.com";

        Project project = ProjectBuilder.builder()
                .projectOwner(user)
                .build();

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        Mockito.when(projectValidator.validateAddTeamMember()).thenReturn(new NotificationError());

        projectService.addTeamMember(user, projectId, email);

        Mockito.verify(projectRepository).findById(1L);

        Mockito.verify(emailService).sendInvitationToProject(Mockito.any(SendInvitationToProjectDTO.class));
    }

    @Test
    void givenUserAndProject_thenAddTeamMember_thenThrowProjectNotFound() {
        User user = new User(1L, "teste", "teste@email.com");
        Long projectId = 1L;
        String email = "usuario@email.com";

        Mockito.when(projectRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(ProjectNotFoundException.class, () -> {
            projectService.addTeamMember(user, projectId, email);
        });
    }

    @Test
    void givenUserAndProject_thenAddTeamMember_thenThrowAddTeamMemberException() {
        User user = new User(1L, "teste", "teste@email.com");
        Long projectId = 1L;
        String email = "usuario@email.com";

        Project project = ProjectBuilder.builder().build();

        Mockito.when(projectRepository.findById(1L)).
                thenReturn(Optional.of(project));

        var notification = new NotificationError();
        notification.addError("Teste");

        Mockito.when(projectValidator.validateAddTeamMember()).thenReturn(notification);

        Assertions.assertThrows(AddTeamMemberException.class, () -> {
           projectService.addTeamMember(user, projectId, email);
        });
    }
}
