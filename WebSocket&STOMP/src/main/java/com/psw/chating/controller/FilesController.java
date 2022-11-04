package com.psw.chating.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.psw.chating.Files;
import com.psw.chating.FilesService;

@Controller
public class FilesController {

	@Autowired
	FilesService filesService;

	@RequestMapping("/insert")
	public ModelAndView Insert() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("insert");
		return mv;
	}
	

	@RequestMapping("/mung/fileinsert")
	public String fileinsert(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {

		
		String path = System.getProperty("user.dir");
        		
		Files file = new Files();

		String sourceFileName = files.getOriginalFilename();
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = path + "/src/main/resources/static/images/";
		// mung-1은 자기 프로젝트이름으로 체인지!!

		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
			destinationFile = new File(fileUrl + destinationFileName);
		} while (destinationFile.exists());

		destinationFile.getParentFile().mkdirs();
		files.transferTo(destinationFile);
		

		file.setFilename(fileUrl + destinationFileName);
		//file.setFileOriName(sourceFileName);
		//file.setFileurl(fileUrl);
		filesService.save(file);
		return "redirect:/insert";
	}
}