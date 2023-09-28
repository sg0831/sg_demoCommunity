package com.practice.demoCommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demoCommunity.dto.GalleryBoard;

public interface GalleryBoardRepository extends JpaRepository<GalleryBoard, Long> {

}
