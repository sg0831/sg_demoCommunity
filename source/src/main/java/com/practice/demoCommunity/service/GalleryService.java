package com.practice.demoCommunity.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practice.demoCommunity.dto.Gallery;
import com.practice.demoCommunity.dto.GalleryBoard;
import com.practice.demoCommunity.dto.GalleryClassification;
import com.practice.demoCommunity.repository.GalleryBoardRepository;
import com.practice.demoCommunity.repository.GalleryClassificationRepository;
import com.practice.demoCommunity.repository.GalleryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleryService {

	private final GalleryRepository galleryRepository;
	private final GalleryClassificationRepository galleryClassificationRepository;
	private final GalleryBoardRepository galleryBoardRepository;

	public List<String> getAllGalleryClassificationNames() {
		return galleryClassificationRepository.findAll().stream().map(GalleryClassification::getName)
				.collect(Collectors.toList());
	}

	public List<Gallery> getAllGallery() {
		return galleryRepository .findAll();
	}


	public int getCurrentLastPageNum(int currentPageNum, int pageSize, int totalPages) {
		int currentLastPageNum = ((currentPageNum / pageSize) + 1) * pageSize;
		return currentLastPageNum >= totalPages ? currentLastPageNum : totalPages;
	}
	
	
	public int getCurrentFirstPageNum(int currentPageNum, int pageSize) {
		return (currentPageNum / pageSize) * pageSize + 1;
	}


	public Page<Gallery> getPagedAllGallery(int pageNum, int pageSize) {
		Pageable pageable = PageRequest .of(pageNum, pageSize);
		return galleryRepository.findAll(pageable);
	}
	
	
	public Gallery getOneGalleryById(Long galleryId) {
		return galleryRepository.findById(galleryId)
				.orElse(null);
	}

	public Gallery getOneGalleryByName(String galleryName) {
		return galleryRepository.findByName(galleryName)
				.orElse(null);
	}
	
	
	public List<GalleryBoard> getBoardListFromGallery(Long galleryId) {
		Gallery fromGallery = galleryRepository .findById(galleryId) .orElse(null);
		return galleryBoardRepository .findByFromGalleryOrderByOrderNum( fromGallery );
	}

}
