package com.weebablu.taskmanager.models;

import com.weebablu.taskmanager.enums.Status;

public class UndoAction {
    public enum ActionType {
        ADD, DELETE, STATUS_CHANGE
    }

    private ActionType actionType;
    private Task taskSnapshot;
    private int taskIndex;
    private Status previousStatus;

    public UndoAction(ActionType actionType, Task taskSnapshot, int taskIndex, Status previousStatus) {
        this.actionType = actionType;
        this.taskSnapshot = taskSnapshot;
        this.taskIndex = taskIndex;
        this.previousStatus = previousStatus;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Task getTaskSnapshot() {
        return taskSnapshot;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public Status getPreviousStatus() {
        return previousStatus;
    }

}
