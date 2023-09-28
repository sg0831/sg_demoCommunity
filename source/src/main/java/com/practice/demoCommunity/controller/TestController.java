package com.practice.demoCommunity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.demoCommunity.service.CommunityTestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

	private final CommunityTestService communityTestService;

	@GetMapping("/setDummyData")
	public String setDummyData() {
		System.out.println("============ 테스트 시작 ============");
		communityTestService .insertTestDummyData100();
		return "test";
	}

}
