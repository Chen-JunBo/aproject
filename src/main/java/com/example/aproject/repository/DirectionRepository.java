package com.example.aproject.repository;


import com.example.aproject.entity.Direction;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends BaseRepository<Direction,Integer> {
    Direction findByName(String name);
}