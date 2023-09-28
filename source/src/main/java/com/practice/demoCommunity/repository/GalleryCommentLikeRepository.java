package com.practice.demoCommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demoCommunity.dto.GalleryCommentLike;

public interface GalleryCommentLikeRepository extends JpaRepository<GalleryCommentLike, Long> {

}
