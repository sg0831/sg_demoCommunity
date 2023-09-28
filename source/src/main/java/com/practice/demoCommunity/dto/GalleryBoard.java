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
import javax.persistence.UniqueConstraint;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gallery_board", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"from_gallery_id", "name"})
	}) 
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
	
	@Column(name = "created")
	private LocalDateTime created;
	
	@Column(name = "order_num")
	private int orderNum;
	
}
