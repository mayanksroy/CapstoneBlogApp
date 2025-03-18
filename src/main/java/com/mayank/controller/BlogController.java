package com.mayank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayank.dto.BlogDTO;
import com.mayank.dto.CommentDTO;
import com.mayank.service.BlogService;
import com.mayank.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs")
@Tag(name = "Blogs API", description = "Operations related to Blogs")
public class BlogController {

	@Autowired
	public BlogService blogService;

	@Autowired
	public CommentService commentService;
	
	@PostMapping
	public ResponseEntity<BlogDTO> postBlog(@Valid @RequestBody BlogDTO blogdto) {
		BlogDTO postBlog = blogService.postBlog(blogdto);
		return new ResponseEntity<BlogDTO>(postBlog, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BlogDTO> getBlog(@PathVariable Long id) {
		BlogDTO getBlog = blogService.getBlog(id);
		return new ResponseEntity<BlogDTO>(getBlog, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogdto) {
		BlogDTO updateBlog = blogService.updateBlog(id, blogdto);
		return new ResponseEntity<BlogDTO>(updateBlog, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBlog(@PathVariable Long id) {
		Boolean deleteBlog = blogService.deleteBlog(id);
		return new ResponseEntity<Boolean>(deleteBlog, HttpStatus.OK);
	}
	
	@PostMapping("/comment")
	public ResponseEntity<CommentDTO> postComment(@Valid @RequestBody CommentDTO commentdto) {
		CommentDTO postcomment = commentService.postComment(commentdto);
		return new ResponseEntity<CommentDTO>(postcomment, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BlogDTO>> getAllBlogs() {
		List<BlogDTO> getallblogs = blogService.getAllBlogs();
		return new ResponseEntity<List<BlogDTO>>(getallblogs, HttpStatus.OK);
	}
	
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDTO>> getAllComments() {
		List<CommentDTO> getallcomments = commentService.getAllComments();
		return new ResponseEntity<List<CommentDTO>>(getallcomments, HttpStatus.OK);
	}
}

//@Operation(summary = "Create a new Blog", description = "Adds a new Blog to the Blog MySQL Server Database")
//@ApiResponses({ @ApiResponse(responseCode = "201", description = "Blog created successfully"),
//		@ApiResponse(responseCode = "400", description = "Invalid/Bad Request") })
//@PostMapping
//public ResponseEntity<BlogDTO> postBlog(@Valid @RequestBody BlogDTO blogdto) {
//	BlogDTO postBlog = blogService.postBlog(blogdto);
//	return new ResponseEntity<BlogDTO>(postBlog, HttpStatus.CREATED);
//}
//
//@Operation(summary = "Get Blog", description = "Get Blog data from the Blog MySQL Server Database")
//@ApiResponses({ @ApiResponse(responseCode = "200", description = "Blog fetched successfully"),
//		@ApiResponse(responseCode = "404", description = "Blog Not Found") })
//@GetMapping("/{id}")
//public ResponseEntity<BlogDTO> getBlog(@PathVariable Long id) {
//	BlogDTO getBlog = blogService.getBlog(id);
//	return new ResponseEntity<BlogDTO>(getBlog, HttpStatus.OK);
//}
//
//@Operation(summary = "Update Blog", description = "Update Blog data in the Blog MySQL Server Database")
//@ApiResponses({ @ApiResponse(responseCode = "200", description = "Blog Updated successfully"),
//		@ApiResponse(responseCode = "404", description = "Blog Not Found") })
//@PutMapping("/{id}")
//public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id, @Valid @RequestBody BlogDTO blogdto) {
//	BlogDTO updateBlog = blogService.updateBlog(id, blogdto);
//	return new ResponseEntity<BlogDTO>(updateBlog, HttpStatus.OK);
//}
//
//@Operation(summary = "Delete Blog", description = "Delete Blog data from the Blog MySQL Server Database")
//@ApiResponses({ @ApiResponse(responseCode = "200", description = "Blog Deleted successfully"),
//		@ApiResponse(responseCode = "404", description = "Blog Not Found") })
//@DeleteMapping("/{id}")
//public ResponseEntity<Boolean> deleteBlog(@PathVariable Long id) {
//	Boolean deleteBlog = blogService.deleteBlog(id);
//	return new ResponseEntity<Boolean>(deleteBlog, HttpStatus.OK);
//}
//
//@Operation(summary = "Post a new Comment", description = "Add comment in the Comment MySQL Server Database")
//@ApiResponses({ @ApiResponse(responseCode = "201", description = "Comment posted successfully"),
//		@ApiResponse(responseCode = "404", description = "Blog not found to post Comment") })
//@PostMapping("/comment")
//public ResponseEntity<CommentDTO> postComment(@Valid @RequestBody CommentDTO commentdto) {
//	CommentDTO postcomment = commentService.postComment(commentdto);
//	return new ResponseEntity<CommentDTO>(postcomment, HttpStatus.OK);
//}
//
//@Operation(summary = "Get All Blogs", description = "Fetch All Blogs from the Blog MySQL Server Database")
//@ApiResponses({ @ApiResponse(responseCode = "200", description = "Blogs Fetched successfully"),
//		@ApiResponse(responseCode = "404", description = "Blogs Not Found") })
//@GetMapping("/all")
//public ResponseEntity<List<BlogDTO>> getAllBlogs() {
//	List<BlogDTO> getallblogs = blogService.getAllBlogs();
//	return new ResponseEntity<List<BlogDTO>>(getallblogs, HttpStatus.OK);
//}
//
//@Operation(summary = "Get All Comments", description = "Fetch All Comments from the Blog MySQL Server Database")
//@ApiResponses({ @ApiResponse(responseCode = "200", description = "Comments Fetched successfully"),
//		@ApiResponse(responseCode = "404", description = "Comments Not Found") })
//@GetMapping("/comments")
//public ResponseEntity<List<CommentDTO>> getAllComments() {
//	List<CommentDTO> getallcomments = commentService.getAllComments();
//	return new ResponseEntity<List<CommentDTO>>(getallcomments, HttpStatus.OK);
//}
