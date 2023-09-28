package com.practice.demoCommunity.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gallery_post_like")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GalleryPostLike {

	@Id  @GeneratedValue ( strategy  =  GenerationType . IDENTITY ) 
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "gallery_post_id", nullable = false)
	private GalleryPost galleryPost;
	
	@ManyToOne
	@JoinColumn(name = "from_user_id", nullable = false)
	private CommunityUser fromUser;
	
	@Column(name = "created")
	private LocalDateTime created;
	
}
