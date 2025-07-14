package com.weebablu.taskmanager.services;

import com.weebablu.taskmanager.enums.PriorityLevel;
import com.weebablu.taskmanager.models.Task;
import com.weebablu.taskmanager.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> taskList;
    private int nextId;

    public TaskService() {
        this.taskList = new ArrayList<>();
        this.nextId = 1;
    }

    public Task addTask(String title, String description, PriorityLevel priority, LocalDateTime dueDate) {
        Task task = new Task(nextId++, title, description, dueDate, priority);
        taskList.add(task);
        return task;
    }

    public void viewAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println(" \nNo tasks available. Add one to get started! :)\n");
            return;
        }
        System.out.println("\n Task Board: ");
        System.out.println(" " + "=".repeat(50));

        for (Task task : taskList) {
            System.out.println(task);
            System.out.println(" " + "-".repeat(50));
        }
    }

    public void updateTaskStatus(int taskIndex, Status newStatus) {
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task task = taskList.get(taskIndex);
            task.setStatus(newStatus);
            System.out.println(" Task status updated successfully! :)");
        } else {
            System.out.println(" ✕ Invalid task index.");
        }
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task removed = taskList.remove(taskIndex);
            System.out.println(" ▥ Deleted Task: " + removed.getTitle());
        } else {
            System.out.println(" ✕ Invalid task index. Deletion failed.");
        }
    }
}
