package com.mayk.TaskComplete.core.service;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.repository.ProjectRepository;
import com.mayk.TaskComplete.core.services.ProjectService;
import com.mayk.TaskComplete.infra.dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@RequiredArgsConstructor
public class ProjectServiceTest {

    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectService(projectRepository);
    }

    @Test
    void givenUserAndProjectDTO_thenAddNewProject() {
        User user = Mockito.mock();
        ProjectDTO projectDTO = new ProjectDTO("projeto01", "descricao01");

        Mockito.when(projectRepository.saveProject(Mockito.any())).thenReturn(Mockito.any());

        projectService.addNewProject(user, projectDTO);

        Mockito.verify(projectRepository).saveProject(Mockito.any());
    }

}
