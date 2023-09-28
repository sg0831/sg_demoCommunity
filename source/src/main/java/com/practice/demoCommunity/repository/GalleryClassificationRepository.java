package com.practice.demoCommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demoCommunity.dto.GalleryClassification;

public interface GalleryClassificationRepository extends JpaRepository<GalleryClassification, String> {

}
