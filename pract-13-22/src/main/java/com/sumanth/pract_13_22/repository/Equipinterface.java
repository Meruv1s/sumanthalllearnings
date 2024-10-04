package com.sumanth.pract_13_22.repository;

import com.sumanth.pract_13_22.entity.Equipments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Equipinterface extends JpaRepository<Equipments,Long> {
}
