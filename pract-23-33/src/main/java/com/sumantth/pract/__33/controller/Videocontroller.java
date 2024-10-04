package com.sumantth.pract.__33.controller;

import com.sumantth.pract.__33.entity.Video;
import com.sumantth.pract.__33.pojo.VideoApiDto;
import com.sumantth.pract.__33.service.Videoservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Videocontroller {

    @Autowired
    Videoservice videoservice;


    @GetMapping("/search")
    public ResponseEntity<List<Video>> searchVideos(@Valid @RequestBody VideoApiDto videoApiDto) {
        List<Video> videos = videoservice.handlevideos(videoApiDto);
        return ResponseEntity.ok(videos);
    }
}