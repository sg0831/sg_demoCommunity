package com.practice.demoCommunity.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice.demoCommunity.dto.Gallery;
import com.practice.demoCommunity.service.GalleryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {

	private final GalleryService galleryService;


	@GetMapping("/all")
	public String getAllGalleryPage(@RequestParam(defaultValue = "0") int page, Model model) {
		int pageSize = 10;
		int firstPageNum = 0;
		int lastPageNum = 0;
		Page<Gallery> resultPage = galleryService .getPagedAllGallery(page, pageSize);
		List<Gallery> resultDataList = resultPage .getContent();
		
		if (resultDataList .size() != 0 ) {
			firstPageNum = galleryService .getCurrentFirstPageNum(page, pageSize);
			lastPageNum = galleryService .getCurrentLastPageNum(page, pageSize, resultPage .getTotalPages() );
		}
		model .addAttribute("allGalleryList", resultDataList );
		// jpa 페이징에서 사용하는 0부터 시작하는 page값이 아닌 화면 출력에 사용되는 page 번호를 전달
		model .addAttribute("currentPageNum", page+1);
		model .addAttribute("startPage", firstPageNum);
		model .addAttribute("endPage", lastPageNum);
		model .addAttribute("hasPreviousPage", resultPage .hasPrevious() );
		model .addAttribute("hasNextPage", resultPage .hasNext() );
		return "gallery/allGalleryPage";
	}
}
