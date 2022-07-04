package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.boot.SpringApplication;
import java.io.IOException;
import java.io.File;

@SpringBootApplication
@ComponentScan("com.example.demo")
public class RestDemoApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(RestDemoApplication.class, args);
	}

	public static void CopyToFolderImage(MultipartFile multipartFile, String name) throws IOException
	{
		File newImage = new File( "C:\\User\\user\\Рабочий стол\\demo2\\target\\classes\\static\\images\\" + name);
		newImage.createNewFile();
		multipartFile.transferTo( newImage );
	}
}
