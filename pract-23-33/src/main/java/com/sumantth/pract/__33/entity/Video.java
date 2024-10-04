package com.sumantth.pract.__33.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="video_id")
    private int id;
    private String title;
    private String videopath;
    private int channelId;
    private int nooflikes;
    private int noofdislikes;
    private String Thumbnailpath;



}
