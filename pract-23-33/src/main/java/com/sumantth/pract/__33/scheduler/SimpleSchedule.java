package com.sumantth.pract.__33.scheduler;

import com.sumantth.pract.__33.service.s3fileservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SimpleSchedule {

    @Autowired
   private  s3fileservice s3fileservice;
//  @Scheduled(fixedRate = 5000)
//    public void printsomedata()
//    {
//        System.out.println("Running rom scheduler");
//    }
    //cron(sec(0-60) miin (0-59) hours(0-23) date(0-30) month(0-11) weeekday
    @Scheduled(cron ="0 0 1 * * *")
//    @Scheduled(fixedRate = 5000)
    public void uploadlogstos3()  throws Exception
    {
//        System.out.println("upload logs to aws s3");
/*
  logs directory
  get all files from logs folder
  loop ->upload each file
 */
        String logsfolder = System.getProperty("user.dir")  + "/logs";

        File logsdirectory = new File(logsfolder);
     File[] logfiles=    logsdirectory.listFiles();
   for (File logfile:logfiles)
   {
   s3fileservice.logFileToS3(logfile,"log/"+logfile.getName());
   }


    }

}
