package com.mayk.TaskComplete.core.dto;

import com.mayk.TaskComplete.core.model.Project;

public record SendInvitationToProjectDTO(
    Project project,
    String invitedEmail
) { }
