package com.practice.demoCommunity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demoCommunity.dto.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
	
	Optional<Gallery> findByName(String name);
//	Page<Gallery> findAll(Pageable pageable);
	
}
