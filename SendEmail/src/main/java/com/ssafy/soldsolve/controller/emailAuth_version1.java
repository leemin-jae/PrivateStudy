package com.ssafy.soldsolve.controller;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.soldsolve.service.MailSendService_version1;


@Controller
@RequestMapping("/join/mailAuth.wow")
public class emailAuth_version1 {
	@Autowired
	MailSendService_version1 mailSendService;  //@Service를 붙였었다.

	@GetMapping()
	@ResponseBody
	public String mailAuth(@RequestParam String mail, HttpServletResponse resp) throws Exception {
		
	    String authKey = mailSendService.sendAuthMail(mail); //사용자가 입력한 메일주소로 메일을 보냄
	    return authKey;
	}
}