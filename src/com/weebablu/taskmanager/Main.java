package com.weebablu.taskmanager;

import com.weebablu.taskmanager.enums.*;
// import com.weebablu.taskmanager.utils.*;
// import com.weebablu.taskmanager.exceptions.*;
// import com.weebablu.taskmanager.models.Task;
import com.weebablu.taskmanager.services.TaskService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final TaskService taskService = new TaskService();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    handleAddTask();
                    break;
                case "2":
                    taskService.viewAllTasks();
                    break;
                case "0":
                    System.out.println(" :) Exiting Task Manager. \n    Stay Productive!");
                    return;

                default:
                    System.out.println(" !! Invalid choice. Try again.");
                    break;
            }
        }

    }

    private static void showMenu() {
        System.out.println("\n === Task Manager CLI ===");
        System.out.println(" 1. + Add Task");
        System.out.println(" 2. ▮ View All Tasks");
        System.out.println(" 0. ✕ Exit");
        System.out.println(" Enter your choice: ");
    }

    private static void handleAddTask() {
        try {
            System.out.println(" Enter task title: ");
            String title = sc.nextLine();
            System.out.println(" Enter task description: ");
            String description = sc.nextLine();
            System.out.println(" Enter Priority (LOW, MEDIUM, HIGH): ");
            PriorityLevel priority = PriorityLevel.valueOf(sc.nextLine().toUpperCase());
            System.out.println(" Enter due date (yyyy-mm-dd HH:mm): ");
            String dateInput = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");
            LocalDateTime dueDate = LocalDateTime.parse(dateInput, formatter);

            taskService.addTask(title, description, priority, dueDate);
            System.out.println(" ✓ Task addded successfully!");
        } catch (Exception e) {
            System.out.println(" ✕ Failed to add tasks. Please check your inputs.");
        }
    }
}
