package com.manager.todo.Application.Controler;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);

    // single file uploading
    @PostMapping("/single")
    public String fileUpoarding(@RequestParam("image")MultipartFile file){
        logger.info("Name :{}", file.getName());
        logger.info("Content: {}", file.getContentType());
        logger.info("original file name:{}", file.getOriginalFilename());
        logger.info("file size name:{}",file.getSize());
        return "File uploaded";
    }


    // multipale file uploading
    // multiple file ke liye MultipartFile[] array use krenge'
@PostMapping("/multiplefile")
    public String multiplefileuploading(@RequestParam("files")MultipartFile[] files){
        Arrays.stream(files).forEach(file -> {
            logger.info("original file name {}", file.getOriginalFilename());
            logger.info("file content name {}", file.getContentType());
            logger.info("file size name:{}",file.getSize());
        });
        return "Multiple file uploaded";

    }

    // image response means send image to the user
    @GetMapping("/serveimage")
    public void serveImage(HttpServletResponse response){
        // image
        try {
            InputStream fileInputStream = new FileInputStream("images/sudhir.png");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream,response.getOutputStream());

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
