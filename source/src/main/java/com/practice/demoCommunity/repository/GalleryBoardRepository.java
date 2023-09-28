package com.practice.demoCommunity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demoCommunity.dto.Gallery;
import com.practice.demoCommunity.dto.GalleryBoard;

public interface GalleryBoardRepository extends JpaRepository<GalleryBoard, Long> {
	List<GalleryBoard> findByFromGallery(Gallery fromGallery);
	List<GalleryBoard> findByFromGalleryOrderByOrderNum(Gallery fromGallery);
}
