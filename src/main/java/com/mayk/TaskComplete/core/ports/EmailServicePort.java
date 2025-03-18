package com.mayk.TaskComplete.core.ports;

import com.mayk.TaskComplete.core.dto.SendInvitationToProjectDTO;

public interface EmailServicePort {

    void sendInvitationToProject(SendInvitationToProjectDTO sendEmailDTO);
}
