package com.weebablu.taskmanager.services;

import java.time.LocalDateTime;

import com.weebablu.taskmanager.enums.PriorityLevel;
import com.weebablu.taskmanager.enums.Status;
import com.weebablu.taskmanager.models.Task;

public interface ITaskService {

    Task addTask(String title, String description, PriorityLevel priority, LocalDateTime dueDate);

    void viewAllTasks();

    void updateTaskStatus(int taskIndex, Status newStatus);

    void deleteTask(int taskIndex);

    boolean isTaskListEmpty();

    Task getTask(int index);

    void filterTasksByPriority(PriorityLevel priority);

    void filterTasksByStatus(Status status);

    void undoLastAction();

    void redoLastAction();

}