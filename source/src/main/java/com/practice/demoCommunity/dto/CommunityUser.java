package com.practice.demoCommunity.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityUser {

	@Id
	private String userId;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@CreatedDate
	private LocalDateTime created;
	
	@Column(name = "isLogin", nullable = false)
	private boolean isLogin;
	
	@Column(name = "lastLoginTime", nullable = true)
	private LocalDateTime lastLoginTime;
	
}
