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
                case "3":
                    handleUpdateStatus();
                    break;
                case "4":
                    handleDeleteTask();
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
        System.out.println(" 1. [+] Add Task");
        System.out.println(" 2. [#] View All Tasks");
        System.out.println(" 3. [o] Mark Task as DONE");
        System.out.println(" 4. [-] Delete Task");
        System.out.println(" 0. [X] Exit");
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
            System.out.println(" Enter due date (yyyy-MM-dd HH:mm): ");
            String dateInput = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dueDate = LocalDateTime.parse(dateInput, formatter);

            taskService.addTask(title, description, priority, dueDate);
            System.out.println(" [:)] Task addded successfully!");
        } catch (Exception e) {
            System.out.println(" [!] Failed to add tasks. Please check your inputs.");
            // e.printStackTrace();

        }
    }

    private static void handleUpdateStatus() {
        try {
            if (taskService.isTaskListEmpty()) {
                // System.out.println(" [!] No tasks to delete.");
                taskService.viewAllTasks();
                return;
            }
            taskService.viewAllTasks(); // Displaying all the tasks
            System.out.println(" Enter the task number to mark as DONE: ");
            int index = Integer.parseInt(sc.nextLine());
            taskService.updateTaskStatus(index - 1, Status.DONE);
        } catch (Exception e) {
            System.out.println(" [!] Failed to update task status. Invalid Input.");
            // e.printStackTrace();
        }
    }

    private static void handleDeleteTask() {
        try {
            if (taskService.isTaskListEmpty()) {
                // System.out.println(" [!] No tasks to delete.");
                taskService.viewAllTasks();
                return;
            }
            taskService.viewAllTasks(); // Displaying all the tasks
            System.out.println(" Enter the task number to delete: ");
            int index = Integer.parseInt(sc.nextLine());
            taskService.deleteTask(index - 1);
        } catch (Exception e) {
            System.out.println(" [!] Failed to delete task. Invalid input.");
            // e.printStackTrace();

        }
    }
}
