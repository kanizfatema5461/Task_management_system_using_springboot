package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskReqDto;
import com.example.taskmanager.entity.TaskManagerEntity;
import com.example.taskmanager.repository.TaskManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskManagerService {

    @Autowired
    TaskManagerRepository taskManagerRepository;

//    addtask

    public void addtask(TaskReqDto taskdto){
        TaskManagerEntity task = new TaskManagerEntity(
                null,
                taskdto.title(),
                taskdto.description(),
                taskdto.status()
        );
        taskManagerRepository.save(task);
    }

//    get single task (View task)

    public TaskManagerEntity getById(Long id){
        TaskManagerEntity taskManagerEntity = taskManagerRepository.findById(id).get();
        return taskManagerEntity;
    }

//    get all task

    public List<TaskManagerEntity> getAll(){
        return taskManagerRepository.findAll();
    }

//    update task

    public TaskManagerEntity updatetask(Long id, TaskReqDto taskReqDto){
        TaskManagerEntity existing = taskManagerRepository.findById(id).orElse(null);

        if(existing != null){
            existing.setTitle(taskReqDto.title());
            existing.setDescription(taskReqDto.description());
            existing.setStatus(taskReqDto.status());

            return taskManagerRepository.save(existing);
        }

        return null;
    }

    //    delete task

    public void deleteById(Long id) {
        taskManagerRepository.deleteById(id);
    }
}
