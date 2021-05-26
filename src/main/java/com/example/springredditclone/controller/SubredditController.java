package com.example.springredditclone.controller;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.exception.SpringRedditException;
import com.example.springredditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {
    private final SubredditService subredditService;
    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDTO));
    }
    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddit(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubreddit(@PathVariable(value = "id") Long id) throws SpringRedditException {
        return ResponseEntity.status(HttpStatus.OK).body(subredditService.getSubreddit(id));
    }

    @PostMapping("/check-subreddit")
    public ResponseEntity<String> checkSubreddit(@RequestBody SubredditDto subredditDTO){
        if(!subredditService.checkSubredditByName(subredditDTO.getName())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Subreddit already exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Ok");
    }

}