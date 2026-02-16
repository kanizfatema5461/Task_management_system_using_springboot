package com.example.taskmanager.controller;


import com.example.taskmanager.dto.TaskReqDto;
import com.example.taskmanager.entity.TaskManagerEntity;
import com.example.taskmanager.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.swing.*;
import java.util.List;

@RestController
public class TaskManagerController {
    @Autowired
    TaskManagerService taskManagerService;



    // TASK ADD

    @PostMapping("addtask")
    public String addtask(@RequestBody TaskReqDto taskReqDto){
        taskManagerService.addtask(taskReqDto);
        return "Task Added Successfully";
    }


    //SINGLE TASK VIEW

    @GetMapping("/{id}")
    public TaskManagerEntity getById(@PathVariable Long id){
        return taskManagerService.getById(id);
    }

    //ALL TASK VIEW

    @GetMapping("All")
    public List<TaskManagerEntity> getAll(){
        return taskManagerService.getAll();
    }

    //    UPDATE TASK

    @PutMapping("/update/{id}")
    public String updateTask(@PathVariable Long id,
                                        @RequestBody TaskReqDto taskReqDto) {
        TaskManagerEntity updatedtask = taskManagerService.updatetask(id, taskReqDto);

        if(updatedtask != null){
            return "Updated Successfully";
        }
        else {
            return "Error: Task not found with " + id;
        }

    }


    //    DELETE TASK

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        taskManagerService.deleteById(id);
        return "Deleted Successfully";
    }


}

