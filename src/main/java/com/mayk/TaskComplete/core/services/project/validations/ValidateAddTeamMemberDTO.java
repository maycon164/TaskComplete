package com.mayk.TaskComplete.core.services.project.validations;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.User;

public record ValidateAddTeamMemberDTO (
   User projectOwner,
   Project project,
   String invitedEmail
){ }
