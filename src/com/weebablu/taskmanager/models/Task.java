package com.weebablu.taskmanager.models;

import java.time.LocalDateTime;
import com.weebablu.taskmanager.enums.PriorityLevel;
import com.weebablu.taskmanager.enums.Status;

public class Task {

    // Fields
    private int id; // Unique Identifier for each task to track it
    private String title;
    private String description;
    private LocalDateTime dueDate; // For reminders
    private PriorityLevel priority; // High (or) Medium (or) Low
    private Status status; // Completed (or) pending
    private LocalDateTime createdAt; // For tracking
    private LocalDateTime updatedAt; // For undo

    // Constructors
    public Task(int id, String title, String description, LocalDateTime dueDate, PriorityLevel priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = Status.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityLevel getPriority() {
        return priority;
    }

    public void setPriority(PriorityLevel priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ToString method for displaying in CLI

    @Override
    public String toString() {
        String statusIcon = (status == Status.DONE ? "[o]" : "[ ]");
        return String.format("\n " + "%s %s (Priority: %s)\n    Due: %s | Status: %s\n", statusIcon, title, priority.name(),
                dueDate.toString().replace('T', ' '), status.name());
    }

}