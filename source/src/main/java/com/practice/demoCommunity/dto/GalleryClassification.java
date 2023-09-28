package com.practice.demoCommunity.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gallery_classification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GalleryClassification {

	@Id
	private String name;
	
}
