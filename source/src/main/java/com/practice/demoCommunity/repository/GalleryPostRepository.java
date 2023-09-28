package com.practice.demoCommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demoCommunity.dto.GalleryPost;

public interface GalleryPostRepository extends JpaRepository<GalleryPost, Long> {

}
