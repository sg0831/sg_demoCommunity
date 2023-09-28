package com.practice.demoCommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demoCommunity.dto.GalleryComment;

public interface GalleryCommentRepository extends JpaRepository<GalleryComment, Long> {

}
