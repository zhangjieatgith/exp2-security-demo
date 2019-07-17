package cn.zhang.jie.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.zhang.jie.dto.FileInfo;

@RestController
@RequestMapping("/file")
public class FileController {

	String folder = "D:\\workspacke\\zhangjie\\exp2-security-demo\\src\\main\\java\\cn\\zhang\\jie\\web\\controller\\";
	
	@PostMapping
	public FileInfo upload(MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		File localFile = new File(folder, new Date().getTime()+".txt");
		file.transferTo(localFile);
		//前后端分离的时候，往往返回给调用者一个文件地址
		return new FileInfo(localFile.getAbsolutePath());
	}
	
	@GetMapping("/{id}")
	public void download(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) {
		try(InputStream is = new FileInputStream(new File(folder,id+".txt"));
			OutputStream os = response.getOutputStream()){
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			IOUtils.copy(is, os);
			os.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
