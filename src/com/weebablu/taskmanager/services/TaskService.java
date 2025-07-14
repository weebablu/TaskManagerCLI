package com.weebablu.taskmanager.services;

import com.weebablu.taskmanager.enums.PriorityLevel;
import com.weebablu.taskmanager.enums.Status;
import com.weebablu.taskmanager.models.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> taskList;
    private int nextId;
    public TaskService(){
        this.taskList = new ArrayList<>();
        this.nextId = 1;
    }
}
