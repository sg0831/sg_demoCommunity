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

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gallery_board")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GalleryBoard {
	
	@Id  @GeneratedValue ( strategy  =  GenerationType . IDENTITY ) 
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "from_gallery_id")
	private Gallery fromGallery;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@CreatedDate
	private LocalDateTime created;
	
}
