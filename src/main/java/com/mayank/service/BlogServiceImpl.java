package com.mayank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayank.dto.BlogDTO;
import com.mayank.entity.Blog;
import com.mayank.exception.BlogNotFoundException;
import com.mayank.repository.BlogRepository;

import jakarta.validation.Valid;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	public BlogRepository blogrepo;

	@Autowired
	public ModelMapper mapper;

	@Override
	public BlogDTO postBlog(@Valid BlogDTO blogdto) {
		// TODO Auto-generated method stub
		Blog blog = mapper.map(blogdto, Blog.class);
		blog = blogrepo.save(blog);
		return mapper.map(blog, BlogDTO.class);
	}

	@Override
	public BlogDTO getBlog(Long id) {
		// TODO Auto-generated method stub
		Blog blog = blogrepo.findById(id)
				.orElseThrow(() -> new BlogNotFoundException("Blog with ID " + id + " not found."));

		return mapper.map(blog, BlogDTO.class);
	}

	@Override
	public BlogDTO updateBlog(Long id, @Valid BlogDTO blogdto) {
		// TODO Auto-generated method stub
		Blog existblog = blogrepo.findById(id)
				.orElseThrow(() -> new BlogNotFoundException("Blog with ID " + id + " not found."));

		existblog.setTitle(blogdto.getTitle());
		existblog.setContent(blogdto.getContent());

		Blog updateblog = blogrepo.save(existblog);
		return mapper.map(updateblog, BlogDTO.class);
	}

	@Override
	public Boolean deleteBlog(Long id) {
		// TODO Auto-generated method stub
		if (!blogrepo.existsById(id)) {
			throw new BlogNotFoundException("Blog with ID " + id + " not found.");
		}
		blogrepo.deleteById(id);

		return true;
	}

	// Optional Method
	@Override
	public List<BlogDTO> getAllBlogs() {
		// TODO Auto-generated method stub
		List<Blog> blog = blogrepo.findAll();
		return blog.stream().map(blogs -> mapper.map(blogs, BlogDTO.class)).collect(Collectors.toList());
	}
}
