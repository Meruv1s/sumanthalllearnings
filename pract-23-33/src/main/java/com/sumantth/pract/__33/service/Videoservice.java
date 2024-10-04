package com.sumantth.pract.__33.service;

import com.sumantth.pract.__33.entity.Video;
import com.sumantth.pract.__33.pojo.VideoApiDto;
import com.sumantth.pract.__33.repository.Videorepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Videoservice {

    @Autowired
    Videorepo videorepo;

    public List<Video> handlevideos(VideoApiDto videoApiDto)
    {
      //  System.out.println("Search word: " + videoApiDto);  // Log search word for debugging
       List<Video> videos = videorepo.searchVideos(videoApiDto.getSearchword());
      System.out.println(videos);
        return videos;
    }
}
