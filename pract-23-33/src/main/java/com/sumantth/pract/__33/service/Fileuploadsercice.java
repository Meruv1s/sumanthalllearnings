package com.sumantth.pract.__33.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class Fileuploadsercice {

    @Autowired
    private s3fileservice s3fileservice;

    private int MAX_ALLOWED_PDF_SIZE=10 *1024 *1024;
    @Value("${file.upload.pdf.path}")
    private String Image_Upload_PDF_path;
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
      // Files.copy(inputfile.getInputStream(),uploadpath);
        s3fileservice.uploadFileToS3(inputfile,uploadpdffileName);// this line stores in s3
    }
}
