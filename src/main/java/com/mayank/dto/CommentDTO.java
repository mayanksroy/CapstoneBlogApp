package com.mayank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CommentDTO {
	
	private Long cid;
	
	@NotNull(message = "BlogID cannot be Null")
	private Long bid;
	
	@NotBlank(message = "Comment cannot be Blank")
	@Size(min = 3, max = 200, message = "Comment must be between 3 to 200 characters")
	private String comment;
}
