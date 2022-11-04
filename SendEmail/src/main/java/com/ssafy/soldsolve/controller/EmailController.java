package com.ssafy.soldsolve.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.ssafy.soldsolve.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping
public class EmailController {

//	//private final EmailService emailService;
//
//	@GetMapping("/confirm-email")
//	public ResponseEntity<Boolean> viewConfirmEmail(@RequestParam String token) {
//		try {
//			boolean result = emailService.verifyEmail(token);
//			return new ResponseEntity<>(result,HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
//		}
//	}

}