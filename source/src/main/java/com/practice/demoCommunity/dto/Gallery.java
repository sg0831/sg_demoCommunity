package com.practice.demoCommunity.dto;

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
@Table(name = "gallery")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gallery {

	@Id  @GeneratedValue ( strategy  =  GenerationType . IDENTITY ) 
	private Long id;
	
	@Column(name = "name", nullable = false, unique =  true)
	private String name;
	
	@Column(name = "discription", nullable = false)
	private String discription;
	
	@ManyToOne
	@JoinColumn(name = "gallery_classification_name", nullable = false)
	private GalleryClassification galleryClassification;
	
	@ManyToOne
	@JoinColumn(name = "gallery_admin_id", nullable = false)
	private CommunityUser galleryAdmin;
	
	private int members;
	private int boards;
	private int posts;
	
}
