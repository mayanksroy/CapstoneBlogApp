package com.mayank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Getter 
@Setter
@Builder
public class BlogDTO {
	
	private Long bid;
	
	@NotBlank(message = "Title cannot be Blank")
	@Size(min = 3, max = 100, message = "Title must be between 3 to 100 characters")
	private String title;
	
	@NotBlank(message = "Content cannot be Blank")
	@Size(min = 3, max = 200, message = "Content must be between 3 to 200 characters")
	private String content;
}
