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
@Table(name = "gallery_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GalleryPost {

	@Id  @GeneratedValue ( strategy  =  GenerationType . IDENTITY ) 
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private CommunityUser author;
	
	private LocalDateTime created;
	
	@ManyToOne
	@JoinColumn(name = "gallery_board_id", nullable = false)
	private GalleryBoard galleryBoard;
	
	private int views;
	private int likes;
	private int comments;
	
}
