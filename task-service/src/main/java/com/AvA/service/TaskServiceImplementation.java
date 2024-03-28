package com.AvA.service;

import com.AvA.model.Task;
import com.AvA.model.TaskStatus;
import com.AvA.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplementation implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Task createTask(Task task, String requesterRole) throws Exception {
        if(!requesterRole.equals(("ROLE_ADMIN"))){
            throw new Exception("Only Admin can create Tasks");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(()->new Exception("Task not found with this ID - "+id));
    }

    @Override
    public List<Task> getAllTask(TaskStatus status) {
        List<Task> allTask=taskRepository.findAll();

        List<Task> filteredTasks=allTask.stream().filter(
                task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
        return filteredTasks;
    }

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {

        Task existinTask = getTaskById(id);

        if(updatedTask.getTitle()!=null){
            existinTask.setTitle(updatedTask.getTitle());
        }
        if(updatedTask.getImage()!=null){
            existinTask.setImage(updatedTask.getImage());
        }
        if(updatedTask.getDescription()!=null){
            existinTask.setDescription(updatedTask.getDescription());
        }
        if(updatedTask.getStatus()!=null){
            existinTask.setStatus(updatedTask.getStatus());
        }
        if(updatedTask.getDeadline()!=null){
            existinTask.setDeadline(updatedTask.getDeadline());
        }
        return taskRepository.save(existinTask);
    }

    @Override
    public void deleteTask(Long id) throws Exception {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Task assignedToUser(Long userID, Long taskId) throws Exception {

        Task task = getTaskById(taskId);
        task.setAssignedUserId(userID);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUserTask(Long userId, TaskStatus status) {

        List<Task> allTask=taskRepository.findByAssignedUserId(userId);

        List<Task> filteredTasks=allTask.stream().filter(
                task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());

        return filteredTasks;

    }

    @Override
    public Task completeTask(Long taskId) throws Exception {

        Task task = getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);

        return taskRepository.save(task);
    }
}
