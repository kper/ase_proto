package com.example.demo.repository;

import com.example.demo.entity.GamePlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlatformRepository extends JpaRepository<GamePlatformEntity, Integer> {
}
