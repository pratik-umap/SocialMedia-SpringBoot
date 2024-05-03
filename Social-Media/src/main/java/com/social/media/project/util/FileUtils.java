package com.social.media.project.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
  
	public static String copyImage(MultipartFile file) throws IOException {
		String filePath="/opt/socialmedia/"+file.getOriginalFilename();
        if (!file.isEmpty()) {
    		  Files.copy(file.getInputStream(), 
    				  Paths.get(filePath),
    				  StandardCopyOption.REPLACE_EXISTING);

		   }
        
        return filePath;
	}
}
