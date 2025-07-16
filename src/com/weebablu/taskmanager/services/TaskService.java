package com.weebablu.taskmanager.services;

import com.weebablu.taskmanager.enums.PriorityLevel;
import com.weebablu.taskmanager.models.Task;
import com.weebablu.taskmanager.enums.Status;
import com.weebablu.taskmanager.models.UndoAction;
import com.weebablu.taskmanager.models.UndoAction.ActionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;

public class TaskService {
    private List<Task> taskList;
    private int nextId;
    private final Deque<UndoAction> undoStack = new ArrayDeque<>();
    private final int MAX_HISTORY = 3;

    public TaskService() {
        this.taskList = new ArrayList<>();
        this.nextId = 1;
    }

    public Task addTask(String title, String description, PriorityLevel priority, LocalDateTime dueDate) {
        Task task = new Task(nextId++, title, description, dueDate, priority);
        taskList.add(task);
        recordUndo(new UndoAction(ActionType.ADD, task, taskList.size() - 1, null));
        return task;
    }

    public void viewAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println(" \nNo tasks available. Add one to get started!" + " :)\n");
            return;
        }
        System.out.println("\n Task Board: ");
        System.out.println(" " + "=".repeat(50));
        System.out.println();

        // for (Task task : taskList) {
        // System.out.println(task);
        // System.out.println(" " + "-".repeat(50));
        // }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("\n %d %s\n", i + 1, taskList.get(i));
            System.out.println(" " + "-".repeat(50));
        }
    }

    public void updateTaskStatus(int taskIndex, Status newStatus) {
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task task = taskList.get(taskIndex);
            Status oldStatus = task.getStatus();
            task.setStatus(newStatus);
            recordUndo(new UndoAction(ActionType.STATUS_CHANGE, task, taskIndex, oldStatus));
            System.out.println(" Task status updated successfully! :)");
        } else {
            System.out.println(" [!] Invalid task index.");
        }
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task removed = taskList.remove(taskIndex);
            recordUndo(new UndoAction(ActionType.DELETE, removed, taskIndex, null));
            System.out.println(" [-] Deleted Task: " + removed.getTitle());
        } else {
            System.out.println(" [!] Invalid task index. Deletion failed.");
        }
    }

    public boolean isTaskListEmpty() {
        return taskList.isEmpty();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void filterTasksByPriority(PriorityLevel priority) {
        boolean found = false;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getPriority() == priority) {
                System.out.printf(" [%d] %s\n", i + 1, task);
                found = true;
            }
        }
        if (!found)
            System.out.println(" No tasks found with priority: " + priority);
    }

    public void filterTasksByStatus(Status status) {
        boolean found = false;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getStatus() == status) {
                System.out.printf(" [%d] %s\n", i + 1, task);
                found = true;
            }
        }
        if (!found)
            System.out.println(" No tasks found with the status: " + status);
    }

    public void undoLastAction() {
        if (undoStack.isEmpty()) {
            System.out.println(" Nothing to undo.");
            return;
        }

        UndoAction action = undoStack.pop();
        switch (action.getActionType()) {
            case ADD:
                taskList.remove(action.getTaskIndex());
                System.out.println(" \nUndo: Task addition reversed.");
                break;
            case DELETE:
                taskList.add(action.getTaskIndex(), action.getTaskSnapshot());
                System.out.println(" Undo: Task deletion reversed.");
                break;
            case STATUS_CHANGE:
                taskList.get(action.getTaskIndex()).setStatus(action.getPreviousStatus());
                break;
            default:
                break;
        }
    }

    private void recordUndo(UndoAction action) {
        if (undoStack.size() == MAX_HISTORY) {
            undoStack.removeLast();
        }
        undoStack.push(action);
    }
}
