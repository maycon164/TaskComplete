package com.mayk.TaskComplete.core.services.project;

import com.mayk.TaskComplete.core.dto.SendInvitationToProjectDTO;
import com.mayk.TaskComplete.core.exception.AddTeamMemberException;
import com.mayk.TaskComplete.core.exception.ProjectNotFoundException;
import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.model.builder.ProjectBuilder;
import com.mayk.TaskComplete.core.ports.EmailServicePort;
import com.mayk.TaskComplete.core.ports.repository.ProjectRepository;
import com.mayk.TaskComplete.core.dto.ProjectDTO;
import com.mayk.TaskComplete.core.services.project.validations.ProjectValidator;
import com.mayk.TaskComplete.core.validators.NotificationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmailServicePort emailService;
    private final ProjectValidator projectValidator;

    public ProjectService(ProjectRepository projectRepository, EmailServicePort emailService, ProjectValidator projectValidator) {
        this.projectRepository = projectRepository;
        this.emailService = emailService;
        this.projectValidator = projectValidator;
    }

    public Project addNewProject(User user, ProjectDTO projectDTO) {
        LocalDate createdAt = LocalDate.now();

        Project project = ProjectBuilder.builder()
                .name(projectDTO.name())
                .description(projectDTO.description())
                .createdAt(createdAt)
                .createdBy(user)
                .projectOwner(user)
                .build();

        return projectRepository.saveProject(project);
    }

    public void addTeamMember(User user, Long projectId, String email) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        NotificationError notificationError = projectValidator.validateAddTeamMember();

        if(notificationError.hasErrors()) {
            throw new AddTeamMemberException(notificationError.getErrors());
        }

        SendInvitationToProjectDTO sendInvitationToProjectDTO = new SendInvitationToProjectDTO(project, email);

        emailService.sendInvitationToProject(sendInvitationToProjectDTO);
    }

}
