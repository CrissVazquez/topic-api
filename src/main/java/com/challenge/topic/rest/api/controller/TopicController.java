package com.challenge.topic.rest.api.controller;

import com.challenge.topic.rest.api.entity.Topic;
import com.challenge.topic.rest.api.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("topicos")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public void saveTopic(@Valid @RequestBody Topic topic) throws Exception {
        topicService.saveTopic(topic);
    }

    @PutMapping
    public void updateTopic(@Valid @RequestBody Topic topic) throws Exception {
        topicService.updateTopic(topic);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopicById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(topicService.getTopic(id));
    }

    @GetMapping
    public ResponseEntity getTopics() {
        return ResponseEntity.ok(topicService.getTopics());
    }

    @DeleteMapping("/{id}")
    public void deleteTopicById(@PathVariable("id") Integer id) {
        topicService.deleteTopicById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
