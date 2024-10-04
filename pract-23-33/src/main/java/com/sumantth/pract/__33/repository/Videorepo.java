package com.sumantth.pract.__33.repository;

import com.sumantth.pract.__33.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Videorepo extends JpaRepository<Video,Integer> {

   @Query(value="select * from videos v where v.title like %:searchword%", nativeQuery = true)
   List<Video> searchVideos(@Param("searchword") String searchword);


}
