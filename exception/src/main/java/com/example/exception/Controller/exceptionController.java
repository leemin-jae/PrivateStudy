package com.example.exception.Controller;

import com.example.exception.Request.TestRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class exceptionController {

    @PostMapping("/user")
    public ResponseEntity<String> savePost(@Valid @RequestBody TestRequest userDto) {
        log.info(userDto.toString());
        return ResponseEntity.ok().body("postDto 객체 검증 성공");
    }

}
