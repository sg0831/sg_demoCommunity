package com.practice.demoCommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demoCommunity.dto.GalleryPostLike;

public interface GalleryPostLikeRepository extends JpaRepository<GalleryPostLike, Long> {

}
