package com.practice.demoCommunity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.demoCommunity.dto.CommunityUser;

public interface CommunityUserRepository extends JpaRepository<CommunityUser, String> {

}
