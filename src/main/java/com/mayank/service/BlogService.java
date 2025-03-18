package com.mayank.service;

import java.util.List;

import com.mayank.dto.BlogDTO;

import jakarta.validation.Valid;

public interface BlogService {

	BlogDTO postBlog(@Valid BlogDTO blogdto);

	BlogDTO getBlog(Long id);

	BlogDTO updateBlog(Long id, @Valid BlogDTO blogdto);

	Boolean deleteBlog(Long id);

	// Optional or Extra Method(s)
	List<BlogDTO> getAllBlogs();

}
