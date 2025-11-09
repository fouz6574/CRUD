package com.example.crud.task.controller;

import com.example.crud.task.model.Task;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    @PostMapping("/add")
    public String addTask(@RequestBody Task task) {
        tasks.add(task);
        return "Task added successfully ";
    }
    @PutMapping("/update/{index}")
    public String updateTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return "Task updated successfully ";
    }
    @DeleteMapping("/delete/{index}")
    public String deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return "Task deleted successfully ";
    }
    @PutMapping("/change-status/{index}")
    public String changeStatus(@PathVariable int index) {
        Task task = tasks.get(index);
        if (task.getStatus().equalsIgnoreCase("done")) {
            task.setStatus("not done");
        } else {
            task.setStatus("done");
        }
        return "Task status changed successfully ";
    }
    @GetMapping("/search/{title}")
    public Task findTaskByTitle(@PathVariable String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return task;
            }
        }
        return null;
    }

}
