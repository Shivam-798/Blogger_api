package com.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private int categoryId;
	@NotBlank
	private String categoryTitle;
	
	@NotBlank
	private String categoryDescription;
}
