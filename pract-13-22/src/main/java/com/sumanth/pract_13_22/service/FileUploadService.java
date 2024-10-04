package com.sumanth.pract_13_22.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileUploadService {
//    get file type (extension)
//    check if it is in allowed file types -> if yes ok else throw error
//    check file size -> if it is under allowed size ok else throw error (Bytes)
//    generate unique file name
// upload to folder
    private int MAX_ALLOWED_SIZE=5 *1024 *1024;
    private int MAX_ALLOWED_PDF_SIZE=10 *1024 *1024;
    @Value("${file.upload.images.path}")
    private String Image_Upload_path;
    @Value("${file.upload.pdf.path}")
    private String Image_Upload_PDF_path;
    public void handleImageUpload(MultipartFile inputfile) throws Exception
    {
   String filename= StringUtils.cleanPath(inputfile.getOriginalFilename());
   String filetype=StringUtils.getFilenameExtension(filename);
        System.out.println(filetype);
        String[] allowedfiletypes={"png","jpg","gif","jpeg"};
        Boolean isfileallowed= Arrays.stream(allowedfiletypes).anyMatch(filetype::equals);
        if(isfileallowed==false)
        {
            throw new Exception(filetype+"file is not allowed");

        }
        if(inputfile.getSize() >MAX_ALLOWED_SIZE)
        {
            throw  new Exception("Max 5Mb allowed");
        }
        String uploadImageName= UUID.randomUUID().toString() +"."+filetype;
        System.out.println(uploadImageName);

        Path uploadpath= Paths.get(Image_Upload_path+uploadImageName);
        Files.copy(inputfile.getInputStream(),uploadpath);
    }
   public void handlepdfupload(MultipartFile inputfile) throws Exception
   {
       String filename= StringUtils.cleanPath(inputfile.getOriginalFilename());
       String filetype=StringUtils.getFilenameExtension(filename);
       if(filetype.equals("pdf")==false)
       {
           throw new Exception(filetype+"file is not allowed");

       }
       if(inputfile.getSize() >MAX_ALLOWED_PDF_SIZE)
       {
           throw  new Exception("Max 10Mb allowed");
       }
       String uploadpdffileName= UUID.randomUUID().toString() +".pdf";
       System.out.println(uploadpdffileName);
       Path uploadpath= Paths.get(Image_Upload_PDF_path+uploadpdffileName);
       Files.copy(inputfile.getInputStream(),uploadpath);
   }
}
