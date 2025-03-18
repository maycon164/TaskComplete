package com.mayk.TaskComplete.core.exception;

import java.util.List;

public class AddTeamMemberException extends RuntimeException{

    public AddTeamMemberException() {
        super("Error when adding team member to project");
    }

    public AddTeamMemberException(List<String> errors) {
        super(String.join("\n", errors));
    }
}
