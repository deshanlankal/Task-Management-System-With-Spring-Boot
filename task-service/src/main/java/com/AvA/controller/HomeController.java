package com.AvA.controller;

import com.AvA.model.Task;
import com.AvA.model.TaskStatus;
import com.AvA.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/tasks")
    public ResponseEntity<String> getAllTask() {


        return new ResponseEntity<>("Welcome To Task Service", HttpStatus.OK);
    }
}
